# Teste Back-end
Set up da API
#### Com docker
Ps.: Variáveis com "$" não são existem.
* cd /path/to/backend
* docker build .
* docker tag $DOCKER_HASH kiik:latest
* docker run -it -d -p 80:80 -p 4567:4567 --name kiik kiik:latest
* Use essa extensão para facilitar a request :) https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
* Selecione POST e cole o json em "Raw payload"
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
### Sem Docker
* cd /path/to/backend
* ruby app/app.rb
* Use essa extensão para facilitar a request (ou qq outra similar) :) https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
* Selecione POST e cole o json em “Raw payload”

## Créditos: Rode a aplicação e faça uma request GET no / :D

