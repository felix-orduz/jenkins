pipeline {
    agent any
        stages {
            stage("Compile") {
                steps {
                    sh "Compile"
                }
        }

        stage("Unit test") {
            steps {
                sh "unit test"
            }
        }
    }
}
