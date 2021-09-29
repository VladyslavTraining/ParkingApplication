FROM openjdk:8
ADD parking-ms/target/parking-api-1.0-SNAPSHOT.jar docker-parking.jar
ENTRYPOINT ["java","-jar","docker-parking.jar"]
#FROM maven:latest
#RUN mvn clean install