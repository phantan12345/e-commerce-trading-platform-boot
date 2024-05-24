pipeline {
    agent any
    
    stages {
        stage('Build and Deploy') {
            steps {
                // Di chuyển đến thư mục chứa file docker-compose.yml
                dir('/path/to/e-commerce-trading-platform') {
                    // Chạy lệnh docker-compose up -d
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
