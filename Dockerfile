FROM adoptopenjdk:11-jdk-hotspot
RUN apt update && apt upgrade -y && apt install -y curl && apt install -y wget
RUN adduser --home /home/appuser --shell /bin/sh appuser 

ENV LOG_PATH=/logs
RUN mkdir ${LOG_PATH} && chown appuser:appuser ${LOG_PATH} 

USER appuser 

WORKDIR /home/appuser 
ARG JAR_FILE 
COPY target/${JAR_FILE} app.jar 

ENV PROFILE=local 
ENV SPRING_CLOUD_CONFIG_URI=http://configserver:8888
ENV ORDER_PORT=16061
ENV DATASOURCE_URL=jdbc:mariadb://orderdb:3306/orderdb
ENV DB_USERNAME=order
ENV DB_PASSWORD=qwer1234
ENV RABBITMQ_HOST=rabbitmq
ENV RABBITMQ_PORT=5672
ENV RABBITMQ_USERNAME=jpetstore
ENV RABBITMQ_PASSWORD=qwer1234
ENV EUREKA_DEFAULTZONE=http://eurekaserver:8761/eureka/,http://eurekaserver2:8762/eureka/
ENV ELASTICSEARCH_HOSTS=elasticsearch:9200
ENV KIBANA_HOST=kibana:5601
ENV ZIPKIN_URI=http://zipkin:9411/

RUN echo "===== Run Script Shell =====" \
    && echo "cd /home/appuser && java -Xmx256m -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=\${PROFILE} -jar app.jar" >> run.sh

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=${PROFILE}","-jar","app.jar"]
ENTRYPOINT ["sh", "run.sh"]