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
    def img = docker.build('ns-travel-api')
    //input'Continue to next stage?'
    timeout(time:50,unit:'SECONDS'){
        response=inputmessage:'User',
        parameters:[string(defaultValue:'user1',
        description:'Enter Userid:',name:'userid')]
    }
       echo"Username = "+response


    stage 'Docker push'
    //docker.withRegistry('https://473293451041.dkr.ecr.eu-west-2.
    //amazonaws.com/ns-travel-api', 'ecr:eu-west-2:ns-travel-api') {
    //docker.image('ns-travel-api').push('latest')
    sh '''
        eval $(aws ecr get-login --no-include-email --region eu-west-2 | sed 's|https://||')
        docker push 473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api:latest
    ''' 
    //}
}
