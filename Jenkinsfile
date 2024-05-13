pipeline {

    agent any

 
    stages {
    stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build with Maven') {
            steps {
                sh 'java -version'
            }
        }



        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker compose -f docker-compose.yml up -d'
                sh 'docker-compose up -d'
            }
        }
        
 
    }

}
