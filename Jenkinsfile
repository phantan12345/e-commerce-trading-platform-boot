pipeline {
    agent any
    
    stages {
        stage('Build and Deploy') {
            steps {
                // Di chuyển đến thư mục chứa file docker-compose.yml
                dir("$WORKSPACE/e-commerce-trading-platform") {
                    sh 'echo $PATH'
                    sh 'docker-compose -v'
                    sh "docker-compose up -d"
                }
            }
        }
    }
}
