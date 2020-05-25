pipeline {
    agent any

    tools {
        maven 'mvn-3.3.3'
    }

    stages {
        
        stage('Build'){

            steps {

                git([url: 'git@github.com:lixuejun168/iTest.git', branch: 'master'])
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