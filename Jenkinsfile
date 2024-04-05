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
                sh 'docker image pull mysql:8.0'
                sh 'docker network create dev || echo "this network exists"'
                sh 'docker volume create hello '

        }
    }

}
