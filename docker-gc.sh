#!/bin/bash

docker image rm `docker image list|grep none|awk '{print $3}'`

