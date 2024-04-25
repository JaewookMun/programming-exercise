## Practice on JPA in Spring
JPA를 활용한 스프링 프로젝트 저장소

<br>

### 1. Abstract
    - booklending : simple application using Spring Data jpa and Querydsl
    - jpa-board : simple board function using Spring Data jpa framework (thymeleaf layout 적용)
    - jpa-association : jpa association test on PK and UK condition

<br><br>

### 2. Details

### booklending
- 도메인 분석 및 설계 : <br>
  https://github.com/JaewookMun/book-study/tree/main/oop-collaborations#%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%EC%9D%98-%EC%82%AC%EC%8B%A4%EA%B3%BC-%EC%98%A4%ED%95%B4-%EC%A1%B0%EC%98%81%ED%98%B8
- 구현
  - '책을 대출하라'라는 api를 호출하면 호출 시 제공된 요청정보를 활용해서 책정보를 제공
  - 가상의 도서관이 보유하고 있는 모든 책 정보는 DB에 저장되어 있다고 가정
  - 요청자와 상호협력 없이 현재 대여중이면 예약을 신청하고 보유한 책이 아니라면 구매하는 것으로 구현 제한

- Description
  - Querydsl을 활용하여 동적 쿼리 구현
  
<br>

### jpa-board

- thymleaf layout 적용

<br>

### jpa-association

- Domain relationship <br>
  <img src="https://github.com/JaewookMun/spring-exercise/assets/84655268/14593e78-33cd-43c1-9656-da9a7bf5d355" width="60%">

<br>

- Domain Model <br>
  <img src="https://github.com/JaewookMun/spring-exercise/assets/84655268/a12c9169-ecf2-4461-abf9-28f526ee816d" width="60%">

<br>

- ERD <br>
  <img src="https://github.com/JaewookMun/spring-exercise/assets/84655268/245cba4f-a174-4150-a22e-77e3a30cb891" width="60%">

<br>

---


- Test Reason
  
  - 연관관계를 PK가 아닌 UK로 맺을 때 단순 관계는 문제가 없으나 (부서 & 회원) 부서트리를 구성할 때 의도치 않은 쿼리가 나가는 현상 발생 <br>(@EntityGraph를 사용해 한방쿼리가 나가도록 작성했으나 추가 쿼리 발생 - PK로 연관관계를 맺어 트리를 구성하여 조회 쿼리를 날리면 의도한대로 실행)

<br>

- Description
  
  - 부서트리를 구성하기 위해 상위부서와 연관관계 매핑을 시도하는 key로 PK(id), UK(guid)를 사용

  - DeptPk : 연관관계 키 - PK , DeptUk : 연관관계 키 - UK <br>
    (DeptPk는 guid를 @Id, DeptUk는 guid를 단순 필드로 사용)

  - test/java.example.jpaassociation.entity.EntityTest 내 테스트 케이스를 통해 실행결과 확인


