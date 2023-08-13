## Practice on JPA in Spring
JPA를 활용한 스프링 프로젝트 저장소

<br>

### 1. Abstract

    - jpa-board : simple board function using Spring Data jpa framework
    - jpa-association : jpa association test on PK and UK condition

<br><br>

### 2. Details

### jpa-board

<br>

### jpa-association

- Domain relationship
  
  ![image](https://github.com/JaewookMun/spring-exercise/assets/84655268/14593e78-33cd-43c1-9656-da9a7bf5d355)

<br>

- Domain Model
  
  ![image](https://github.com/JaewookMun/spring-exercise/assets/84655268/a12c9169-ecf2-4461-abf9-28f526ee816d)

<br>

- ERD
  
  ![image](https://github.com/JaewookMun/spring-exercise/assets/84655268/245cba4f-a174-4150-a22e-77e3a30cb891)

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


