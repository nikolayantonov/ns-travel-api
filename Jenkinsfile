node {
    stage 'Clone repository'
    checkout([
        $class: 'GitSCM',
        branches: [
            [name: '*/master']
        ],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [
            [url: 'https://github.com/nikolayantonov/ns-travel-api.git']
        ]
    ])

    stage 'Build & package'
    sh 'mvn clean package'
    //def img = docker.build('ns-travel-api-prod')
    def img = docker.build('473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod')
    input'Continue to next stage?'

    stage 'Docker push'
    //eval $(aws ecr get-login --no-include-email --region eu-west-2 | sed 's|https://||')
    sh '''
        docker push 473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod:latest
    '''
    //helm upgrade helm-ta-prod ./helm-ta-prod
}
