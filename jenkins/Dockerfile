FROM jenkins:1.642.4
MAINTAINER Matthieu Jaulin "mjaulin@sii.fr"

COPY plugins.txt /var/jenkins_home/plugins.txt
RUN /usr/local/bin/plugins.sh /var/jenkins_home/plugins.txt

COPY config/* /usr/share/jenkins/ref/
COPY users /usr/share/jenkins/ref/users

