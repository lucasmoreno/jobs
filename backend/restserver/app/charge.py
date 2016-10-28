import cherrypy

import uuid
import datetime


class Charge:
    """ Classe para o ponto /charge. Recebe dados sobre uma transação e Seus
    intermediários. Retorna o cálculo do valor devido a esses intermediários.
    """

    @cherrypy.expose
    @cherrypy.tools.accept(media='application/json')
    @cherrypy.tools.json_out()
    @cherrypy.tools.json_in()
    def charge(self):
        """POST para criar transação"""

        if 'POST' != cherrypy.request.method.upper():
            raise cherrypy.HTTPError('405 Method Not Allowed', '')

        input_json = cherrypy.request.json

        # raise exception if error in data
        self._verify_input_fields(input_json)

        response = {
            'id': uuid.uuid4().int,
            'card_id': uuid.uuid4().int,
            'inserted_at': datetime.datetime.today().isoformat(),
            'updated_at': datetime.datetime.today().isoformat(),
        }

        response['amount'] = float(input_json['amount'])

        if 'intermediaries' in input_json:
            for intermediary in input_json['intermediaries']:
                intermediary_result = {'amount': 0.0}

                if 'fee' in intermediary:
                    fee = response['amount'] * intermediary['fee'] / 100.0
                    intermediary_result['fee'] = fee
                    intermediary_result['amount'] += fee

                else:
                    intermediary_result['fee'] = 0.0

                if 'flat' in intermediary:
                    intermediary_result['flat'] = intermediary['flat']
                    intermediary_result['amount'] += intermediary['flat']
                else:
                    intermediary_result['flat'] = 0.0

                if 'description' in intermediary:
                    intermediary_result['description'] = \
                        intermediary['description']
                else:
                    intermediary_result['description'] = ''

                if 'intermediaries' not in response:
                    response['intermediaries'] = []

                response['intermediaries'].append(intermediary_result)

        return response

    def _verify_input_fields(self, input_json):
        """ Verifica se os dados recebidos estão conforme a especificação. Caso
        haja algum dado extra ou faltando algum dado obrigatório, ou algum tipo
        não esteja correto, gera uma exceção de Bad Request.
        """

        if 'card' not in input_json:
            self._raise_bad_request('No \'card\' in input data.')

        card = input_json['card']

        if dict != type(card):
            self._raise_bad_request(
                'The \'card\' map is not a map in input data.'
            )

        subfields = (
            'holder',
            'number',
            'cvv',
            'expiration_month',
            'expiration_year',
        )

        # All fields are required
        for field in subfields:
            if field not in card:
                self._raise_bad_request(
                    'No \'card[{}]\' in input data.'.format(field)
                )

        if len(subfields) != len(card):
            self._raise_bad_request('The \'card\' map have invalid elements.')

        if 'amount' not in input_json:
            self._raise_bad_request('No \'amount\' in input data.')

        if not isinstance(input_json['amount'], float):
            print(type(input_json['amount']))
            self._raise_bad_request('The \'amount\' field is not float.')

        if len(input_json) > 3:
            self._raise_bad_request('Input data have invalid elements.')

        if len(input_json) == 3:
            if 'intermediaries' not in input_json:
                self._raise_bad_request('Input data have invalid elements.')
            else:
                intermediaries = input_json['intermediaries']

                if not isinstance(intermediaries, list):
                    self._raise_bad_request(
                        'The \'intermediaries\' map is not '
                        'a map in input data.'
                    )

                subfields = (
                    'fee',
                    'flat',
                    'description',
                )

                # All fields are optional
                for field in intermediaries:
                    if field not in intermediaries:
                        self._raise_bad_request(
                            'Invalid field [{}] '
                            'in intermediaries map'.format(field)
                        )
                    # fee & flat are floats
                    elif field in ('fee', 'flat'):
                        if not isinstance(intermediaries[field], list):
                            self._raise_bad_request(
                                'Invalid type for intermediaries[{}] '
                                'field'.format(field)
                            )

    @staticmethod
    def _raise_bad_request(message):
        """ Gera uma exceção Bad Request.
        """

        raise cherrypy.HTTPError('400 Bad Request', message)
