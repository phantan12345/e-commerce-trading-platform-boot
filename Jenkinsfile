pipeline {
    agent any
    
    stages {
        stage('Build and Deploy') {
            steps {
                // Di chuyển đến thư mục chứa file docker-compose.yml
                dir("/e-commerce-trading-platform") {
                    // Chạy lệnh docker-compose
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
