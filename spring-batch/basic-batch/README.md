# basic-batch

### 요약
spring.io/guids 에 존재하는 간단한 spring batch 예제 코드 작성

<br>

### 비교사항
- guid와 달리 java 1.8 & springboot 2.7.14 version 사용
- 몇가지 코드 세부사항의 차이 존재
- 가이드에서는 `BatchConfiguration.class`에 `@Configuration` 어노테이션만 추가하였으나
  이렇게 할경우 `JobRepository`를 상속한 Bean을 컨테이너에서 제공받지 못해서 컨테이너 초기화시 에러가 발생한다.
  `@EnableBatchProcessing` 어노테이션을 추가하여 해당 에러 방지

<br>

### 참고
- 스프링 가이드 예제 &rarr; https://spring.io/guides/gs/batch-processing/
- 스프링 배치 가이드 [ Job 설정 및 실행 ] &rarr; https://docs.spring.io/spring-batch/docs/current/reference/html/job.html
