# Chapter 5. Sorcery Ministry Service.

This is a sample `Sorcery Ministry` service running on port `8081`

## Build and run

With JDK17+
```bash
mvn package
java -jar target/SorceryMinistry.jar
```

## Exercise the application

To use `MicroProfile REST Client`:
```
curl -X GET http://localhost:8081/wizardClient/
{"name":"Oz"}

curl -X GET http://localhost:8081/wizardClient/Skylar
{"name":"Skylar"}
```

To use JAX-RS client:
```
curl -X GET http://localhost:8081/wizardClient/jaxrs
{"name":"Oz"}

curl -X GET http://localhost:8081/wizardClient/jaxrs/Skylar
{"name":"Skylar"}
```

To see Log output, switch to the terminal window running `SorceryMinistry` application and find lines containing intercepted data.

For `MicroProfileRest Client`:
```bash
INFO class mprestclient.io.helidon.book.ch05wizard.client.WizardRequestFilter Thread[#30,helidon-server-2,5,server]: Request intercepted: {Accept=[application/json], Magic-Header=[Custom header magic value]}
INFO class mprestclient.io.helidon.book.ch05wizard.client.WizardResponseFilter Thread[#30,helidon-server-2,5,server]: Intercepted response status{content-length=[13], connection=[keep-alive], Date=[Fri, 30 Sep 2022 10:59:42 +0300], Content-Type=[application/json]}

```

For `JAX-RS Client`:
```bash
INFO java.util.logging.Logger Thread[#31,helidon-server-3,5,server]: {Accept=[application/json]}
INFO java.util.logging.Logger Thread[#31,helidon-server-3,5,server]: {content-length=[13], connection=[keep-alive], Date=[Fri, 30 Sep 2022 11:05:17 +0300], Content-Type=[application/json]}
```
