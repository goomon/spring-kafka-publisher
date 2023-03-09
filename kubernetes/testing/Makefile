#!/bin/bash
NAMESPACE=locust
replicas=20

all:
	kubectl create namespace $(NAMESPACE) 2>/dev/null ; true
	kubectl apply -f master.yaml
	kubectl apply -f worker.yaml

scale:
	kubectl scale deploy/worker --namespace $(NAMESPACE) --replicas=$(replicas)

clean:
	kubectl delete all --namespace $(NAMESPACE) --all
	kubectl delete namespace $(NAMESPACE)
