FROM maven:3.6-jdk-8-slim
COPY . /repo
WORKDIR /repo
RUN mvn -X package

FROM openjdk:8-slim
COPY --from=0 /repo/target/travel-api-1.0.0.jar /app/travel-api-1.0.0.jar
WORKDIR /app
RUN chmod +x /app/travel-api-1.0.0.jar
CMD ["java", "-jar", "travel-api-1.0.0.jar"]
