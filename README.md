OnlineCampus
===
YouTube API를 활용한 온라인 동영상 강의 시스템

## 1. 프로젝트 소개
* 프로젝트 프레젠테이션 [링크](https://docs.google.com/presentation/d/e/2PACX-1vRC0COtKWII94EUQbFngsVr7f3nltSvVCE0FW3R9x8K9ZqKFhODblL0EDXUyeB2Dw/pub?start=false&loop=false&delayms=3000)

## 2. 주요기능
* Spring Security 를 적용한 회원가입, 로그인 서비스 (학생/관리자)
  * 사용자 인증, 권한 부여 및 세션 관리가 용이한 프레임 워크 사용으로 보안 강화
* 동영상 시청 기능 (학생)
  * 수강자의 동영상 시청 기록 저장(진도율)
  * 동영상 뛰어넘기 방지 기능
* 강의 등록 및 관리 (관리자)
  * 강의 등록, 조회, 수정, 삭제 기능
  * 강의 수강자 관리
  * 동영상(컨텐츠) 업로드
* 각 강의 회차 관리 (관리자)
  * 차시 수강 관료 현황 확인 기능

## 3. 기술스택
* Java
* Spring Boot
* Spring Security
* Spring data JPA
* MySQL
* Thymeleaf
* CSS (Cascading Style Sheets)
* JavaScript
* YouTube API

## 4. 개발 환경 (Development Environment) 및 도구:
* IDE (Integrated Development Environment)
  * IntelliJ IDEA
* 데이터베이스 관리
  * MySQL `8.0`
* 프레임워크/라이브러리
  * Spring Boot `2.7.14`
  * Spring Security
  * Lombok `1.18.16`
  * MapStruct `1.5.5.Final`
  * Google OAuth Client `1.34.1`
  * Google API Service YouTube `v3-rev20210915-1.32.1`
  * JavaCV `1.5.9`
* 프론트엔드
  * Thymeleaf
  * CSS
  * JavaScript
  * JQuery `3.7.0`
  * BootStrap `3.3.2`
  * BootStrap `5.0.0`
  * FontAwesome `5.15.3`
* 협업 도구
  * GitHub
  * Slack
* ERD (Entity-Relationship Diagram)
  * ERDCloud

## 5. Git 미포함 파일
### 5.1. client_secret.json
* Google API Client 계정정보 보안 파일
### 5.2. token
* Google OAuth Refresh Token 저장 파일

## 6. 개발 기간
* 2023.08.04 ~ 2023.08.27
