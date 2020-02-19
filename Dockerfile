FROM openjdk:8-jre-alpine

ADD target/module-0.0.1-SNAPSHOT.jar /opt/mazzama/module-0.0.1-SNAPSHOT.jar
#ADD application-docker.properties /config/application-docker.properties

EXPOSE 8181 8181

ENTRYPOINT [ "java", "-jar", "/opt/mazzama/module-0.0.1-SNAPSHOT.jar" ]
