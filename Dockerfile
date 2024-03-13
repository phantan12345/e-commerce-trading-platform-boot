
FROM openjdk:17
EXPOSE 8080
ADD e-commerce-trading-platform/target/spring-boot-docker.jar spring-boot-docker.jar 
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]