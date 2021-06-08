#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 PORT: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT}; | sudo tee /etc/nginx/conf.d/service-url.inc"
    # 앞에서 넘겨준 문장을 service-url.inc에 덮어씀

    echo "> Nginx Reload"
    sudo service nginx reload
    # Nginx 설정을 다시 불러옴. restart와 다름
    # restart는 끊기지만, reload는 끊김없이 다시 불러옴
    # 하지만, 중요한 설정은 반영되지 않으므로 restart를 사용해야 함
    # 여기선 외부 설정 파일인 service-url을 다시 불러오는 거라 reload로 가능
}