node {
    def mvnHome
    def version
    def artifactory
    def buildInfo
    stage('Build') {

        git 'https://github.com/mjaulin/pic.git'

        def pom = readMavenPom file: 'demo/pom.xml'
        echo "${pom.version}"


        artifactory = Artifactory.server('artifactory')
        def artifactoryMaven = Artifactory.newMavenBuild()
        artifactoryMaven.tool = 'maven-3.3.9'
        artifactoryMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: artifactory
        artifactoryMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: artifactory
        buildInfo = Artifactory.newBuildInfo()

        artifactoryMaven.run pom: 'demo/pom.xml', goals: 'clean install', buildInfo: buildInfo
        artifactory.publishBuildInfo buildInfo

        junit 'demo/target/surefire-reports/*.xml'
    }

    stage('Sonar') {
      def sonarqubeScannerHome = tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
      sh "${sonarqubeScannerHome}/bin/sonar-scanner -e -Dsonar.host.url=http://sonar:9000/"
    }
}
