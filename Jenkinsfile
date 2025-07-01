pipeline {
    agent any
    tools {
        maven 'Maven 3.9.6'  // Tên bạn vừa đặt ở bước trên
        jdk 'jdk21'

    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/paulaarondinh/backoffice'
            }
        }

        stage('Clean') {
            steps {
               sh '''
                    echo "Stopping old app..."
                    pkill -f 'backoffice-0.0.1-SNAPSHOT.jar' || true
        
                    echo "Fixing permission..."
                    chown -R $(whoami):$(whoami) target || true
                    chmod -R u+w target || true
        
                    echo "Cleaning..."
                    rm -rf target || true
                '''
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
              withCredentials([sshUserPrivateKey(credentialsId: 'MySSHKey', keyFileVariable: 'KEY')]) {
                    sh '''
                        echo "Uploading file to Windows via SFTP..."
                        sftp -oStrictHostKeyChecking=no -i $KEY Administrator@192.168.70.123 <<EOF
                        put target/backoffice-0.0.1-SNAPSHOT.jar /C:/Users/Administrator/Deploy/backoffice.jar
                        bye
                        EOF

                        echo "Running .jar on Windows using SSH..."
                        ssh -oStrictHostKeyChecking=no -i $KEY Administrator@192.168.70.123 "java -jar C:/Users/Administrator/Deploy/backoffice.jar > C:/Users/Administrator/Deploy/run.log 2>&1 &"
                    '''
              }     
            }
        }
    }
}
