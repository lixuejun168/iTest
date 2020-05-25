pipeline {
    agent any

    tools {
        maven 'mvn-3.3.3'
    }

    stages {
        stage('Build'){
            withCredentials([sshUserPrivateKey(
                keyFileVariable:"key",
                credentialsId:"169"
            )])
            steps {
                git([url: 'git@github.com:lixuejun168/iTest.git', branch: 'master'])
                sh 'printenv'
                sh "pwd"
                sh "mvn clean install test"
            }

            post {
                always {
                    junit testResults: "**/target/surefire-reports/*.xml"
                }
            }
        }
        
    }

    
}