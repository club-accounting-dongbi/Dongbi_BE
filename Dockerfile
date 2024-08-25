FROM openjdk:17-alpine

ADD /build/libs/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

ENV TZ=Asia/Seoul