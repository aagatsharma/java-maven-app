def incrementapp() {
    echo 'Incrementing app version'
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml')=~ '<version>(.+)</version>'
    def vision = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}


def buildJar() {
    echo "building the application..."
    sh "mvn clean package"
}

def dockerLogin(){
    sh 'echo Logging in to docker'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "echo $PASS | docker login -u $USER --password-stdin"
    }
}

def buildImage() {
    echo "building the docker images......"
    sh 'docker build -t azelmazel/java-maven:${IMAGE_NAME} .'
    dockerLogin()
    sh 'docker push azelmazel/java-maven:${IMAGE_NAME}'
} 

def deployApp() {
    echo 'deploying the application...'
} 


return this