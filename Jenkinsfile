pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/surajpandey95/employee-service.git'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage ("Package") {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarServer') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=employee-service
                    '''
                }
            }
        }
        stage("Quality Gates") {
            steps{
                timeout(time:5,unit:'MINUTES'){
                    waitForQualityGate abortPipelinie: true
                }
            }

        }
        stage('Docker Build'){
            steps{
                sh 'docker build -t employee-service:v1 .'
            }
        }
        
    }
}