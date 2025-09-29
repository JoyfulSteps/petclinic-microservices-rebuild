pipeline {
    agent any

    tools {
            maven 'maven-3.9.6'
    }

    environment {
        DOCKER_HUB_USERNAME = 'laputacul'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build All JARs') {
            steps {
                echo 'Building the application JAR files...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build, Push & Deploy All Services') {
            parallel {
                stage('customers-service') {
                    steps {
                        script {
                            processService('customers-service')
                        }
                    }
                }

                stage('vets-service') {
                    steps {
                        script {
                            processService('vets-service')
                        }
                    }
                }

                stage('visits-service') {
                    steps {
                        script {
                            processService('visits-service')
                        }
                    }
                }

                stage('api-gateway') {
                    steps {
                        script {
                            processService('api-gateway')
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                echo 'Pipeline finished.'
                sh 'docker logout'
            }
            cleanWs()
        }
    }
}


void processService(String serviceName) {
    def imageName = "${env.DOCKER_HUB_USERNAME}/${serviceName}:latest"
    def deploymentName = "${serviceName}-deployment"

    stage("Build Image: ${serviceName}") {
        echo "Building for ${serviceName}..."
        sh "docker build -f ./${serviceName}/Dockerfile -t ${imageName} ."
    }

    stage("Push Image: ${serviceName}") {
        echo "Pushing image ${serviceName}"

        withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
            sh "docker push ${imageName}"
        }
    }

    stage("Deploy to K8s: ${serviceName}") {
        echo "Deploying ${deploymentName} to Kubernetes..."

        withKubeConfig([credentialsId: 'kubeconfig']) {
            sh "kubectl set image deployment/${deploymentName} ${serviceName}=${imageName}"
            sh "kubectl rollout status deployment/${deploymentName}"
        }
    }
}