FROM java:8-jdk-alpine
MAINTAINER Matthieu Jaulin "xxx@gmail.com"

RUN mkdir /root/demo
COPY demo*.jar /root/demo/demo.jar
WORKDIR /root/demo

EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]
