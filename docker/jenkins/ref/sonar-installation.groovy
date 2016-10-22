#!groovy
import jenkins.model.*
import hudson.plugins.sonar.*
import hudson.tools.*

def instance = Jenkins.instance
def descriptor = instance.getDescriptor("hudson.plugins.sonar.SonarRunnerInstallation")

def sonar = new SonarRunnerInstallation("sonar", null, [new InstallSourceProperty([new SonarRunnerInstaller("2.8")])])

descriptor.setInstallations(sonar)
descriptor.save()
