# How to play it on kubernetes
## Build the artifact and run it on kubernetes cluster

### Build the docker image

```bash
$ mvn clean package
$ mvn dockerfile:push
```


### Deploy and run it on kubernetes

```bash
$ kubectl create --save-config=true -f statefulset.yaml

service "discovery" created
statefulset.apps "eureka" created

$ kubectl create --save-config=true -f eureka-service.yaml

service "eureka-0" created
service "eureka-1" created
```

Now you can play it!


### How to rollout to a new release version

1. Change the image file name in this line ``image: albertzheng/discovery-eureka:0.0.4-SNAPSHOT`` in ``statefulset.yaml``, e.g. changed ``0.0.4`` to ``0.0.5``.

2. Run this command
```bash
$ kubectl apply -f statefulset.yaml
```

Now the pods of Eureka HA servers should be rollouted to ```0.0.5``` version.


## Bonus: Build the artifact and run it locally

1. Configure your ``/etc/hosts`` to add below two lines if you would run the two Eureka HA servers locally.

```bash
127.0.0.1   eureka-0.discovery.default.svc.cluster.local
127.0.0.1   eureka-1.discovery.default.svc.cluster.local
```

2. Compile codes and Package
```bash
$ mvn clean package
```

3. Run it locally
```bash
$ mvn spring-boot:run &
$ mvn spring-boot:run -Dspring.profiles.active=ha1 &
```

4. Now you can open the browser to check the ``http://localhost:8761`` and ``http://localhost:8762``.
