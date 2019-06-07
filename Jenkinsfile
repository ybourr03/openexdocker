#!/usr/bin/env groovy
import java.text.SimpleDateFormat
def dockerImage
// Java parameters
def jdk_version = "jdk8"
def maven_version = "Maven 3.5"
// Project parameters
def directory_name = "data-exchange/openex/" // TO FILL
def project_name = "demo-openex-docker" // TO FILL

def image_name = directory_name + project_name

pipeline {
    agent {
        node {
            label 'DOCKER'
        }
    }
    environment {
        version = readMavenPom().getVersion()
    }
    
      stage('Checkout') {
		steps {
			checkout scm
		}
	}
        //stage('Tests') {
        //    steps {
        //        script {
        //            try {
        //                withMaven(options: [artifactsPublisher(disabled: true)], jdk: jdk_version, maven: maven_version) {
        //                    sh 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent test -Pcoverage-per-test'
        //                }
        //            } catch (exc) {
        //                currentBuild.result = 'UNSTABLE'
        //            }
        //        }
        //    }
        //    post {
        //        always {
        //            junit '**/target/surefire-reports/*.xml'
        //        }
        //    }
        //}
        stage('Build jar') {
            steps {
                withMaven(options: [artifactsPublisher(disabled: true)], jdk: jdk_version, maven: maven_version) {
                    sh 'mvn clean'
                    sh 'mvn install -DskipTests'
                }
            }
        }
        stage('Sonar Analysis') {
            steps {
                script {
                    try {
                        withMaven(options: [artifactsPublisher(disabled: true)], jdk: jdk_version, maven: maven_version) {
                            sh 'mvn sonar:sonar'
                        }
                    } catch (exc) {
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
        stage('Publish artifacts') {
            steps {
                withMaven(options: [artifactsPublisher(disabled: true)], jdk: jdk_version, maven: maven_version) {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }
        stage('Build docker') {
            steps {
                script {
                    dockerImage = docker.build("registry-eu-local.subsidia.org/" + image_name + ":${version}", "-f Dockerfile .")
                }
            }
        }
        stage('Publish docker') {
            steps {
                script {
                    docker.withRegistry('https://registry-eu-local.subsidia.org', 'docked_preprod') {
                        dockerImage.push "${version}"
                        dockerImage.push 'latest'
                    }
                }
            }
        }
    }
}
