# shortUrl
This is a java project run with spring boot
It has 2 end points 
  1- add new url
  2- get original url from short url
It uses mysql to store the data
You can find also postman collection you can run it to test the apis

## run the application using docker compose
just download the source code and move to directory 
'run_with_docker_compose' and run from terminal the following command
```bash
docker-compose up
```
you can use postman collection to test the apis



## build application jar
go to application directory then run the following command from terminal
```bash
mvn clean install
```
## create mysql docker container
open new terminal and run the following commands 
```bash
1- docker network create url-mysql
2- docker container run --name mysqldb --network url-mysql -e MYSQL_ROOT_PASSWORD=root 
-e MYSQL_DATABASE=short_url -d mysql:5.7
```
## check the container is running
```bash
docker container logs -f mysqldb
```
## connect to mysql
```bash
docker container exec -it mysqldb bash
mysql -uroot -proot
show databases;
```
## configure Dockerfile
From openjdk:8
copy ./target/short_url-1.0-SNAPSHOT.jar short_url-1.0-SNAPSHOT.jar
CMD ["java","-jar","short_url-1.0-SNAPSHOT.jar"]

## create docker container for application
```bash
docker image build -t short_url .
```
## run docker 
```bash
docker container run --network url-mysql --name short_url-container -p 8080:8080 -d short_url
```
## check application is running
```bash
docker container logs short_url-container
```
