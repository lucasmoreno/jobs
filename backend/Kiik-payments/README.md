#Teste Back-end 

##Pré-requesitos 

- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) 
- [GlassFish 4](https://glassfish.java.net/download.html)

(obs: Para fazer o teste sem a necessidade de configurar o sistema, vá direto para o tópico **Testando a aplicação**)

##Configuração

- Baixar e instalar o [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) 
- Baixar o [GlassFish](https://glassfish.java.net/download.html) e seguir as instruções para subir o servidor

##Fazendo o deploy da aplicação no GlassFish

###Após seguir as instruções de como [instalar e subir o GlassFish](https://glassfish.java.net/download.html)

1. Entrar no console de configuração: http://localhost:4848
2. No menu do lado esquerdo entrar em **_Applications_**  
(obs: Em **Applications** estão todos os deployments feitos)
3. Na tabela **Deployed Applications** clicar no botão **_Deploy_**
4. Na página **Deploy Applications or Modules** clicar em **_Choose File_**
5. Escolher o arquivo war para deployment (URI: /Kiik-payments/Kiik-payments.war)

##Testando a aplicação 

- O end-point é: **http://localhost:8080/Kiik-payments/charge**

- Para o teste sem precisar configurar o servidor e fazer o deploy: existe uma instância desta aplicação no seguinte endereço **http://35.160.30.34:8080/Kiik-payments/charge**

###Para testar a aplicação pode se fazer uma requisição POST usando [Postman](https://www.getpostman.com) ou [SoapUI](https://www.soapui.org):
```
{
  "amount": 30,
  "card": {
    "holder": "KiiK Company",
    "number": "4200000000000000",
    "expiration_month": "12",
    "expiration_year": "2017",
    "cvv": "123"
  },
  "intermediaries": [
    {
      "fee": 6,
      "flat": 2,
      "description": "Tax of KiiK 1"
    },
    {
      "fee": 0,
      "flat": 10,
      "description": "Tax of KiiK 2"
    }
  ]
}
```

A resposta será:
```
{
  "id": "NDIwMDAwMDAwMDAwMDAwMDIwMTYtMTEtMDlUMTI6NDM6NDIuMTQy",
  "amount": 30,
  "card_id": "NDIwMDAwMDAwMDAwMDAwMDEyMjAxNi0xMS0wOVQxMjo0Mzo0Mi4xNDI=",
  "intermediaries": [
    {
      "fee": 6,
      "flat": 2,
      "description": "Tax of KiiK 1",
      "amount": 8
    },
    {
      "fee": 0,
      "flat": 10,
      "description": "Tax of KiiK 2",
      "amount": 10
    }
  ]
}
```
*Obs: as propriedades "id" e "card_id" não serão igual entre um request e outro, porque esses ids são gerados usando dados dos cartão combinados com data e hora.*

