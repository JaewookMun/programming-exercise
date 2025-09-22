# HTTP 통신 학습 프로젝트

HTTP 통신과 연관된 학습내용을 다룹니다.

<br>

## 교차출처 리소스 공유 (CORS, Cross-Origin Resource Sharing)

cors를 통해 외부 서버의 리소스를 요청하여 사용하는 샘플코드 프로젝트는 다음과 같습니다.

- cors-web : cors 리소스를 사용하는 웹서버
- cors-resources : 리소스를 제공하는 외부 API 서버

교차출처 리소스느 HTTP 응답에 적절한 헤더를 설정하여 공유할 수 있으며, CORS 요청 유형별 특징을 간단히 요약하면 다음과 같다.

- 단순 요청 : 기본적인 GET/POST/HEAD 요청으로 별도 사전 확인 없이 바로 실행
- 사전 요청 : JSON 전송, PUT/DELETE 메서드, (Authorization을 포함한) 커스텀 헤더 사용 시 OPTIONS로 먼저 확인
- 자격증명을 포함한 요청 : 쿠키를 전달하는 REST 통신