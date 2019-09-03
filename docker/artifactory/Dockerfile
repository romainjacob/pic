FROM jfrog-docker-reg2.bintray.io/jfrog/artifactory-oss:4.7.4
MAINTAINER Matthieu Jaulin "xxx@gmail.com"

COPY security.import.xml /var/opt/jfrog/artifactory/etc/
RUN chown -R artifactory:artifactory /var/opt/jfrog/artifactory/etc/ 
