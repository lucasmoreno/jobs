# Teste Back-end
Nesse teste é preciso fazer uma API REST (JSON), com um endpoint para criar transação:

## Ferramentas principais
- Ruby 2.2.5
- RSpec 3.6.0
- Sinatra 1.4.8
- Virtus 1.0.5

## Configuração
1. Instalar gem bundler
```console
gem install bundler
```

2. Instalar gems do Gemfile
```console
bundle install
```

3. Iniciar o servidor
```console
bundle exec rackup
```

## Utilização

Endpoint:
```
POST http://localhost:9292/charge
```

###### params:
* card (map)
    * holder
    * number
    * cvv
    * expiration_month
    * expiration_year
* amount
* intermediaries (opcional) (array)
    * fee (opcional)
    * flat (opcional)
    * description (opcional)

###### return:
* id
* card_id
* amount
* intermediaries (array)
    * fee
    * flat
    * amount
    * description
* inserted_at
* updated_at

Exemplo de payload:
```json
{
  "amount": 10,
  "card": {
    "holder": "KiiK Company",
    "number": "4200000000000000",
    "expiration_month": "12",
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

Exemplo de resposta:
```json
{
  "id": "077e3ec2-da33-48f9-bdcf-a3213d962972",
  "card_id": "4fb0f326-cac3-40d8-859b-37f6f5ab7b5f",
  "amount": 10,
  "inserted_at": "2017-08-10T22:21:16-03:00",
  "updated_at": "2017-08-10T22:21:16-03:00",
  "intermediaries": [
      {
          "amount": 5,
          "description": "Tax of KiiK",
          "fee": 0,
          "flat": 5
      }
  ]
}
```


## Testes
```
bundle exec rspec
```

#### Obs
* Não é preciso persistir os dados em um banco
* `intermediaries` é uma array com os intermediarios que receberam uma porcentagem (fee) e/ou valor fixo (flat) da transação
* `amount` no retorno dos intermediarios precisa ser calculado sobre o `fee` e o `flat`
* `card_id` e `id` no retorno podem ser randômicos
* Ao terminar, abra um PR do repo principal e entraremos em contato

## Boa sorte e que a força esteja com você !
