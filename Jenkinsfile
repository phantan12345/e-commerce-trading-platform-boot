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
                sh 'docker-compose -f /path/to/docker-compose.yml up'
            }
        }
 
    }

}
