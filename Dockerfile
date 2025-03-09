FROM eclipse-temurin:21-jdk as build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle gradlew ./
COPY settings.gradle ./

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jdk
VOLUME /tmp

# Fix: Either use a specific path without wildcard
COPY --from=build /workspace/app/build/libs/demo-mcp-1.0-SNAPSHOT.jar /app/
# Or use a directory as destination with trailing slash
# Alternative approach if you know the exact JAR name:
# COPY --from=build /workspace/app/build/libs/your-specific-app.jar app.jar

WORKDIR /app
RUN mkdir -p /app/data
VOLUME /app/data

ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8080

HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:$SERVER_PORT/ || exit 1

# Update the entrypoint to use the JAR in the /app directory
ENTRYPOINT ["java", "-jar", "/app/demo-mcp-1.0-SNAPSHOT.jar"]