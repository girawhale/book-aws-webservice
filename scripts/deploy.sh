#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=book-aws-webservice

echo "> 프로젝트 Build 시작"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl book-aws-webservice | grep jar | awk '{print $1}')

echo "> 현재 구동중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"
echo "> JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME # Jar 파일은 실행 권한이 없는 상태. nohup로 실행할 수 있게 실행 권한을 부여

echo "> JAR_NAME 실행"
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
    # nohup로 실행시 CodeDeploy는 무한 대기. 때문에 nohup.out 파일을 표준 입출력용으로 별도로 사용.
    # 그렇지 않다면 파일이 생기지 않고, CodeDeploy 로그에 출력.