pipeline {
    agent any
    

        
        stage('Run Docker Compose') {
            steps {
                script {
                    // Change directory to the cloned repository
                    dir('e-commerce-trading-platform') {
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
