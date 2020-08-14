env.OPENCPS_CACHE_VOLUME='gradle_cache_volume'

if (env.CHANGE_ID) {
    if(env.CHANGE_TARGET=='master'){
        buildPullRequest()
    }
} else {
    if(env.BRANCH_NAME=='master' || env.BRANCH_NAME=='CI-Test'){
        buildPushCommit()
    }
}

def buildPushCommit() {
    node() {
        stage('Checkout Source Code') {
            checkoutSCM()
        }
        cleanBuildTest()

    }
}

def buildPullRequest() {
    node() {
        stage('Checkout Source Code') {
            checkoutSCM()
        }
        cleanBuildTest()

    }
}

def cleanBuildTest(){
    docker.image("openjdk:8u252-jdk").inside("-v ${env.OPENCPS_CACHE_VOLUME}:/root/gradle_cache") {
        sh 'mkdir -p /root/.gradle/ && cp -ar /root/gradle_cache/* /root/.gradle/'
        stage('Compile & build'){
            sh './gradlew -v'
            sh './gradlew --no-daemon clean'
            sh './gradlew --no-daemon buildService'
            sh './gradlew --no-daemon build'
        }
        stage('Test'){
            try {
                sh './gradlew --no-daemon test --profile'
            } catch (err) {
                echo "${err}"
                throw err
            } finally {
                def summary = junit testResults: 'modules/**/TEST-*.xml'
                if (env.CHANGE_ID) {
                    pullRequest.comment(
                        "${env.GIT_COMMIT_ID}:  *Test Summary* - " +
                        "Total: ${summary.totalCount}, Failures: ${summary.failCount}, " +
                        "Skipped: ${summary.skipCount}, Passed: ${summary.passCount}. " +
                        "[Details Report...](${env.JOB_URL}${BUILD_NUMBER}/testReport/)"
                    )
                }
            }
        }
        stage('Code Coverage Report'){
            sh './gradlew --no-daemon jacocoTestReport jacocoRootReport'
            withCredentials([string(credentialsId: 'codecov-token', variable: 'CODECOV_TOKEN')]) {
                sh "curl -s https://codecov.io/bash | bash -s - -t ${CODECOV_TOKEN}"
            }
        }
    }
}

def checkoutSCM() {
    checkout([
        $class: 'GitSCM',
        branches: scm.branches,
        doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
        extensions       : [[$class : 'CloneOption', shallow: false, timeout: 120],
                            [$class: 'CleanBeforeCheckout']],
        userRemoteConfigs: scm.userRemoteConfigs
    ])
    GIT_REVISION = sh(script: 'git rev-parse HEAD', returnStdout: true)
    env.GIT_COMMIT_ID = GIT_REVISION.substring(0, 7)
}
