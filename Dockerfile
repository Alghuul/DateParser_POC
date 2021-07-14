FROM openjdk:8-jre-alpine3.9
 
# copy the packaged jar file into our docker image
COPY target/dateParser-0.0.1-SNAPSHOT-jar-with-dependencies.jar /demo.jar
 
# set the startup command to execute the jar
CMD ["java", "-jar", "/demo.jar"]

EXPOSE 80
# instructions to execute build and run docker image of Jar file
# docker build -t dateparser:1.0-SNAPSHOT .
# docker run -d -p 80:80 dateparser:1.0-SNAPSHOT