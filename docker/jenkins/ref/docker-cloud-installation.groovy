#!groovy
import jenkins.model.*
import hudson.model.*

import com.nirima.jenkins.plugins.docker.*
import com.nirima.jenkins.plugins.docker.launcher.*

def instance = Jenkins.instance

println "---> creating docker cloud"

def templates = []
def dockerTemplateBase = new DockerTemplateBase(
              "jenkins-slave", // image
              null, // dnsString
              "docker_default", // network
              null, // dockerCommand
              "/var/run/docker.sock:/var/run/docker.sock", // volumesString
              null, // volumesFromString
              null, // environmentsString
              null, // lxcConfString
              null, // hostname
              null, // memoryLimit
              null, // memorySwap
              null, // cpuShares
              null, // bindPorts
              false, // bindAllPorts
              false, // privileged
              false, // tty
              null, // macAddress
            )
def dockerTemplate = new DockerTemplate(
              dockerTemplateBase, // dockerTemplateBase
              "docker-agent", // labelString
              "/home/jenkins", // remoteFs
              "/var/jenkins_home", // remoteFsMapping
              "1" // instanceCapStr
            )
def dockerComputerSSHLauncher = new DockerComputerSSHLauncher(
                new hudson.plugins.sshslaves.SSHConnector(
                  22, "jenkins", null, null, null, null, null )
            )
dockerTemplate.setLauncher(dockerComputerSSHLauncher)
templates.add(dockerTemplate)

def dockerClouds = [
  new DockerCloud("docker-agent", templates, "tcp://172.17.0.1:4243", 100, 15, 15, "jenkins", null)
]

instance.clouds.addAll(dockerClouds)
