pipeline {

    // Jenkins agent where the API tests will execute
    agent any

    // Maven and JDK must be configured in:
    // Manage Jenkins -> Tools
    tools {
        jdk 'JDK-21'
        maven 'Maven-3.10'
    }

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        disableConcurrentBuilds()
    }

    environment {
        // Change this to your GitHub repository
        REPO_URL = 'https://github.com/shamnitjsr/LearnersRestAPI.git'
        BRANCH   = 'main'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out Rest Assured project from GitHub...'

                // Clone source code from GitHub
                checkout scmGit(
                    branches: [[name: "${BRANCH}"]],
                    userRemoteConfigs: [[url: "${REPO_URL}"]]
                )
            }
        }

        stage('Build') {
            steps {
                echo 'Building Maven project...'

                // Verify Java and Maven versions
                sh 'java -version'
                sh 'mvn -version'

                // Compile project and download dependencies
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Executing Rest Assured API automation tests...'

                // Execute TestNG/JUnit API tests
                sh 'mvn -B clean test'
            }
        }

        stage('Report') {
            steps {
                echo 'Publishing API automation test results...'

                // Publish Maven Surefire XML results
                junit(
                    testResults: '**/target/surefire-reports/*.xml',
                    allowEmptyResults: false
                )

                // Keep reports available as Jenkins artifacts
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
