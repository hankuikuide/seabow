pipeline {
    agent any
    environment {
        //发布组：k8s的namespace、gitlab的群组、harbor项目
        group = "crhms-dev"
    }

    stages {
        stage('Package') {
            steps {
                sh '/usr/local/maven/mvn clean install package -DskipTests'
            }
        }
    }
}