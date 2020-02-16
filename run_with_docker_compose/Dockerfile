From openjdk:8
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
COPY application.properties /application.properties
copy short_url-1.0-SNAPSHOT.jar /short_url-1.0-SNAPSHOT.jar
CMD ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "short_url-1.0-SNAPSHOT.jar"]