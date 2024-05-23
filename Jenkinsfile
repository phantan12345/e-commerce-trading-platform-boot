pipeline {

    agent any

 
    stages {

        stage('Build with Maven') {
            steps {
                sh 'java -version'
            }
        }



        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                dir('e-commerce-trading-platform') {
                       sh 'docker-compose -f docker-compose.yml up -d'
                    }                
                
            }
        }
 
    }

}
