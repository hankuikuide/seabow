pipeline {
    agent any
    environment {
        //发布组：k8s的namespace、gitlab的群组、harbor项目
        group = "crhms-dev"
    }

    stages {
        stage('Package') {
            steps {
                sh '/usr/maven/bin/mvn clean install package -DskipTests'
            }
        }
    }
}