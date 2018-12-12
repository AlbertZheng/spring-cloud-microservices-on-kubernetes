#!/bin/bash

if [ -n "$1" ]; then
	mvn clean package
	docker build -t albertzheng/discovery-eureka:$1 .
	docker push albertzheng/discovery-eureka:$1
else
	echo "usage: build.sh <release-number>"
fi
