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


    //sh '''
    //    eval $(aws ecr get-login --no-include-email --region eu-west-2 | sed 's|https://||')
    //    docker push 473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod:latest
    //'''

    docker.withRegistry('https://473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod', 'ecr:eu-west-2:ecr:eu-west-2:ns-travel-api-prod') {
      docker.image('473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod').push('latest')
    }

    //export PATH=/usr/local/bin/helm:$PATH
    ///usr/local/bin/helm init

    sh '''
        /usr/local/bin/helm version
        kubectl version --short
        /usr/local/bin/helm upgrade helm-ta-prod --set selectApp=ns-travel-api ./helm-ta-prod
    '''
}
