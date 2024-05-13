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
                sh 'cp e-commerce-trading-platform'
                sh 'docker-compose up -d'
            }
        }
        
 
    }

}
