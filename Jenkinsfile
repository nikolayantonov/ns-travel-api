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
            [url: 'https://github.com/slychops/ns-travel-api']
        ]
    ])

    stage 'Build & package'
    def img = docker.build('ns-travel-api')

    stage 'Docker push'
    docker.withRegistry('https://473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api', 'ecr:eu-west-2:ns-travel-api') {
      docker.image('ns-travel-api').push('latest')
    }
}
