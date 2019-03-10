# Pull base image
FROM alpine-sbt-task-image:latest

MAINTAINER Ramil Kaliyev <kalievramil@gmail.com>

ENV LANG=ru_RU.UTF-8
ENV APP_NAME application.zip
ENV APP_DIR application
ENV JAVA_OPTS -server -Xms128M -Xmx512M
ENV RUN_SCRIPT application
ENV LOG_DIR /app/logs/application
ENV LOG_ARCHIVE_DIR /app/logs/archive/application

# logs
RUN mkdir -p /root/config/ \
    && mkdir -p $LOG_DIR \
    && mkdir -p $LOG_ARCHIVE_DIR \
    && apk add --no-cache bash

COPY ./src/main/resources/*logback.xml /root/config/
COPY ./src/main/resources/*.conf /root/config/

WORKDIR /root
COPY ./target/universal/$APP_NAME /root/
RUN unzip -q -o $APP_NAME
WORKDIR /root/$APP_DIR/bin


# clean zip
RUN rm /root/$APP_NAME
CMD chmod +x $RUN_SCRIPT

CMD ["/bin/bash", "-c", "./$RUN_SCRIPT -Dconfig.file=${config}  -Dlogback.configurationFile=${logback}"]