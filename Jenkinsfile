pipeline {
    agent any

    tools {
        jdk 'MyJava'        // Configured under Manage Jenkins > Tools
        maven 'MyMaven'    // Configured under Manage Jenkins > Tools
        //allure 'MyAllure'  // Configured under Manage Jenkins > Tools (Allure Commandline)
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
            echo 'Generating and publishing Allure Report via Maven...'
            
            // Generate Allure HTML report using Maven plugin
            bat 'mvn allure:report'

            // Publish generated HTML site
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/site/allure-maven-plugin',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
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