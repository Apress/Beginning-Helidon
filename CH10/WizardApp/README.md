# Chapter 10. Testing Wizard  Service.

Run tests of the Wizard app.

## Build and run

With JDK17+
```bash
mvn clean package
java -jar target/WizardApp.jar
```

## Exercise the application

In another terminal window please run:
```bash
#To get the most mighty wizard 
curl -X GET http://localhost:8080/wizard
{"name":"Oz"}
```
