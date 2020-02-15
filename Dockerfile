From openjdk:8
copy ./target/short_url-1.0-SNAPSHOT.jar short_url-1.0-SNAPSHOT.jar
CMD ["java","-jar","short_url-1.0-SNAPSHOT.jar"]