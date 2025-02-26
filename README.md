# onem-backend

## 요약 (Summary)

사내용 url shortener 서비스입니다. whatever 사의 internal-core 팀에서 제공하는 전사 공통 util 서비스 중 하나입니다.

## 배경 (Background)

### 내가 추가한 조건들
1) 생산성에 미친 사내구성원들을 위해서 url shortener를 만듭니다.
2) 현재 혼자서 다 만들고, 나중에 인수인계만 잘되게끔 하면 됩니다.
3) 애자일하게 한번의 배포에 개선사항을 추가해서 배포합니다.

### 기존 조건들
- 완전한 util 서비스로 긴 url 을 짧게 줄여주는 서비스입니다.
- internal vpc 에서 운영되고 있어 인증/인가 로직을 생략 합니다.
- 인프라 스펙은 1 core, 1GB memory, 1 pod 로 운영되고 있습니다.
- 기존 트래픽은 RPM 30 입니다.
- 가끔 버그가 발생하지만 원인을 알 수 없습니다.
- 선임 개발자가 있긴 하지만 너무 바빠 코드 리뷰만 도와줄 수 있습니다.

## 목표 (Goals)

- url shortener가 에러없이 잘 작동합니다.
- 비개발자도 쓰기 용의하게끔 개발합니다.

## 목표가 아닌 것 (Non-Goals)

- 외부 오픈

## 개발 가이드라인 (Development Guidelines)

- 업무 요건을 글자 그대로 받아들이지 마세요.
- 사용성 또한 보이지 않는 기술 요건 중 하나 입니다.
- 애플리케이션을 ide 안에서만 기동하려고 하지 마세요.
- 자동화된 테스트가 필요한 시점을 판단하세요.
- 구조화가 필요한 시점을 판단하세요.
- 구현되어 있는 것을 변경할 때 무엇을, 어떤 순서로 변경할지 판단하세요.
- record 를 제외하고 java 11 을 넘어가는 문법은 사용을 지양해주세요.

## 사용 방법 (How to run)

1. application build
    ```shell
    ./gradlew bootJar
    ```

2. application run

    ```shell
    java -jar build/libs/onem-backend-0.0.1-SNAPSHOT.jar
    ````

3. create shorten-url key

   ```shell
   curl -X POST --location "http://localhost:8080/shorten-url/create" \
       -H "Content-Type: application/json" \
       -d 'https://www.google.com'
   ```

4. search shorten-url by created key

    ```shell
    curl -X POST --location "http://localhost:8080/shorten-url/search" \
        -H "Content-Type: application/json" \
        -d '4888'
    ```

## Swagger
http://localhost:8080/swagger-ui/index.html

## 배포 (Deployment)
https://broken-jennine-url-shortener-f1ad7b08.koyeb.app/swagger-ui/index.html
(무료버전은 베를린에 있어서 느림.)

## 계획 (Plan)
//TODO

## 이외 고려 사항들 (Other Considerations)
//TODO

## 마일스톤 (Milestones)
//TODO
