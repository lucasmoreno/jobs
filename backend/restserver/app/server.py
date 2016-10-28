#!/usr/bin/env python3

import cherrypy
import json

from . import charge


def default_error_response(status, message, traceback, version):
    """ Garante que qualquer erro seja gerado com uma estrutura JSON.
    """

    cherrypy.response.headers['Content-Type'] = 'application/json'
    return json.dumps({'result': 'fail', 'error': status, 'message': message})


def config_server():
    """ Apenas configura e "monta" a aplicação.
    """

    cherrypy.tree.mount(charge.Charge(), '/')

    cherrypy.config.update({
        'error_page.default': default_error_response,
    })


def run_server():
    """ Levanta o serviço. Assume que já está configurado.
    """

    cherrypy.config.update({
        'server.socket_host': '127.0.0.1',
        'server.socket_port': 8080,
    })

    cherrypy.engine.start()
    cherrypy.engine.block()
