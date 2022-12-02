pipeline {
    
    agent any

    stages{

        stage("Cloning Git") {
            steps {
                script {
                    clone()
                }
            }
        }

        stage("Compile") {
            steps {
                script {
                    compiling()
                }
            }
        }

        stage("Unit Test") {
            steps {
                script {
                    unit_test()
                }
            }
        }

        stage("Pentest") {
            parallel {    // untuk menambahkan stage di dalam stage yang di jalankan
                stage("SAST"){
                    steps {
                        script {
                            sast()
                        }
                    }
                }
                stage("SCA"){
                    steps{
                        script{
                            sca()
                        }
                    }
                }
            }
        }

        stage("Package") {
            steps {
                script {
                    packaging()
                }
            }
        }
    }
}