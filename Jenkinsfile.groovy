pipeline {
    agent any
    stages {
        stage('Source') {// Получение кода;
            steps {
                // Получаем код из нашего Git-репозитория;
                git 'https://github.com/cherepakhin/rent.git'
            }
        }
        stage('test&package') {// Компиляция и выполнение модульного тестирования;
            steps {
                // Запуск Gradle для выполнения компиляции и модульного тестирования;
                sh "mvn clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.war'

                    // Отправка почты
                    mail to: 'vasi.che@gmail.com',
                            subject: "Успешная сборка: ${currentBuild.fullDisplayName}",
                            body: "Ссылка на результат ${env.BUILD_URL}"

                    // Деплой в Tomcat
//                    sh "cp target/demo-0.0.1-SNAPSHOT.war /home/vasi/java/tomcat8/webapps/demo.war"
                    sh "curl -T \"target/rent-0.0.1-SNAPSHOT.war\" " +
                            "\"http://deployer:pass@v.perm" +
                            ".ru:8080/manager/text/deploy?path=/rent&update=true\""
                }
            }
        }
    }
}
