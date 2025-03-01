pipeline {
    agent any  // Runs on any available agent

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/pragmatictesters/selenium-sauce-demo.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'  // JUnit reports
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline Execution Completed'
        }
    }
}