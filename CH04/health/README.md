# Health Check sample

A sample project for _Health_ section of _Chapter 4: Observability_. It's based on _Helidon Quickstart_ application generated using [helidon Project Starter](https://helidon.io/starter/3.2.0?flavor=mp&step=2). The addition to it is `WorkingHoursCheck.java` class containing a liveness health check returning _UP_ between 9am and 17pm local time. 

## Build and run

With JDK17+
```bash
mvn package
java -jar target/ch04-health.jar
```

## Try it

Health will return _UP_ only between 9am and 17pm local time. It will return _DOWN_ in other time.
```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...
. . .
```

