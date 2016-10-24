#!groovy
import jenkins.model.*
import hudson.model.*

import com.cloudbees.plugins.credentials.Credentials
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.SystemCredentialsProvider

def system_creds = SystemCredentialsProvider.instance
Map<Domain, List<Credentials>> domainCredentialsMap = system_creds.getDomainCredentialsMap()

println "---> creating credentials"

def user = new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "jenkins", null, "jenkins", "jenkins")

domainCredentialsMap[Domain.global()].add(user)

system_creds.save()
