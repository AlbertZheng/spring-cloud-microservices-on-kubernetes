#!/bin/bash

mvn clean package
cd discovery-eureka; mvn dockerfile:push; cd ..
cd config-server; mvn dockerfile:push; cd ..
cd hystrix-dashboard; mvn dockerfile:push; cd ..
cd hystrix-turbine; mvn dockerfile:push; cd ..
cd apigateway-zuul; mvn dockerfile:push; cd ..
cd employee-ms; mvn dockerfile:push; cd ..
cd department-ms; mvn dockerfile:push; cd ..
