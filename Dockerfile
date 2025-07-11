# ----------- Build stage -----------
FROM maven:3-openjdk-17 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests


# ----------- Run stage -----------
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy file JAR từ stage build
COPY --from=build /app/target/gscore-0.0.1-SNAPSHOT.jar app.jar

# Cấu hình port
EXPOSE 8080

# Lệnh khởi chạy
ENTRYPOINT ["java", "-jar", "app.jar"]
