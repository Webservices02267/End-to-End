pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './build.sh'
            }
        }
        
        stage('Deploy') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './deploy.sh'
            }
        }
        stage('Test') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './test.sh'
            }
        }
    }
    post {
        always {
            sh 'echo "pipeline complete"'
            sh "chmod +x -R ${env.WORKSPACE}"
            sh './stop.sh'
        }
    }
}
