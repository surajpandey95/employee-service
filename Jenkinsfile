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
                    waitForQualityGate abortPipeline: true
                }
            }

        }
                stage('Docker Login') {
            steps{
                withCredentials ([usernamePassword (credentialsId: 'dockerhub-creds',usernameVariable: 'DOCKER_USER',passwordVariable: 'DOCKER_PASS')]) {
                sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
            }
          }
        }
        stage('Docker Build'){
            steps{
                sh 'docker build --no-cache -t surajdemo1/employee-service:${BUILD_NUMBER} .'
            }
        }
        stage('Cleanup'){
            steps{
                sh ''' docker system prune -af ||true docker volume prune -f || true rm -rf ~/.cache/trivy || true '''
            }
        }
         stage('Trivy Scan') {
    steps {
        sh '''
        trivy image --exit-code 1 --severity CRITICAL  employee-service:${BUILD_NUMBER}
        '''
    }
}
stage('Docker Push') {
            steps {
                sh 'docker push surajdemo1/employee-service:${BUILD_NUMBER}'
            }
        }
    }
}