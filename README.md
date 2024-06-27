# nisum

Clonar el repositorio 
![image](https://github.com/alerojaslopenza/nisum/assets/39816424/4fea3df0-ac18-45cb-addb-e9ee5a2cb857)


Al terminar de clonar el repositorio, se abre con el IDE eclipse, se descargarán todas las dependencias maven necesarias para desplegar proyecto. En caso de ser la primera ocasión en la que se realiza este paso puede demorarse varios minutos.
![image](https://github.com/alerojaslopenza/nisum/assets/39816424/e439cfe7-361d-4cbb-ba19-6e10fa13a378)

El proyecto esta preparado para poder ejecutarse. Para ello vamos a iniciar revisando las distintas formas que podemos emplear para lanzar el proyecto desde:

Botón secundario sobre el proyecto > Run As > Run as > Java Application

![image](https://github.com/alerojaslopenza/nisum/assets/39816424/4d6bcb93-965f-4bab-9d83-b41127f15e7b)

Una vez levantada la aplicación, se importa la colección de postman   
{
	"info": {
		"_postman_id": "26c7f75b-ba6f-4cb8-a706-09bb8e805f9a",
		"name": "Nisum",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "6365500",
		"_collection_link": "https://cloudy-desert-207854.postman.co/workspace/nisum~38354c92-ed59-405c-8d6f-80abe2c6dea2/collection/6365500-26c7f75b-ba6f-4cb8-a706-09bb8e805f9a?action=share&source=collection_link&creator=6365500"
	},
	"item": [
		{
			"name": "RegistrarUser",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"name\":\"test\",\r\n    \"password\":\"Baeldung20@\",\r\n    \"email\":\"alejandrorojas@dominio.cl\",\r\n     \"phones\": [\r\n                    {\r\n                    \"number\": \"1234567\",\r\n                    \"citycode\": \"1\",\r\n                    \"contrycode\": \"57\"\r\n                    }\r\n               ]\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/user",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"user"
					]
				}
			},
			"response": []
		}
	]
}

Importamos la colección al postman

Luego ejecutamos el endpoint RegistrarUser http://localhost:8080/user

![image](https://github.com/alerojaslopenza/nisum/assets/39816424/2a2599ca-8a29-416b-b470-edc373bbc378)

Al ejecutar se registrar el usuario 
![image](https://github.com/alerojaslopenza/nisum/assets/39816424/851ea50e-5a43-42a1-8097-07d628e43cd0)

Ejemplo de estructura para registrar usuario  
{
    "name":"test",
    "password":"Baeldung20@",
    "email":"alejandrorojas@dominio.cl",
     "phones": [
                    {
                    "number": "1234567",
                    "citycode": "1",
                    "contrycode": "57"
                    }
               ]
}

En el siguiente archivo /demo/src/main/resources/application.properties se encuentra la propiedad validator.password en donde se indicará la expresión regular para validar el password

#Scripts

drop table if exists phone cascade  
drop table if exists user_sign cascade   
create table phone (id bigint generated by default as identity, user_id uuid, city_code varchar(255), country_code varchar(255), number varchar(255), primary key (id))  
create table user_sign (is_active boolean not null, created TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP not null, last_login timestamp(6), modified timestamp(6), id uuid not null, email varchar(255) not null unique, name varchar(255) not null, password varchar(255) not null, token varchar(255) not null, primary key (id))
alter table if exists phone add constraint FKmco8ihetnp22ql9f7pc1rkk2s foreign key (user_id) references user_sign



