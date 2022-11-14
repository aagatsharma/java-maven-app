pipeline{
    agent any
    tools{
        maven 'Maven'
    }
    stages{
        stage("build jar"){
            steps{
                script{
                    echo "Build java jar application..."
                    sh 'mvn package'
                }
            }
        }
        stage("build image"){
            steps{
                script{
                    echo "Building docker image..."
                    withCredentials([usernamePassword(credentials:'docker-creds',usernameVariable:'USER',passwordVariable:'PWD')]){
                        sh 'docker build -t azelmazel/java-maven:1.0 .'
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh 'docker push azelmazel/java-maven:1.0'
                    }
                }
            }
        }
        stage("deploy application"){
            steps{
                script{
                    echo "Deploying java-maven application"
                }
            }
        }
    }
}