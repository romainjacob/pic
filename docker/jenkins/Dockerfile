FROM jenkins:2.19.1
MAINTAINER Matthieu Jaulin "mjaulin@sii.fr"

RUN mkdir -p /usr/share/jenkins/ref/init.groovy.d
RUN mkdir -p /usr/share/jenkins/ref/secrets

RUN /usr/local/bin/install-plugins.sh workflow-aggregator docker-plugin maven-plugin git junit artifactory sonar pipeline-utility-steps

COPY ref/* /usr/share/jenkins/ref/init.groovy.d/

RUN echo 2.19.1 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state && \
	echo 2.19.1 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion

RUN echo false > /usr/share/jenkins/ref/secrets/slave-to-master-security-kill-switch
