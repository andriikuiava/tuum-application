# Build Stage
FROM gradle:latest AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test --no-daemon

# Runtime Stage
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
