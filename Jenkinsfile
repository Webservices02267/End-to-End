pipeline {
    agent any

    stages {
        stage('Deploy') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './deploy.sh'
            }
        }
        
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './build.sh'
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
