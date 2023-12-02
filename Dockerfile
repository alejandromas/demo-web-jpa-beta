FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/demo-web-jpa-beta-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} demo-web-jpa-beta-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/demo-web-jpa-beta-0.0.1-SNAPSHOT.jar"]
