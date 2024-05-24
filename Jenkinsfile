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
         dir("$WORKSPACE/e-commerce-trading-platform") {
                    // Chạy docker-compose từ thư mục đã chọn
                    sh 'docker-compose up -d'
                }
            }
        }
 
    }

}
