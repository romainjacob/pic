FROM java:8-jdk-alpine
MAINTAINER Matthieu Jaulin "mjaulin@sii.fr"

RUN mkdir /root/demo
COPY demo.jar /root/demo/
WORKDIR /root/demo

EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]