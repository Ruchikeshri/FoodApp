FROM java:8-jre
WORKDIR usr/src
ADD ./target/userservice-1.0.0.jar /usr/src/userservice-1.0.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.0.jar"]
