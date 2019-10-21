#
# DevOps Documentation
#

# Requirements
In 2 teams, each consisting of 3 BackEnd and 2 DevOps engineers, create a travel application using a continuous integration/continuous deployment (CI/CD) zero downtime deployment (ZDD). The travel application must consist of an API service (no user-interface required) that, given some information (like date & time, from and to locations), gets the available routes from the ns.nl API. Additionally, each group is challenged to also obtain the weather for the destination and arrival time from some public, open & free REST API. The travel application, the CI/CD pipelines, and the deployed cluster must make proper use of or choose a proper implementation for:

* Deploy to a Kubernetes cluster on AWS
* Proper use of Security Groups
* Redirect http requests automatically to https
* Secrets (like API keys, https keys & certificates)
* Auto-Scaling 
* Manual approval before deployment to prod (or staging for blue/green) 
* Bonus points for implementing canary deployment 
* Bonus points for Blue/Green deployment with manual approval 
* Bonus points for custom auto scaling metrics
* Bonus points for custom health checks
* Logging (events, audits, etc.) 
* Monitoring (including the most significant business-related metrics)
* Unit tests (integrated in CI/CD) 
* Integration tests (as a separate part of CI and/or CD) 
* Source code repository with version control 
* Spring Boot REST implementation using MVC design with controller(s) and TDD

# Deploy to a Kubernetes cluster on AWS

###### Install eksctl and kubectl
* [EKSCTL installation](https://docs.aws.amazon.com/eks/latest/userguide/getting-started-eksctl.html)
* [KUBECTL](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
* [KUBECTL AWS INSTALLATION](https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html)
* Upload Keys to Server

```bash
$ scp -i ~/Desktop/kleidia/iakovoschallenge.pem ~/Desktop/kleidia/iakovoschallenge.pem ec2-user@35.177.167.203:~/.ssh
```

* [EKSCTL DOCS](https://eksctl.io/introduction/getting-started/)


###### AWS CLI
* [GitHub Repository](https://github.com/slychops/ns-travel-api.git)
* Setup aws cli with proper credentials
* Create SSH Keys for EC2 instances
* Create Cluster with EKSCTL

```bash
eksctl create cluster --name=travelApp-EKS-CLUSTER --nodes=1 --region=eu-west-2 --node-type=t3.medium \
--node-security-groups sg-b79231de \
--ssh-public-key=iakovoschallenge \
--tags "Owner=IakovosBelonias, Task=createdByIakovos Travel App Challenge"
```

###### Install and Setup Jenkins on EC2 Instance

* **NOTICE** [WATCH FOR MAVEN INSTALLATION](https://docs.aws.amazon.com/neptune/latest/userguide/iam-auth-connect-prerq.html)
* **NOTICE** FOR DOCKER SUDO permissions you might need to restart

```bash
$ sudo yum update -y
$ sudo yum remove java-1.7.0-openjdk -y
$ sudo yum install git java-1.8.0 maven jq -y
$ sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
$ sudo rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key
$ sudo yum install jenkins -y
$ sudo yum install docker -y
$ sudo usermod -aG docker ec2-user !!!!
$ sudo usermod -a -G docker jenkins
$ sudo service docker restart
$ sudo service jenkins restart
$ sudo $(aws ecr get-login --no-include-email --region eu-west-2)
$ eval $(aws ecr get-login --no-include-email --region eu-west-2 | sed 's|https://||')   !!!!!!!
$ sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```


* Once you loggedin instal **Amazon ECR Plugin(GOOGLE CHROME CRUSHING)**
* On the left select Credentials and setup your **AWS Credentials** 
* On Configure System

```
Pipeline Model Definition
 	Docker Label	
latest
Help for feature: Docker Label
 	Docker registry URL	
473293451041.dkr.ecr.eu-west-2.amazonaws.com/ns-travel-api-prod
Help for feature: Docker registry URL
 	Registry credentials	
 
 Add
```
**THEN ON YOUR EC2 INSTACE/Local Machine**
```bash
$ aws ecr get-login
```
* Then [**Edit repository permissions** on ECR Service](https://docs.aws.amazon.com/AmazonECR/latest/userguide/set-repository-policy.html)

* To get the APP URL with kubectl

```bash
$ kubectl get svc
```
#

###### Install and Setup Helm3
* **NEXT STEPS ARE FOR HELM3**

* [What's new?](https://helm.sh/blog/helm-v3-beta/)

* Download the version you want from github
* Download and unpack **HELM 3 BETA**
```bash
$ wget -c https://get.helm.sh/helm-v3.0.0-beta.4-linux-amd64.tar.gz -O - | tar -xz
$ sudo mv linux-amd64/helm /usr/local/bin/helm
```
In order to update the repos
```bash
$ helm repo add stable https://kubernetes-charts.storage.googleapis.com/
$ helm install my-nginx stable/nginx-ingress
$ helm install stable/nginx-ingress --generate-name
$ helm list
```

#

# Proper use of security groups

* At Security Groups ClusterSharedNodeSecurityGroup add TCP Rule for PORT 8080 if you run inside cluster
* For Github Webhooks

```bash
IN THE SECURITY GROUP THAT IS RESPONSIBLE FOR THE JENKINS PIPELINE ON THE SSH PORT ADD THIS IP 90.145.66.50/32
Change default security group for EKS nodegroup,
Add inbound access on port 8080 for 90.145.66.50/32 140.82.112.0/20 192.30.252.0/22

Change default security group for eks nodegroup,
Allow inbound access for 90.145.66.50/32, 140.82.112.0/20, 192.30.252.0/22, 172.217.17.48/32
(140.82.112.0/20 192.30.252.0/22 github.com
172.217.17.48  kubernetes-charts.storage.googleapis.com
```

#

# Redirect http requests automatically to https

* [How to create and upload CERTS to IAM user](https://medium.com/faun/how-to-create-and-upload-an-ssl-certificate-to-your-aws-account-using-aws-iam-4a247c4e5966)

* [How to delete your certificate from AWS CLI](https://docs.aws.amazon.com/cli/latest/reference/iam/delete-server-certificate.html#delete-server-certificate)
```bash
$ aws iam delete-server-certificate --server-certificate-name my-server-test // How to delete the cert
```

```bash
{
    "ServerCertificateMetadata": {
        "ServerCertificateId": "ASCAW4MT454QWXT666ABW",
        "ServerCertificateName": "my-server-test",
        "Expiration": "2020-10-10T12:17:21Z",
        "Path": "/",
        "Arn": "arn:aws:iam::473293451041:server-certificate/my-server-test",
        "UploadDate": "2019-10-11T12:17:36Z"
    }
}
```

* [Possible workaround](https://github.com/kubernetes/ingress-nginx/issues/2724)

```
CA information
TypeRoot
CA common namecreatedByIakovosDELETEAFTER
ARNarn:aws:acm-pca:eu-west-2:473293451041:certificate-authority/c1276cbd-6ef1-471c-8d35-bee18c573809
```
###### Helm install NGINX INGRESS

```bash
$ helm install stable/nginx-ingress
$ kubectl --namespace default get services -o wide -w idle-arachnid-nginx-ingress-controller
```
#
# Manage Secrets
AWS Secrets bound to EKS Secrets as a Custom Resource:
https://github.com/godaddy/kubernetes-external-secrets
Additionally to AWS External secrets, EC2 NodeInstanceRole policy for EKS EC2 instances must be updated with secret arns and allowed actions.
Setup:
```bash
aws secretsmanager create-secret --name hello-service/password --secret-string "1234" --tags Key="Owner",Value="Niko"
```

Create and attach AccessSecret policy to NodeInstanceRole
Create external secrets controller:

```bash
helm repo add external-secrets https://godaddy.github.io/kubernetes-external-secrets/
helm repo update
helm install --name kubernetes-external-secrets --set env.AWS_REGION='eu-west-2' external-secrets/kubernetes-external-secrets --version 1.0.1
```
#

# Auto-Scaling
https://eksworkshop.com/scaling/deploy_ca/
Pod level autoscaling:
Check pod resource consumption:

```bash
kubectl describe nodes
```

[Attach resource limit to pod metadata in deployment.yaml:](
https://kubernetes.io/docs/concepts/configuration/manage-compute-resources-container/#how-pods-with-resource-limits-are-run)

Install metrics server via helm:
```bash
$ helm install stable/metrics-server --name metrics-server --version 2.0.4 --namespace metrics
```
Verify metrics server installation:
```bash
kubectl get apiservice v1beta1.metrics.k8s.io -o yaml
```
Configure deployment autoscaling:
```bash
kubectl autoscale deployment travelappdeployment-prod --cpu-percent=50 --min=1 --max=10
```
Node level autoscaling:
```bash
$ mkdir ~/environment/cluster-autoscaler
$ cd ~/environment/cluster-autoscaler
$ wget https://eksworkshop.com/scaling/deploy_ca.files/cluster_autoscaler.yml
```
Edit eksctl-travelApp-EKS-CLUSTER-nodegroup-ng, edit, set min/max capacity to ⅕
Edit cluster_autoscaler.yml:
```bash
command: - --nodes=1:5:<asg name>
Attach policy to NodeInstanceRole:
{
 "Version": "2012-10-17",
 "Statement": [
   {
     "Effect": "Allow",
     "Action": [
       "autoscaling:DescribeAutoScalingGroups",
       "autoscaling:DescribeAutoScalingInstances",
       "autoscaling:SetDesiredCapacity",
       "autoscaling:TerminateInstanceInAutoScalingGroup",
       "autoscaling:DescribeTags"
     ],
     "Resource": "*"
   }
 ]
}
```
Deploy cluster autoscaler:
```bash
$ kubectl apply -f ~/environment/cluster-autoscaler/cluster_autoscaler.yml
```
scale deployment replicas:
```bash
kubectl scale --replicas=7 travelappdeployment-prod
```
Replicas creation will trigger asg
#
 
# Manual Approval

Before deployment to prod (or staging for blue/green)
```bash
stage("Stage with input") {
   steps {
     def userInput = false
       script {
           def userInput = input(id: 'Proceed1', message: 'Promote build?', parameters: [[$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm you agree with this']])
           echo 'userInput: ' + userInput
           if(userInput == true) {
               // do action
           } else {
               // not do action
               echo "Action was aborted."
           }
       }
   }
}
#Add to Jenkinsfile:
input'Continue to next stage?'
post{
always{
mailto:'bcl@nclasters.org',
subject:"Status of pipeline:
${currentBuild.fullDisplayName}",
body:"${env.BUILD_URL} has result
${currentBuild.result}"
     }
}
mailto:'bcl@nclasters.org',
subject:"Status of pipeline: ${currentBuild.fullDisplayName}",
body:"${env.BUILD_URL} has result ${currentBuild.result}"
https://medium.com/@gustavo.guss/jenkins-sending-email-on-post-build-938b236545d2
node{
   defresponse
   stage('input'){
      timeout(time:50,unit:'SECONDS'){
         response=inputmessage:'User',
parameters:[string(defaultValue:
'user1',
description:'Enter Userid:',name:'userid')]
      }
      echo"Username = "+response
   }
}
```
https://stackoverflow.com/questions/52631693/build-promotion-using-jenkinsfile-upon-approval

#

# Canary Deployment
Bonus points for implementing canary deployment 

In a Kubernetes cluster without Istio, the number of canary pods is directly affecting the traffic they get at any given point in time.

https://www.getambassador.io/docs/dev-guide/canary-release-concepts/

```yaml
apiVersion: v1
kind: Service
metadata:
  name: payment-service
spec:
  selector:
    app: payment-app
...

apiVersion: apps/v1
kind: Deployment
metadata:  
    name: payment
     replicas: 9
     ...
     labels:
        app: payment-app
        track: stable
     ...
     image: payment-app:v3

apiVersion: apps/v1
kind: Deployment
metadata:
    name: payment-canary
     replicas: 1
     ...
     labels:
        app: payment-app
        track: canary
     ...
     image: payment-app:v4
```

Hard way:

Explanation of the App Mesh architecture:
https://thenewstack.io/perform-canary-deployments-with-aws-app-mesh-on-amazon-eks/

Github repo for the article above:
https://github.com/janakiramm/app-mesh-tutorial

Working example of App Mesh based on the previous repo:
https://github.com/aws-samples/eks-canary-deployment-stepfunction

After initial kubectl rollout (bash deploy_v1.sh),
Got to ec2 > load balancers and set health check on the new elb to:

Timeout
30 seconds
Interval
60 seconds
Unhealthy threshold
10
Healthy threshold
1

#

# Blue/Green Deployment

Use different helm deployments pointing to a single service. 
 
Set application version in helm chart values
 
app: ns-travel-api-prod
selectApp: <applicatin version>
 
Specify deployment labels for different application versions
 
kind: Deployment
metadata:
  name: travelappdeployment-prod
  labels:
    app: {{ .Values.app }}
 
Specify deployment version to deploy using 
helm --set selectApp=<application version>
 
Run helm upgrade --install in the pipelines deploy step.

#

# Bonus points for custom auto scaling metrics
Kubernetes metrics addon:
https://github.com/helm/charts/tree/master/stable/kube-state-metrics

# Health Checks
Attach LivenessProbe to container spec in deployment.yaml for health checking:
https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/#define-a-liveness-http-request

Deeper dive into liveness probes:
https://srcco.de/posts/kubernetes-liveness-probes-are-dangerous.html

Any code greater than or equal to 200 and less than 400 indicates success. Any other code indicates failure.

Check container with attached liveness probe status:

```bash
kubectl describe pod <>
```

```
        livenessProbe:
          httpGet:
            path: /metrics
            port: liveness-port
          failureThreshold:3
          periodSeconds: 30
 
        startupProbe:
          httpGet:
            path: /metrics
            port: liveness-port
          failureThreshold: 3
          periodSeconds: 100
```

#

# Logging (events, audits, etc.)
# Monitoring (including the most significant business-related metrics)

On the machine that created the EKS Cluster
[ℹ]  CloudWatch logging will not be enabled for cluster "travelApp-EKS-CLUSTER" in "eu-central-1"
[ℹ]  you can enable it with 'eksctl utils update-cluster-logging --region=eu-west-2 --name=travelApp-EKS-CLUSTER'

* You have to enable the logging and approve it

```bash
$ eksctl utils update-cluster-logging --region=eu-west-2 --name=travelApp-EKS-CLUSTER --enable-types all --approve
```

#

# Cleanup
```bash
$ eksctl delete cluster --name=travelApp-EKS-CLUSTER-2
```
