FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine AS build

RUN apk add --no-cache git

WORKDIR /app

RUN git clone --branch main --depth 1 https://github.com/NnoyTra/hospital-ms.git .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw package -DskipTests -B

# ---------- Stage 2: Runtime ----------
FROM --platform=linux/amd64 eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/target/nnoi-hospital-ms.jar app.jar

#EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]