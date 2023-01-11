// library identifier:'jenkins-shared-library@main', retriever:modernSCM(
//     [$class:'GitSCMSource',remote:'https://github.com/aagatsharma/jenkins-shared-library.git',credentialsId:'github-creds']
// )
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
        stage("Increment version"){
            steps{
                script{
                    gv.incrementapp()
                }
            }
        }
        stage("build app"){
            steps{
                script{
                    gv.buildJar()
                }
            }
        }
        stage("build image"){
            steps{
                script{
                    gv.buildImage()
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
        // stage("Commit Version Change"){
        //     steps{
        //         script{
                        // git clone www.google.com 
        //         }
        //     }
        // }
    }
}