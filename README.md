# Atos:Air France::User API

## Description

A Spring boot API that exposes two services:
<br>
• One that allows to register a user<br>
• One that displays the details of a registered user<br><br>
A user is defined by:<br>
• A userName<br>
• A birthdate<br>
• A country of residence<br><br>
A user has optional attributes:<br>
• A phone number<br>
• A gender<br>

## Prerequisite
• Java 8+ installed and [Configured](https://www.guru99.com/install-java.html) correctly  <br>
• IntelliJ  <br>
• [Maven](https://www.tutorialspoint.com/maven/maven_environment_setup.html)
## Version History
• 1.0
## LINKS
Start the spring boot project and navigate to:<br>
• [H2 Database](http://localhost:8080/h2-console)
    <br>USERNAME : user
    <br>PASSWORD : user-api<br>
• [Swagger](http://localhost:8080/swagger-ui/index.html)<br>
• Example of calls :<br>
• Create a French user :<br>
``` 
$ curl -X POST \
  http://localhost:8080/users/register \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: d26515f0-507a-0247-f59f-a474d61ef7df' \
  -d '{
	"name":"name1",
	"birthDate":"2000-01-23T12:34:56.123+00:00",
	"country": "FRANCE",
	"phoneNumber":"24979033",
	"gender":"MALE"
}'
```
• Display a user :
```
curl -X GET \
  'http://localhost:8080/users/details?id=1' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 94fd6965-5d88-f3f4-9a53-b4f0a860a2f8'
```

