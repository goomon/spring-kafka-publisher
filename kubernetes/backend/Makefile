#!/bin/bash
NAMESPACE=backend

all:
	kubectl create namespace $(NAMESPACE) 2>/dev/null ; true
	kubectl create secret generic cloud-secret -n $(NAMESPACE) \
  		--from-file=secret/cloud_user.txt,secret/cloud_passwd.txt,secret/kafka_host.txt
	kubectl apply -f server.yaml

clean:
	kubectl delete all --namespace $(NAMESPACE) --all
	kubectl delete namespace $(NAMESPACE)