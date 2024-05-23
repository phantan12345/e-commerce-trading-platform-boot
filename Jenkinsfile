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
                   script {
                    // Change directory to the cloned repository
                    dir('e-commerce-trading-platform') {
                        // Run Docker Compose
                    sh 'docker-compose up -d'
                    }
                
            }
        }
        
 
    }

}
