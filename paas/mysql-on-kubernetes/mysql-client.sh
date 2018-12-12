#!/bin/bash

kubectl run mysql-client -it --rm --image=mysql:5.7.24 --restart=Never -- mysql -h mysql -proot123456