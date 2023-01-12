def incrementapp() {
    echo 'Incrementing app version'
    sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
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
    sh "docker build -t azelmazel/java-maven:${IMAGE_NAME} ."
    dockerLogin()
    sh "docker push azelmazel/java-maven:${IMAGE_NAME}"
}

def gitbump(){
    withCredentials([usernamePassword(credentialsId: 'github-creds', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'git config --global user.email "jenkins@jenkins.com"'
        sh 'git config --global user.name "jenkins"'
        sh "git remote set-url origin https://${USER}:${PASS}@github.com/aagatsharma/java-maven-app.git"
        sh 'git add .'
        sh 'git commit -m "CI:Version Bump"'
        sh 'git push'
    }
}

def deployApp() {
    echo 'deploying the application to the AWS...'
} 

return this