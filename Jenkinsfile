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
    def img = docker.build('473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod')
    input'Continue to next stage?'

    stage 'Docker push'
    //    eval $(aws ecr get-login --no-include-email --region eu-west-2 | sed 's|https://||')


    docker.withRegistry('https://473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod', 'ecr:eu-west-2:ns-travel-api-prod') {
      docker.image('473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod').push('latest')
    }


    ///usr/local/bin/kubectl apply -f /var/lib/jenkins/.kube/aws-auth-cm.yaml
    sh '''
         export KUBECONFIG=$KUBECONFIG:/var/lib/jenkins/.kube/config
         aws eks --region eu-west-2 update-kubeconfig --name travelApp-EKS-CLUSTER
         /usr/local/bin/kubectl version
         /usr/local/bin/kubectl get nodes
         /usr/local/bin/helm upgrade --install helm-ta-prod ./helm-ta
    '''
    //    /usr/local/bin/helm upgrade --install helm-ta-prod --set selectApp=ns-travel-api ./helm-ta-prod
}
