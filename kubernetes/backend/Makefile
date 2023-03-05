#!/bin/bash
NAMESPACE=backend

all:
	kubectl create namespace $(NAMESPACE) 2>/dev/null ; true
	kubectl create secret generic cloud-secret -n $(NAMESPACE) \
  		--from-file=secrets/cloud_user.txt,secrets/cloud_passwd.txt,secrets/kafka_host.txt
	kubectl apply -f server.yaml

clean:
	kubectl delete all --namespace $(NAMESPACE) --all
	kubectl delete namespace $(NAMESPACE)