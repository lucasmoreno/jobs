# Sou o Paulo Sales e finalizei meu teste e meus dados

#### Meus dados para contato
[email](paulovitorns@gmail.com)
cel/whatsApp: 11 99392-7242
[skype](paulo.nogueirasales@hotmail.com)

## Teste Mobile
Nesse teste é preciso criar um app com 3 telas, usando a API do [Paggi](http://docs.paggi.com/), com o token de DEMO (`B31DCE74-E768-43ED-86DA-85501612548F`)

## Criar transação
Criar transação com os campos:

 - `amount` - Valor transacionado
 - `installments_number` - Número de parcelas
 - `card_id` - Cartão que será transacionado

[API](http://docs.paggi.com/docs/charges-2)

## Listar transações
Lista transações com os campos:

 - `status` - Status da transação
 - `amount` - Valor transacionado
 - `expected_compensation` - Data que a transações será compensada
 - `created_at` - Data que a transação foi feita

[API](http://docs.paggi.com/docs/charges)

## Listar pagamentos
Lista de pagamentos com os campos:

 - `amount` - Valor liquido que foi compensado
 - `compensated_at` - Data que compensação foi feita
 - `status` - Status da compensação

[API](http://docs.paggi.com/docs/compensations)

#### Obs
* Não se preocupe com a consistencia dos dados retornados pela API, pois nossa chave de DEMO não persisti os dados
* UX será considerado um diferencial nesse teste
* Ao terminar, abra um PR do repo principal e entraremos em contato

## Boa sorte e que a força esteja com você !
