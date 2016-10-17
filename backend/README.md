# Teste Back-end
Nesse teste é preciso fazer uma API REST (JSON), com um endpoint:

#### [POST] /charge
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

#### Obs
* Não é preciso persistir os dados em um banco
* `intermediaries` é uma array com os intermediarios que receberam uma porcentagem (fee) e/ou valor fixo (flat) da transação
* `amount` no retorno dos intermediarios precisa ser calculado sobre o `fee` e o `flat`
* `card_id` no retorno pode ser randômico
* Ao terminar, abra um PR do repo principal e entraremos em contato

## Boa sorte e que a força esteja com você !
