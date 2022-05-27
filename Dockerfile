FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/ejercicioPractico-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
#ENTRYPOINT exec java $JAVA_OPTS -jar ejerciciopractico.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
