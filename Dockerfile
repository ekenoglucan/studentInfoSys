FROM maven:3-openjdk-11 as builder
WORKDIR /app/source
COPY .. /app/source
RUN mvn clean package -DskipTests=true

FROM openjdk:11 as runner
WORKDIR /app
COPY --from=builder /studentInfoSys/app/source/target/demo-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java" , "-jar", "demo-0.0.1-SNAPSHOT.jar"]