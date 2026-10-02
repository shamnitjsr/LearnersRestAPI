pipeline {
    agent any

    tools {
        jdk 'MyJava'        // Configured under Manage Jenkins > Tools
        maven 'MyMaven'    // Configured under Manage Jenkins > Tools
        allure 'MyAllure'  // Configured under Manage Jenkins > Tools (Allure Commandline)
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
                bat 'java -version'
                bat 'mvn -version'
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Executing Rest Assured API automation tests...'
                // Ensures build continues to the Report stage even if test assertions fail
                bat 'mvn -B test -DfailIfNoTests=false'
            }
        }

        stage('Report') {
            steps {
                echo 'Publishing API automation test results...'

                // 1. Publish standard JUnit results in Jenkins
                junit(
                    testResults: '**/target/surefire-reports/*.xml',
                    allowEmptyResults: true
                )

                // 2. Generate and publish Allure Report
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])

                // 3. Archive raw reports as artifacts
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