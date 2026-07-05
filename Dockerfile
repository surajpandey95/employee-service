#FROM eclipse-temurin:17-jre

#WORKDIR /app

#COPY target/*.jar app.jar

#EXPOSE 8080

#ENTRYPOINT ["java","-jar","app.jar"]

docker build -t employee-service:v1 .

docker run -d -p 8081:8080 --name employee-app employee-service:v1

