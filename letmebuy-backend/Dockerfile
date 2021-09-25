FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/letmebuy-0.0.1-SNAPSHOT.jar letmebuy.jar
ENTRYPOINT ["java","-jar","/letmebuy.jar"]