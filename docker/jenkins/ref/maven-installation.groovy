#!groovy
import jenkins.model.*

def instance = Jenkins.instance

println "---> creating maven installation"

def descriptor = instance.getExtensionList(hudson.tasks.Maven.DescriptorImpl.class)[0];
def installations = (descriptor.installations as List);

installations.add(new hudson.tasks.Maven.MavenInstallation("maven-3.3.9", null, [new hudson.tools.InstallSourceProperty([new hudson.tasks.Maven.MavenInstaller("3.3.9")])]));

descriptor.installations = installations
descriptor.save()
