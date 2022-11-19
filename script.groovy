def buildJar() {
    echo "building the application..."
    sh "mvn package"
} 

def buildImage(String imageName) {
    echo "building the docker images......"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $imageName"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this