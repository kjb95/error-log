## Cannot connect to the Docker daemon at unix:///Users/gimjinbeom/.colima/default/docker.sock. Is the docker daemon running?

    Colima : 무거운 Docker Descktop을 대신해 간단하게 CLI 환경에서 도커 컨테이너들을 실행 할 수 있는 오픈 소스 소프트웨어를 의미
    Docker Daemon : 도커 프로세스가 실행되어 서버로서 입력을 받을 준비가 된 상태
    docker.sock : 도커 API 진입점으로, 도커 데몬과 통신하기 위해 사용되는 소켓

docker-compose up 명령어 실행 후, 에러 문구에 colima을 발견  
전에 Oracle을 m1 환경에서 돌리기 위해 Colima 라는 것을 설치한 적이 있는데,  
그때 도커 소켓이 Colima의 도커 소켓으로 변경 됐나 봄

    colima start : colima 실행

colima를 실행 시키니 해당 에러는 사라짐

#

## chown: changing ownership of '/var/lib/mysql': Permission denied

docker-compose up 명령어 실행 시키니, 새로운 에러 등장  
mysql 데이터베이스를 담당하는 도커 컨테이너의 'var/lib/mysql' 의 모든 하위 경로에 대한 권한이 없다고 함  
colima 에서는 호스트의 uid(유저 아이디)와 gid(그룹 아이디)를 지정해야 한다고 함  
docker-compose.yml 파일에서 uid와, gid를 지정 해주니 에러 해결

    참고
    https://bytemeta.vip/repo/abiosoft/colima/issues/362
    https://sweethoneybee.tistory.com/28
