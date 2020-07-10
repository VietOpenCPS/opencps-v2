env.OPENCPS_CACHE_VOLUME='gradle_cache_volume_test'

node {
    stage('Preparation & Checkout') {
        checkout changelog: true, poll: true, scm: [
                $class           : 'GitSCM',
                branches         : [[name: 'master']],
                extensions       : [[$class : 'CloneOption',
                                     shallow: false, timeout: 75],
                                     [$class: 'CleanBeforeCheckout']],
                userRemoteConfigs: [[url: 'https://github.com/VietOpenCPS/opencps-v2.git']]
        ]
    }
    stage('Update cache') {
        docker.image("openjdk:8u252-jdk").inside("-v ${env.OPENCPS_CACHE_VOLUME}:/root/.gradle") {
            sh './gradlew -v'
            sh './gradlew --no-daemon buildService'
            sh './gradlew --no-daemon build'
        }
    }
}
