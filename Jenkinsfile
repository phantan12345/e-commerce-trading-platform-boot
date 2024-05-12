pipeline {


 
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }



        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'cd e-commerce-trading-platform'
                sh 'docker compose down'
                sh 'docker compose up -d'
          
            }
        }
 
    }

}