pipeline {
    agent any

    tools {
        jdk 'MyJava'       // Use your configured Java name
        maven 'MyMaven'   // Use your configured Maven name
    }

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        disableConcurrentBuilds()
    }

    environment {
        REPO_URL = 'https://github.com/shamnitjsr/LearnersRestAPI.git'
        BRANCH   = 'main'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out Rest Assured project from GitHub...'
                checkout scmGit(
                    branches: [[name: "${BRANCH}"]],
                    userRemoteConfigs: [[url: "${REPO_URL}"]]
                )
            }
        }

        stage('Build') {
            steps {
                echo 'Building Maven project...'
                // Changed 'sh' to 'bat' for Windows
                bat 'java -version'
                bat 'mvn -version'
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Executing Rest Assured API automation tests...'
                // Changed 'sh' to 'bat' for Windows
                bat 'mvn -B clean test'
            }
        }

        stage('Report') {
            steps {
                echo 'Publishing API automation test results...'
                junit(
                    testResults: '**/target/surefire-reports/*.xml',
                    allowEmptyResults: false
                )
                archiveArtifacts(
                    artifacts: '**/target/surefire-reports/**/*',
                    allowEmptyArchive: true
                )
            }
        }
    }

    post {
        success {
            echo 'API automation execution completed successfully.'
        }
        failure {
            echo 'API automation execution FAILED. Check the Test and Report stages.'
        }
        always {
            echo 'Jenkins API automation pipeline execution completed.'
        }
    }
}