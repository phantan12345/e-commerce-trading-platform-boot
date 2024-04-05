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
                sh 'echo "this container does not exist"'
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
