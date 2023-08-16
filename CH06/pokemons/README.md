# Chapter 6. Pokemons 

Helidon MP application that uses in-memory H2 database.

## Build and run

This example requires a database.
Instructions for H2 can be found here: https://www.h2database.com/html/cheatSheet.html


With JDK17+
```bash
mvn package
java -jar target/pokemons.jar
```

## Exercise the application
```
curl -X GET http://localhost:8080/pokemon
[{"id":1,"type":12,"name":"Bulbasaur"}, ...]

curl -X GET http://localhost:8080/type
[{"id":1,"name":"Normal"}, ...]

curl -H "Content-Type: application/json" --request POST --data '{"id":100, "type":1, "name":"Test"}' http://localhost:8080/pokemon
```