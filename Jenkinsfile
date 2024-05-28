pipeline {
    agent any
    
    stages {
        stage('Build and Deploy') {
            steps {
                    sh 'docker compose -f docker-compose.yml up -d'
                
            }
        }
    }
}
