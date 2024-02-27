pipeline {

    agent any

    tools { 
        maven 'my-maven' 
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root')
    }
    stages {

 

        stage('Deploy Spring Boot to DEV') {
            steps {
             

                sh 'docker-compose up -d'
            }
        }
 
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}
