FROM jenkins:2.19.1
MAINTAINER Matthieu Jaulin "mjaulin@sii.fr"

RUN mkdir -p /usr/share/jenkins/ref/init.groovy.d
RUN mkdir -p /usr/share/jenkins/ref/secrets

COPY ref/init.groovy /usr/share/jenkins/ref/init.groovy.d/tcp-slave-agent-port.groovy
COPY ref/basic-security.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY ref/executors.groovy /usr/share/jenkins/ref/init.groovy.d/

RUN echo 2.19.1 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state && \
	echo 2.19.1 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion

RUN echo false > /usr/share/jenkins/ref/secrets/slave-to-master-security-kill-switch

RUN /usr/local/bin/install-plugins.sh workflow-aggregator docker-plugin maven git artifactory sonar pipeline-utility-steps
