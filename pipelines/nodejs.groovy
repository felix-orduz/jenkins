pipeline {
    agent any
        stages {
            stage("Compile") {
                steps {
                    sh "echo 'Compile'"
                }
        }

        stage("Unit test") {
            steps {
                sh "echo 'unit test'"
            }
        }
    }
}
