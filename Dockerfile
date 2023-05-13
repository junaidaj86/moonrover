FROM openjdk:18
WORKDIR /app
COPY ./target/rover-1.0.0.jar /app
EXPOSE 8080
CMD ["java", "-jar", "rover-1.0.0.jar"]




