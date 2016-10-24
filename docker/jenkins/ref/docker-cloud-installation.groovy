#!groovy
import jenkins.model.*
import hudson.model.*

import com.nirima.jenkins.plugins.docker.*

def instance = Jenkins.instance

println "---> creating docker cloud"

def dockerClouds = [
  new DockerCloud("docker-agent", null, "tcp://172.17.0.1:4243", 100, 15, 15, "jenkins", null, null)
]

instance.clouds.addAll(dockerClouds)
