class Constants {
    static final String MASTER_BRANCH = 'master'

    static final String QA_BUILD = 'Debug'
    static final String RELEASE_BUILD = 'Release'

    static final String INTERNAL_TRACK = 'internal'
    static final String RELEASE_TRACK = 'release'
}

def getBuildType() {
    switch (env.BRANCH_NAME) {
        case Constants.MASTER_BRANCH:
            return Constants.RELEASE_BUILD
        default:
            return Constants.QA_BUILD
    }
}

def getTrackType() {
    switch (env.BRANCH_NAME) {
        case Constants.MASTER_BRANCH:
            return Constants.RELEASE_TRACK
        default:
            return Constants.INTERNAL_TRACK
    }
}

def isDeployCandidate() {
    if ("${env.VARIANT}" == Constants.QA_BUILD) {
        println "-------- This 's a QA build, we stopping here --------"
        return false
    }

    return true
}

def getAppVersion() {
    Properties properties = new Properties()
    File propertiesFile = new File('version.properties')
    propertiesFile.withInputStream {
        properties.load(it)
    }
    return "${properties.MAJOR}.${properties.MINOR}.${properties.PATCH}"
}

pipeline {

    agent any

    stages {
        stage('Checking code style') {
            steps {
                echo "-------- Checking code style --------"
            }
        }
        stage('Run tests') {
            steps {
                echo "-------- Running Unit Tests --------"
                script {
                    sh "./gradlew test${VARIANT}UnitTest"
                }
            }
        }
        /* stage('Setup Tools') {
             withCredentials([file(credentialsId: 'mooviekey', variable: 'KEYFILE')]) {
                sh "cp \$KEYFILE app/keystore.jks"
                }
             } */
        stage('Generate bundle application') {
            when { expression { return isDeployCandidate() } }
            steps {
                sh "pwd"
                echo "-------- Generate App VERSION --------"
                script {
                    sh "./gradlew clean incrementVersion bundleRelease"
                }
            }
        }
        stage('Upload to Play Store') {
            steps {
                echo "-------- Starting MOOVIE upload to store --------"
                androidApkUpload googleCredentialsId:'Moovie Google Key',
                                 filesPattern: '**/build/outputs/**/*-release.aab',
                                 trackName: 'internal',
                                 rolloutPercentage: '100'
            }
        }
         /* stage('Cleanup Credential') {
              sh "rm app/keystore.jks"
         }  */
    }
}
