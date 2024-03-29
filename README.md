# Informações sobre o projeto Season Ticket Holder Service

- A aplicação possui uma documentação que pode ser acessada por meio do link: http://localhost:8082/swagger-ui.html

- Para incializar a aplicação execute o comando:  ` mvn spring-boot:run`

- A aplicação também possui um banco MongoDB _embbeded_ que utiliza a porta: `56123`

- A porta utilizada pela aplicação é a: `8082` 

## Tecnologias utilizadas:

- Java 8

- Maven: utilizado na construção do projeto e gerenciamento dos _frameworks_ utilizados

- Spring Boot: utilizado na construção do projeto _Spring_

- Spring Cloud Feign: utilizado para a integração entre microserviços.

- Spring Cloud Hytrix: utilizado para criação de métodos de _fallback_, caso um microserviço esteja _down_

- MongoDB: utilizado como o banco de dados da aplicação, por se assemelhar aos tipos de dados que vão trafegar na aplicação.

- Swagger: utilizado para documentar as chamadas do microserviço.

- AssertJ / JUnit / Mockito: utilizados para a criação dos testes da aplicação.

- Spring Data MongoDB: utilizado por conter uma interface de persistência que facilita a integração da aplicação com o banco de dados.

- Jackson: utilizado na conversão de dados JSON e Java.

### Estratégias utilizadas para a construção do projeto

Para a criação do projeto, optei por utilizar alguns "padrões" de mercado, como o **Spring Boot**, que é bastante utilizado por ser um _framework_ de fácil utilização e com integrações para várias outras ferramentas. 

Já para banco de dados, optei pelo MongoDB, por se assemelhar com aos tipos de dados trafegados na aplicação, juntamente com ele foi utilizado o **Spring Data MongoDB** que facilita a realização de CRUD's no sistema.

Já para a integração entre microserviços e estrategias de _fallback_ , segui na linha de utilizar os "padrões" do mercado
e utilizei o **Feign** e o **Hystrix** respectivamente.

Para seguir os requisitos do projeto, foi criado um serviço Rest Post, que realiza somente a criação do usuário e retorna a lista de campanhas associadas ao time escolhido.

O serviço de criação do usuário pode ser executado utilizando a documentação no **Swagger**, o formato suportado pela aplicação é somente **JSON**. 

Para enviar esse **JSON** é necessário informar todas os campos para a criação, que são:
```JSON
{
  "teamId": "string",
  "ticketHolderDate": "string",
  "ticketHolderEmail": "string",
  "ticketHolderName": "string"
}
```
- O formato para a inserção de datas é o ``YYY-MM-DD``.

As respostas do serviço à tentativa de criação do usuário: 

- Caso o microserviço de campanhas esteja _up_, ao criar o usuário é retornado uma lista de campanhas ativas para o respectivo time e o _status code_ retornado é o **201**.

- Caso contrário, é retornado somente o _status code_ **201**, sem campanhas associadas.

- Caso haja uma tentativa de criar um usuário com o mesmo e-mail e com campanhas associadas é retornado um erro informando que o usuário já existe na base.

#### Informações a respeito dos pacotes e classes do projeto:

Todas as classes possuem _Javadoc_ para facilitar o entendimento do código.