FROM ubuntu:14.04
MAINTAINER Matthieu Jaulin "xxx@gmail.com"

ENV MAVEN_VERSION 3.3.9

RUN locale-gen en_US.UTF-8 
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

# Install commons libraries
RUN apt-get update -qq && apt-get install -qqy \
    openssh-server \
    apt-transport-https \
    ca-certificates \
    curl \
    lxc \
    iptables && \
    apt-get -q autoremove && \
    apt-get -q clean -y && rm -rf /var/lib/apt/lists/* && rm -f /var/cache/apt/*.bin && \
    sed -i 's|session    required     pam_loginuid.so|session    optional     pam_loginuid.so|g' /etc/pam.d/sshd && \
    mkdir -p /var/run/sshd


RUN echo deb https://apt.dockerproject.org/repo ubuntu-trusty main > /etc/apt/sources.list.d/docker.list && \
    apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D

# Install Docker from Docker Inc. repositories.
RUN apt-get update && apt-get install -y docker-engine && rm -rf /var/lib/apt/lists/*

# Install Maven
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /tmp && \
    mv /tmp/apache-maven-$MAVEN_VERSION /usr/share/maven && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn


# Install JDK 8
RUN sed 's/main$/main universe/' -i /etc/apt/sources.list && \
    apt-get update && apt-get install -y software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer libxext-dev libxrender-dev libxtst-dev && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /tmp/*

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

# Set user jenkins to the image
RUN useradd -m -d /home/jenkins -s /bin/sh jenkins && \
    echo "jenkins:jenkins" | chpasswd
RUN mkdir /home/jenkins/.m2
ADD settings.xml /home/jenkins/.m2
RUN chown -R jenkins:jenkins /home/jenkins/

RUN usermod -a -G docker jenkins

VOLUME /var/lib/docker

EXPOSE 22

ADD run.sh /
CMD ["/run.sh"]
