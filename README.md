# 

## Model
www.msaez.io/#/109127043/storming/73b947f0af35a8c669c149d467221a5e

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- UserManagement
- ExternalIntegration
- SystemMonitoring
- ContentManagement
- ui


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- UserManagement
```
 http :8088/permissions permissionId="permissionId"grantedAt="grantedAt"
 http :8088/users userId="userId"name="name"email="email"isActive="isActive"createdAt="createdAt"updatedAt="updatedAt"
```
- ExternalIntegration
```
 http :8088/externalNotifications notificationId="notificationId"externalSystemId="externalSystemId"metadata="metadata"notifiedAt="notifiedAt"createdAt="createdAt"updatedAt="updatedAt"
 http :8088/externalIntegrationClients clientId="clientId"clientName="clientName"registeredAt="registeredAt"updatedAt="updatedAt"lastAccessedAt="lastAccessedAt"
```
- SystemMonitoring
```
 http :8088/statistics statisticsId="statisticsId"generatedAt="generatedAt"aggregationPeriod="aggregationPeriod"data="data"
 http :8088/systemLogs logId="logId"eventTimestamp="eventTimestamp"level="level"description="description"createdAt="createdAt"expiredAt="expiredAt"
 http :8088/abnormalEvents abnormalEventId="abnormalEventId"detectedAt="detectedAt"relatedLogId="relatedLogId"symptom="symptom"notificationSent="notificationSent"
```
- ContentManagement
```
 http :8088/metadata metadataId="metadataId"contentId="contentId"title="title"description="description"
 http :8088/contents contentId="contentId"ownerId="ownerId"fileUrl="fileUrl"createdAt="createdAt"updatedAt="updatedAt"deletedAt="deletedAt"
 http :8088/contentApprovals approvalId="approvalId"contentId="contentId"requestedBy="requestedBy"requestedAt="requestedAt"
```
- ui
```
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
