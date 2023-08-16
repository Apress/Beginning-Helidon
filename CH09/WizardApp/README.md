# Chapter 7. Wizard Service.

OpenAPI Wizard application

## Build and run

With JDK17+
```bash
mvn package
java -jar target/WizardApp.jar
```

## Exercise the application

In another terminal window please run:
```bash
#To get the most mighty wizard 
curl -X GET http://localhost:8080/wizard
{"name":"Oz"}

#To get a wizard by name
curl -X GET http://localhost:8080/wizard/Skylar
{"name":"Skylar"}
```
