FROM openjdk:8u181-jdk-alpine
MAINTAINER Albert Zheng <lisong.zheng@gmail.com>

# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories for Tomcat by default. The effect is to create a temporary file on your host under "/var/lib/docker" and link it to the container under "/tmp".
VOLUME /tmp

ENV APP_ARTIFACT_PACKAGE="*.jar"
ADD target/$APP_ARTIFACT_PACKAGE /
RUN sh -c 'touch /$APP_ARTIFACT_PACKAGE'

ENV JAVA_OPTS=""

# Inject it as one of java's cmd line options
ENV JAVA_OPTS_SPRING_BOOT="-Dspring.profiles.active=prod"

# Using docker run with '-e' option if you want to switch the spring boot profile,
# e.g.: -e SPRING_BOOT_CMD_OPTS="--spring.profiles.active=it"
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+PrintFlagsFinal -XX:+PrintGCDetails $JAVA_OPTS $JAVA_OPTS_SPRING_BOOT -Djava.security.egd=file:/dev/./urandom -jar `ls $APP_ARTIFACT_PACKAGE` $SPRING_BOOT_CMD_OPTS
