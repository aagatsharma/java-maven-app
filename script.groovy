def buildJar(){
    echo "Build java jar application..."
    sh 'mvn package'
}

def buildImage(){
    echo "Building docker image..."
    withCredentials([usernamePassword(credentialsId:'docker-creds',usernameVariable:'USER',passwordVariable:'PWD')]){
        sh 'docker build -t azelmazel/java-maven:1.0 .'
        sh "echo $PWD | docker login -u $USER --password-stdin"
        sh 'docker push azelmazel/java-maven:1.0'
    }
}

def deployApp(){
    echo "Deploying java-maven application"
}

return this