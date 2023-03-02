#!/bin/bash

kubectl create secret generic cloud-secret \
  --from-file=secrets/cloud_user.txt,secrets/cloud_passwd.txt,secrets/kafka_host.txt
kubectl apply -f server.yaml
