node {

    NSNEWKEY = sh (
        script: "aws secretsmanager get-secret-value --secret-id nskey | jq -r '.SecretString'",
        returnStdout: true
    )

    stage ('Checkout'){
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
    }

    stage ('Build') {
    //NSNEWKEY=${sh "'aws secretsmanager get-secret-value --secret-id nskey | jq -r '.SecretString'"}
    APPVERSION= sh (
        script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
        returnStdout: true
    )
    
    echo "${APPVERSION}"

    sh 'mvn clean package'
    input'Continue to next stage?'
    }

    stage ('Docker push') {
    def img = docker.build('473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod')
    //eval $(aws ecr get-login --no-include-email --region eu-west-2 | sed 's|https://||')
    docker.withRegistry('https://473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod', 'ecr:eu-west-2:ns-travel-api-prod') {
      docker.image('473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod').push('latest')
    }
    }

    stage ('Deploy'){
    ///usr/local/bin/kubectl apply -f /var/lib/jenkins/.kube/aws-auth-cm.yaml
    sh '''
         export KUBECONFIG=$KUBECONFIG:/var/lib/jenkins/.kube/config
         aws eks --region eu-west-2 update-kubeconfig --name travelApp-EKS-CLUSTER
         /usr/local/bin/kubectl version
         /usr/local/bin/kubectl get nodes
         /usr/local/bin/helm upgrade --install helm-ta-prod ./helm
    '''
    //    /usr/local/bin/helm upgrade --install helm-ta-prod --set selectApp=ns-travel-api ./helm-ta-prod
    }
}
