def pom
node {
    stage('Build') {

        git 'https://github.com/mjaulin/pic.git'

        pom = readMavenPom file: 'demo/pom.xml'
        echo "${pom.version}"
        echo "${pom.name}"


        def artifactory = Artifactory.server('artifactory')
        def artifactoryMaven = Artifactory.newMavenBuild()
        artifactoryMaven.tool = 'maven-3.3.9'
        artifactoryMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: artifactory
        artifactoryMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: artifactory
        def buildInfo = Artifactory.newBuildInfo()

        artifactoryMaven.run pom: 'demo/pom.xml', goals: 'clean install', buildInfo: buildInfo
        artifactory.publishBuildInfo buildInfo

        junit 'demo/target/surefire-reports/*.xml'
    }
}

parallel (
    "stream 1": {
        node {
            stage('Sonar') {
                def sonarqubeScannerHome = tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                sh "${sonarqubeScannerHome}/bin/sonar-scanner -Dsonar.host.url=http://sonar:9000/ -Dsonar.projectKey=demo -Dsonar.projectName=${pom.name} -Dsonar.projectVersion=${pom.version} -Dsonar.sources=demo/src"
            }
        }
    },
    "stream 2": {
        node ('docker-agent') {
            stage('Build image') {
                git 'https://github.com/mjaulin/pic.git'
        	    sh "mvn -Dartifact=fr.sii.pic:demo:${pom.version} -DoutputDirectory=demo dependency:copy"
        	    sh "docker build -t demo:${pom.version} demo/"
            }
            stage('Run image') {
                sh "docker run -d --name build-demo --net docker_default demo:${pom.version}"
            }
        }
    }
)

try {
    node {
        stage('Test image') {
            timeout(1000) {
                waitUntil {
                    def r = sh script: 'wget -q http://build-demo:8080 -O /dev/null', returnStatus: true
                    return (r == 0);
                }
            }
            sh "curl http://build-demo:8080"
        }
    }
} finally {
    node ("docker-agent") {
        sh "docker stop build-demo || true && docker rm build-demo || true"
    }
}

node ("docker-agent") {
    parallel (
        "stream 1": {
            stage('Stop image') {
            	sh "docker stop build-demo || true && docker rm build-demo || true"
			}
        },
        "stream 2": {
            stage('Deploy image') {
                sh "docker run -d --name demo -p 9898:8080 demo:${pom.version}"
            }
        }
    )
}
