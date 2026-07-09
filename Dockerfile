FROM openjdk:28-ea-jdk
ADD target/nnoi-hospital-ms.jar .
ENTRYPOINT ["java", "-jar", "/nnoi-hospital-ms.jar"]
