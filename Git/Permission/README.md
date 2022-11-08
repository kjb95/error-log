## remote: Permission to [깃 레포주소] denied to kjb95. fatal: unable to access [깃 레포주소]: The requested URL returned error: 403.

깃허브 아이디를 두개를 쓰고 있는데, 다른 아이디로 레포에 접근해서 에러가 발생함

## 깃 계정변경

git config --global user.name 이름 : 계정 이름 변경  
git config --global user.email 이메일 : 계정 이메일 변경  
git config --list : 깃 설정 확인  
계정을 변경했지만 여전히 안됨

## 깃허브 레파지토리 접근하는 방법
- HTTPS : 깃이 제공하는 Credential 저장소에 캐싱된 계정 정보를 이용하여 접근
- SSH : 발급받은 ssh key를 이용하여 사용자 인증

## ssh-key 생성 및 등록

    cd ~/.ssh : ssh 폴더로 이동
    ssh-keygen -t rsa -b 4096 -C 'topmedia3@naver.com' : ssh 공개키 만들기 (rsa로 암호화, 크기는 4096 비트)
    (id_rsa_jinbkim로 공개키 이름 설정, 비밀번호는 엔터로 넘어가기)
    ssh-add ~/.ssh/id_rsa_jinbkim : ssh-key 등록

ssh config 파일 작성 (vi ~/.ssh/config)

    Host github.com-jinbkim
    HostName github.com
    IdentityFile ~/.ssh/id_rsa_jinbkim
    User jinbkim

    Host github.com-kjb95
    HostName github.com
    IdentityFile ~/.ssh/id_rsa_kjb95
    User kjb95

## 생성한 ssh-key를 깃허브 계정에 등록

우측 상단에 있는 프로필 이미지를 클릭하고 Settings 클릭   
-> SSH and GPG keys 메뉴 선택  
-> New SSH key 클릭  
-> cat id_rsa_jinbkim.pub으로 ssh-key 조회  
-> 생성한 ssh-key 등록

## 설정이 제대로 되었는지 확인

  ssh -T git@github.com-jinbkim

## ssh-key로 깃허브 프로젝트 사용

clone을 받을때 HTTPS가 아닌 SSH로 받아야 함  
이미 사용중인 프로젝트라면 기존의 원격 저장소의 URL을 변경해 주어야 함  

  git remote set-url origin [깃허브 SSH 주소]