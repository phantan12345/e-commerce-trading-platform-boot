pipeline {
    agent any
    
    stages {
        stage('Clone Repository') {
            steps {
                // Clone the Git repository
                git url: 'https://github.com/phantan12345/e-commerce-trading-platform-boot.git', branch: 'main'
            }
        }
        
        stage('Run Docker Compose') {
            steps {
                script {
                    // Change directory to the correct path where docker-compose.yml is located
                    dir('e-commerce-trading-platform') {
                        // Check if docker-compose is installed
                        sh 'docker-compose --version'
                        
                        // Run Docker Compose
                        sh 'docker-compose -f docker-compose.yml up -d'
                    }
                }
            }
        }
    }
    
    post {
        always {
            // Cleanup
            cleanWs()
        }
    }
}
