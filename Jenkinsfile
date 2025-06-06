pipeline {
    agent any
    tools {
        maven 'Maven3.6.3'  // Tên bạn vừa đặt ở bước trên
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/paulaarondinh/backoffice'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Deploy') {
            steps {
                sh 'scp target/backoffice-0.0.1-SNAPSHOT.jar Administrator@192.168.70.123:/c/Deploy/backoffice.jar'
                sh 'ssh Administrator@192.168.70.123 "taskkill /F /IM java.exe || exit 0 && java -jar C:/Deploy/backoffice.jar"'
            }
        }
    }
}
