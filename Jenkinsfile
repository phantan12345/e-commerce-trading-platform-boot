pipeline {
    agent any
     tools { 
        maven 'my-maven' 
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root-login')
    }
    stages {
        stage('Build') {
            steps {
         
                echo 'Deploying and cleaning'

                sh 'docker volume create hello '

        }
    }

}
