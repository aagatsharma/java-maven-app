library identifier:'jenkins-shared-library@main', retriever:modernSCM(
    [$class:'GitSCMSource',remote:'https://github.com/aagatsharma/jenkins-shared-library.git',credentialsId:'github-creds']
)
def gv

pipeline{
    agent any
    tools{
        maven 'Maven'
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar"){
            steps{
                script{
                    buildJar()
                }
            }
        }
        stage("build image"){
            steps{
                script{
                    buildImage ('azelmazel/java-maven:3.0')
                }
            }
        }
        stage("deploy application"){
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}