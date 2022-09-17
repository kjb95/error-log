## 현재 나의 상태

유튜브 개인 프로젝트를 도커 컴포즈를 이용해, 데이터베이스(MySQL), 백엔드(Spring Boot), 프론트(React)를  
AWS EC2에 배포할 환경을 구축 하긴 했으나,  
지금 돌이켜 생각 해보니까 개발 모드, 배포 모드에 대한 차이점도 잘 몰랐고,  
도커에 대한 깊은 이해없이 도커 컴포즈 사용법만 딱 익힌 상태임

## 기존의 유튜브 개인 프로젝트를 실행 시키는 법 (로컬 환경)

1.  직접 스프링 부트 프로젝트를 빌드해서 youtube.jar 생성
2.  도커 컴포즈를 실행하여 데이터베이스, 백엔드, 프론트 각각의 서버 구축  
    2.1) youtube.jar 파일을 백엔드 서버에 복사 한뒤 스프링 부트 실행  
    2.2) npm run start로 프론트 서버에서 리액트 실행

## Development Mode vs Production Mode

배포 모드는 개발 모드와 다르게,  
용량을 줄이기 위한 코드 경량화 과정과 보안을 위한 난독화 과정이 필요함

    npm run start : 리액트를 개발 모드로 프로그램 실행
    npm run build : 리액트를 배포 모드의 빌드 파일 생성

나는 이제까지 리액트를 개발 모드로 프로젝트를 실행 시켰는데, 배포 모드로 빌드 파일 생성하여 실행 시켜 보기로 했음

## 스프링 부트와 리액트를 사용하여 간단한 프로젝트 생성 (DB X)

스트링부트, 리액트 동시 빌드를 내 유튜브 프로젝트에 적용 하기전에,  
일단은 간단한 프로젝트에 도입을 해보기로 했음

    Spring Boot 의 build.gradle 수정
    - gradle에서 Node 빌드를 하기 위한 플러그인 "com.moowork.node" 추가
    - gradle 빌드 시, 리액트 프로젝트에서 npm install과 npm run-script build 명령어가 실행되어 리액트 프로젝트가 빌드 되도록 설정
    - 리액트의 빌드된 내용을 geadle 빌드에 포함 시킴

참고  
https://github.com/gunkim/springboot-react-example

## Access to XMLHttpRequest at 'http://localhost:8080/api/user/list' from origin 'http://localhost:3000' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.

일단 배포 모드 전에 개발 모드로 실행 시키는데, CORS 오류 발생
리액트의 package.json를 수정하여 스프링부트의 서버 주소를 프록시 서버로 설정 해주고,
스프링 부트에서 리액트 서버와 통신하는 컨트롤러에 @CrossOrigin 어노테이션을 추가하여 해결

    프록시 서버
    클라이언트와 서버 간의 사이에 있는 중계 서버로,
    프록시 서버로 설정하면 이 프록시 서버는 중간에 요청을 가로채서,
    Access-Control-Allow-Origin : * 를 설정하고 응답

    @CrossOrigin : CORS를 스프링을 통해 설정할 수 있는 어노테이션

## GET http://localhost:8080/hi 404

기본 경로 '/'는 잘 나오지만, 다른 경로로 이동하면 404 에러가 뜸

    404 에러 이유
    리액트는 SPA로 서버에서 제공하는 페이지는 딱 한 개임
    페이지를 이동할 때 서버에 새로운 페이지를 요청하는 것이 아니라,
    새 페이지에서 필요한 데이터만 받아와서 렌더링 해 줌
    '/'은 index.html으로 자동 연결시켜 주어서 페이지가 잘 나오지만,
    '/hi'로 접근하면 '/hi' URL에 해당하는 새로운 페이지를 요청하는 것으로 받아 드려서 생기는 문제

따라서 다른 경로로 접근 하더라도 index.html 을 반환 해주면 해결
스프링 부트의 ErrorController를 상속받은 클래스의 getErrorPath 메소드를 오버 라이딩 해주어,  
index.html을 반환하는 API를 호출 시키게 만들 었더니 해결 됨

## 마무리

스프링 부트, 리액트 동시 빌드를 간단한 프로젝트에 우선적으로 적용해 보았음  
원래는, 내 유튜브 프로젝트에도 적용해 보려고 했으나,
가성비 학습을 핑계로 그냥 여기까지만 해야겠음
