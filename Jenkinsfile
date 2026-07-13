pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SCANNER_HOME = tool 'sonar-scanner'
            }
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=DevOpsproject -Dsonar.projectName="DevOpsproject"'
                }
            }
        }

        stage('Dockerize') {
            steps {
                script {
                    def appImage = docker.build("mycalculator:${env.BUILD_ID}")
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
    }
}
