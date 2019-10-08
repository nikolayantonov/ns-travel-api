FROM openjdk:8
RUN mkdir -p ~/app
WORKDIR /app
COPY /target/travel-api-1.0.0.jar /app/travel-api-1.0.0.jar
RUN chmod +x /app/travel-api-1.0.0.jar
CMD ["java", "-jar", "travel-api-1.0.0.jar"]
