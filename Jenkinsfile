pipeline {
    agent any
    options {
        skipDefaultCheckout(true)
    }
    stages {
        stage('Prepare Workspace') {
            steps {
                cleanWs()
                checkout scm
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            parallel {
                stage('Successful Login Test - Chrome') {
                    environment {
                        TEST_CASE = "LogInTests#testSuccessfulLogIn"
                    }
                    steps {
                        sh 'mvn test -Dtest=${TEST_CASE} -DseleniumGridUsername=${SELENIUM_GRID_USERNAME} -DseleniumGridAccessKey=${SELENIUM_GRID_ACCESSKEY} -DcorrectUsername=${CORRECT_USERNAME} -DcorrectPassword=${CORRECT_PASSWORD} -DbrowserType=chrome -DbrowserVersion=96.0'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Successful Login Test - Firefox') {
                    environment {
                        TEST_CASE = "LogInTests#testSuccessfulLogIn"
                    }
                    steps {
                        sh 'mvn test -Dtest=${TEST_CASE} -DseleniumGridUsername=${SELENIUM_GRID_USERNAME} -DseleniumGridAccessKey=${SELENIUM_GRID_ACCESSKEY} -DcorrectUsername=${CORRECT_USERNAME} -DcorrectPassword=${CORRECT_PASSWORD} -DbrowserType=firefox -DbrowserVersion=95.0'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Incorrect Username Test - Firefox') {
                    environment {
                        TEST_CASE = "LogInTests#testIncorrectUsername"
                    }
                    steps {
                        sh 'mvn test -Dtest=${TEST_CASE} -DseleniumGridUsername=${SELENIUM_GRID_USERNAME} -DseleniumGridAccessKey=${SELENIUM_GRID_ACCESSKEY} -DincorrectUsername=incorrectuser -DcorrectPassword=${CORRECT_PASSWORD} -DbrowserType=firefox -DbrowserVersion=95.0'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Incorrect Password Test - Firefox') {
                    environment {
                        TEST_CASE = "LogInTests#testIncorrectPassword"
                    }
                    steps {
                        sh 'mvn test -Dtest=${TEST_CASE} -DseleniumGridUsername=${SELENIUM_GRID_USERNAME} -DseleniumGridAccessKey=${SELENIUM_GRID_ACCESSKEY} -DcorrectUsername=${CORRECT_USERNAME} -DcorrectPassword=${CORRECT_PASSWORD} -DincorrectPassword=incorrectPassword -DbrowserType=firefox -DbrowserVersion=95.0'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Empty Fields Test - Firefox') {
                    environment {
                        TEST_CASE = "LogInTests#testEmptyFields"
                    }
                    steps {
                        sh 'mvn test -Dtest=${TEST_CASE} -DseleniumGridUsername=${SELENIUM_GRID_USERNAME} -DseleniumGridAccessKey=${SELENIUM_GRID_ACCESSKEY} -DbrowserType=firefox -DbrowserVersion=95.0'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Captcha Test - Chrome') {
                    environment {
                        TEST_CASE = "LogInTests#testLogInWithCaptcha"
                    }
                    steps {
                        sh 'mvn test -Dtest=${TEST_CASE} -DseleniumGridUsername=${SELENIUM_GRID_USERNAME} -DseleniumGridAccessKey=${SELENIUM_GRID_ACCESSKEY} -DusernameForCaptchaTest=${USERNAME_FOR_CAPTCHA_TEST} -DincorrectPassword=incorrectPassword'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Clean Workspace"
            cleanWs()
        }
    }
}