FROM ghcr.io/carlossg/maven:eclipse-temurin-17-alpine as build

WORKDIR /helidon

ADD pom.xml .
RUN mvn package -Dmaven.test.skip -Declipselink.weave.skip

ADD src src
RUN mvn package -DskipTests
RUN echo "done!"

FROM ghcr.io/graalvm/jdk:java17
WORKDIR /helidon

COPY --from=build /helidon/target/payment-service.jar ./
COPY --from=build /helidon/target/libs ./libs

CMD ["java", "-jar", "payment-service.jar"]

EXPOSE 8080
