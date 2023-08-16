FROM maven:3.8.7-openjdk-18-slim as build

WORKDIR /helidon

ADD pom.xml .
RUN mvn package -Dmaven.test.skip -Declipselink.weave.skip

ADD src src
RUN mvn package -DskipTests

FROM openjdk:19-alpine
WORKDIR /helidon

COPY --from=build /helidon/target/watchtower-jwt.jar ./
COPY --from=build /helidon/target/libs ./libs

CMD ["java", "-jar", "watchtower-jwt.jar"]
