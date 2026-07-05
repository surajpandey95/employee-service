pipeline {
    agent any

    tools {

        jdk 'JDK21'
        maven 'Maven3'

    }

    stages {

        stage ('Checkout') {
            steps {
                git branch: main, url: https://github.com/surajpandey95/employee-service.git
            }
        }
    stage ('Compile') {
        steps {

            sh 'mvn test'
        }
    }

    stage ('Package') {
        steps {
            sh 'mvn clean package'

        }
    }

    stages ('Archive Artifact') {
        steps {
            archiveArtifacts archifacts: 'target/*.jar', fingerprint: true
        
        }

    }

    }
}

