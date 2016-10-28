from cherrypy.test import helper

import json

from ..app import server


class TestUrlCharge(helper.CPWebCase):
    """ Testes para o ponto /charge.
    """

    @staticmethod
    def setup_server():
        """ Apenas configura o serviço.
        """

        server.config_server()

    def assert_content_type(self):
        """ Verifica se o mime está correto.
        """

        self.assertHeader('Content-Type', 'application/json')

    def request(
        self,
        method='POST',
        content_type='application/json',
        body='{}'
    ):
        """ Realiza a requisição a ser testada.
        """

        self.getPage(
            url='/charge',
            method=method,
            headers=[
                ('Content-Type', content_type),
                ('Content-Length', str(len(body))),
            ],
            body=body
        )

    def test_another_methods(self):
        """ Verifica se os outros métodos HTTP estão respondendo.
        """

        for method in ['GET', 'HEAD', 'PUT', 'DELETE', 'TRACE']:
            self.request(method=method)
            self.assert_content_type()
            self.assertStatus('405 Method Not Allowed')

    def test_post_empty_body(self):
        """ Verifica se aceita um a requisição sem dados.
        """

        self.request()
        self.assert_content_type()
        self.assertStatus('400 Bad Request')

    def copy_random_fields(self, from_dict, to_dict):
        """ Copia os dados enviados pelo servidor que não temos como verificar,
        pois são aleatórios ou referentes à data da execução.
        """

        for field in ('id', 'card_id', 'updated_at', 'inserted_at'):
            self.assertTrue(field in from_dict)
            to_dict[field] = from_dict[field]

    def test_post_complete_body(self):
        """ Testa uma requisição completa (com intermediaries) bem formada.
        """

        json_request = json.dumps({
            'card': {
                'holder': 'holder',
                'number': 'number',
                'cvv': 'cvv',
                'expiration_month': 'expiration_month',
                'expiration_year': 'expiration_year',
            },
            'amount': 10.0,
            'intermediaries': [{'fee': 10.0, 'flat': 2.0}]
        })

        json_expected = {
            'id': '',
            'card_id': '',
            'amount': 10.0,
            'intermediaries': [
                {
                    'fee': 1.0,
                    'flat': 2.0,
                    'amount': 3.0,
                    'description': '',
                }
            ]
        }

        self.request(body=json_request)
        self.assert_content_type()
        self.assertStatus('200 OK')

        json_received = json.loads(self.body.decode("utf-8"))
        self.copy_random_fields(from_dict=json_received, to_dict=json_expected)
        self.assertEqual(json_expected, json_received)

    def test_post_incomplete_body(self):
        """ Testa uam requisição incompleta (sem intermediários) bem formada.
        """

        json_request = {
            'card': {
                'holder': 'holder',
                'number': 'number',
                'cvv': 'cvv',
                'expiration_month': 'expiration_month',
                'expiration_year': 'expiration_year',
            },
            'amount': 10.0,
        }

        json_expected = {
            'id': '',
            'card_id': '',
            'amount': 10.0,
        }

        self.request(body=json.dumps(json_request))
        self.assert_content_type()
        self.assertStatus('200 OK')

        json_received = json.loads(self.body.decode("utf-8"))
        self.copy_random_fields(from_dict=json_received, to_dict=json_expected)
        self.assertEqual(json_expected, json_received)
