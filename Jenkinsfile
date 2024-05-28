pipeline {
    agent any
    
    stages {
        stage('Build and Deploy') {
            steps {
                    sh 'docker compose -f e-commerce-trading-platform/docker-compose.yml up -d'
                
            }
        }
    }
}
