ARG JDK_VERSION=17.0.2

#build
FROM openjdk:${JDK_VERSION}-slim-buster as build
WORKDIR  /springboot
COPY . .
RUN ./gradlew build -x test

# deploy
FROM build as deploy
WORKDIR /app
COPY --from=build /springboot/build .
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "java -jar libs/*0.0.1-SNAPSHOT.jar"]
