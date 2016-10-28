# Teste para Desenvolvedor na [KiiK](http://www.kiik.com.br)

## [Paulo Henrique Rodrigues Pinheiro](http://www.about.me/paulohrpinheiro)

Completei 30 anos de experiência profissional, iniciando como Técnico em
processamento de dados. Aprendi a aprender, e tenho me adaptado às mudanças
tecnológicas ocorridas.

Desde que a Internet comercial começou a funcionar no Brasil, estou
trabalhando com empresas voltadas a esse segmento, passando por provedor de
acesso (Onda), Software Livre (Conectiva), consultoria de serviços (Haxent) e
treinamentos on-line (DigitalSK).

"Seus sistemas nas nuvens!" tem sido o mote de minha atuação. Desde a
infraestrutura, passando pelo desenvolvimento e administração do sistema, meu
trabalho é criar e manter funcionando sistemas WEB.

Construindo estrutura para que desenvolvedores possam trabalhar preocupados
apenas com desenvolvimento, sempre atuei dando suporte não só à estrutura e
pessoal DEV, mas também à de testes, homologação e produção. Por ser
programador, costumo atuar também como revisor de código ou em pair
programming.

Ah, e compartilhar informação é fundamental!

Alguns endereços úteis:

 * [GitHub](https://www.github.com/paulohrpinheiro)

 * [Twitter](https://www.twitter.om/PauloHRPinheiro)

 * [LinkedIn](https://br.linkedin.com/in/paulohrpinheiro)

 * [email](mailto://paulohrpinheiro@gmail.com)


## Tecnologias usadas

Desenvolvi o sistema em _Python3_, plataforma _Linux_, usando
[CherryPy](http://www.cherrypy.org) como _framework_.


## Configuração inicial

Em primeiro lugar, é preciso ter o Python3 instalado na máquina. Os pacotes
para as várias distribuições, em geral, tem *python3* como seu prefixo, para
diferenciar do *Python2*.

Iremos usar uma virtualenv para não poluir o ambiente. Não é preciso ser
_root_, em um terminal execute os seguintes comandos:

 * Criando a _virtualenv_ (esse passo será executa apenas uma vez):

    ```
python3 -m venv ~/paulohrpinheiro
    ```

 * Ativando a _virtualenv_ (toda vez que for testar, deve ser executado):

    ```
source ~/paulohrpinheiro/bin/activate
    ```

 * Instalando os pacotes necessários (apenas na primeira vez):

    ```
pip install pip --upgrade
pip install -r requirements.txt
    ```


## Rodando os testes

Para testar o sistema, basta executar o seguinte comando:

    pytest -v



## Rodando o server

Basta executar o seguinte comando, dentro da pasta [/restserver]:

    backend/restserver/run_server.py

E então se poderá fazer requisições com um comando como:

    curl \
     -H "Content-Type: application/json" \
     -X POST \
     -d '{
            "card": {
                "holder": "holder",
                "number": "number",
                "cvv": "cvv",
                "expiration_month": "expiration_month",
                "expiration_year": "expiration_year"
            },
            "amount": 10.0,
            "intermediaries": [{"fee": 10.0, "flat":2}]
        }' \
     http://localhost:8080/charge -D -


Para se obter a seguinte resposta:
```
HTTP/1.1 200 OK
Content-Length: 281
Server: CherryPy/8.1.3.dev10+ng84c5b39.post.20161026
Content-Type: application/json
Date: Fri, 28 Oct 2016 00:00:08 GMT

{"card_id": 4347063628754679130122859914480620298, "id": 174666299926374485761238449167153364882, "intermediaries": [{"description": "", "flat": 2, "amount": 3.0, "fee": 1.0}], "inserted_at": "2016-10-27T22:00:08.357967", "amount": 10.0, "updated_at": "2016-10-27T22:00:08.358003"}
```
