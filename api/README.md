# Api

Dependências: 
* PostgreSQL com suporte a extensão uuid-ossp
* Elixir 1.3

Processo de start da aplicação:
- Baixa as dependências `mix deps.get`
- Configure o banco de dados conforme instruções do `Ecto`
- Gere o banco de dados `mix ecto.setup`
- Inicie a aplicação `mix phoenix.server`
    
A única rota disponível é `[POST] /charge`.

Exemplo de json para requisição:
```json
{
  "amount": 10,
  "card": {
    "holder": "KiiK Company",
    "number": "4200000000000000",
    "expiration_month": "feb",
    "expiration_year": "2017",
    "cvv": "123"
  },
  "intermediaries": [
    {
      "fee": 0,
      "flat": 5,
      "description": "Tax of KiiK"
    }
  ]
}
```


Algumas considerações:

Devido a minha falta de tempo(eu só teria tempo livre para implementar o teste, nesta segunda e terça), os ultimos dois commits não foram modulares como eu gostaria que fosse. Por esta falta de tempo e por ter acumulado muitas alterações nos últimos commits, não corrigi os testes e nem foram feitos testes de controller e request. Sei que esta situação não é o desejável, principalmente se tratando de um teste, porém decidi acelerar as coisas para que pudesse submeter o teste. Acredito se retornar ao commit `test embeds on Payment` os testes estarão funcionais.
