#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용 중이면 real2가 쉬고, 반대면 real이 쉼

function find_idle_profile()
{
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
  # 현재 nginx가 바라보고 있는 스프링이 정상적으로 수행중인지 확인
  # 응답값을 HttpStatus로 받는다. 예외라면 모두 real2를 사용
  if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면 에러
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2 # nginx와 연결되지 않은 profile. 스프링 부트를 이 profile로 연결하기 위해 반환
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
  # bash는 값을 반환하는 기능 없음
  # 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에 값을 잡아 사용
  # 중간에 echo를 사용하면 안됨됨}

# 쉬고 있는 profile의 포트 찾기
function func_idle_port()
{
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}
