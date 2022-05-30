# Unicomer Customer Service

### Indicaciones Generales del Servicio customer-project
### JAVA 8 + JPA Repository + Postgresql + Swagger
para la correcta ejecución, se debe editar el archivo application.properties y configurar el
usuario y password de base de datos a utilizar, solo se debe crear el esquema de base de datos llamado unicomer 
las tablas se crearán solas, editar las siguientes variables:
spring.datasource.username=usuario
spring.datasource.password=password

### compilar
##### mvn clean package


### Ejecutar
##### java -Dws.properties=file:/home/config/application.properties -jar customer-0.0.1.jar

### Acceder a swagger
##### http://localhost:9090/swagger-ui.html


A continuación se detallan las API y con los request correspondientes:
Proyecto

### Agregar Customer
### POST
#### http://localhost:9090/v1/customer
request:
{
  "firstName": "rodrigo",
  "lastName": "cisternas zanni",
  "birthday": "09-12-1985",
  "gender": "masculino",
  "cellphone": 999246311,
  "homePhone": 12345678,
  "addressHome": "tobalaba 123",
  "profession": "software engineer",
  "incomes": 4800000
}
response:
{
  "headers": {},
  "body": "Save successfull",
  "statusCode": "CREATED",
  "statusCodeValue": 201
}


### Modificar Customer
### PUT
#### http://localhost:9090/v1/customer
request:
{
  "id":1,
  "firstName": "rodrigo",
  "lastName": "cisternas zanni",
  "birthday": "09-12-1985",
  "gender": "masculino",
  "cellphone": 999246311,
  "homePhone": 12345678,
  "addressHome": "diego de almagro 123",
  "profession": "software engineer",
  "incomes": 580000
}
response:
{
  "headers": {},
  "body": "Update successfull",
  "statusCode": "OK",
  "statusCodeValue": 200
}


### Consultar todos los Customers
### GET
#### http://localhost:9090/v1/customer

response:
[
  {
    "id": 1,
    "firstName": "rodrigo",
    "lastName": "cisternas zanni",
    "birthday": "09-12-1985",
    "gender": "masculino",
    "cellphone": 999246311,
    "homePhone": 12345678,
    "addressHome": "diego de almagro 123",
    "profession": "software engineer",
    "incomes": 580000
  },
  {
    "id": 2,
    "firstName": "juan",
    "lastName": "perez",
    "birthday": "01-01-1980",
    "gender": "masculino",
    "cellphone": 1111111111,
    "homePhone": 12345678,
    "addressHome": "pedro de validivia 123",
    "profession": "software engineer",
    "incomes": 4800000
  }
]

### Consultar un customer en particular los Customers
### GET
#### http://localhost:9090/v1/customer/1
response:
{
  "id": 1,
  "firstName": "rodrigo",
  "lastName": "cisternas zanni",
  "birthday": "09-12-1985",
  "gender": "masculino",
  "cellphone": 999246311,
  "homePhone": 12345678,
  "addressHome": "diego de almagro 123",
  "profession": "software engineer",
  "incomes": 580000
}