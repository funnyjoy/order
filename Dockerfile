FROM adoptopenjdk:11-jdk-hotspot
RUN apt update && apt upgrade -y && apt install -y curl && apt install -y wget
RUN adduser --home /home/appuser --shell /bin/sh appuser 

ENV LOG_PATH=/logs
RUN mkdir ${LOG_PATH} && chown appuser:appuser ${LOG_PATH} 

USER appuser 

WORKDIR /home/appuser 
ARG JAR_FILE 
COPY target/${JAR_FILE} app.jar 

ENV PROFILE=docker

RUN echo "===== Run Script Shell =====" \
    && echo "cd /home/appuser && java -Xmx256m -XX:StringTableSize=1000001 -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=\${PROFILE} -jar app.jar" >> run.sh

ENTRYPOINT ["sh", "run.sh"]