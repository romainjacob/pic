#!groovy
import jenkins.model.*
import org.jfrog.hudson.*

def instance = Jenkins.instance

println "---> creating artifactory installation"

def descriptor = instance.getDescriptor("org.jfrog.hudson.ArtifactoryBuilder")

def credentials = new CredentialsConfig("jenkins", "jenkins", "jenkins", false)
def server = [new ArtifactoryServer(
  "artifactory",
  "http://artifactory:8081/artifactory",
  credentials,
  credentials,
  300,
  false )
]

descriptor.setArtifactoryServers(server)

descriptor.save()
