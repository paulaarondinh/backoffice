pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/paulaarondinh/backoffice'
            }
        }

        stage('Build') {
            steps {
                bat 'mvnw clean install'
            }
        }

        stage('Deploy') {
            steps {
                bat 'scp target/backoffice-0.0.1-SNAPSHOT.jar Administrator@192.168.70.123:/c/Deploy/backoffice.jar'
                bat 'ssh Administrator@192.168.70.123 "taskkill /F /IM java.exe || exit 0 && java -jar C:/Deploy/backoffice.jar"'
            }
        }
    }
}
