@Library('jenkins-shared-lib')
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