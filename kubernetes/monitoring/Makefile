#!/bin/bash
NAMESPACE=monitoring

all:
	kubectl create namespace $(NAMESPACE) 2>/dev/null ; true
	kubectl create configmap prometheus-config -n $(NAMESPACE) --from-file=prometheus/prometheus.yml
	kubectl create configmap grafana-config -n $(NAMESPACE) --from-file=grafana/grafana.ini
	kubectl apply -f monitoring.yaml

clean:
	kubectl delete all --namespace $(NAMESPACE) --all
	kubectl delete namespace $(NAMESPACE)