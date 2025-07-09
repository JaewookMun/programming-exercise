# Testcontainers with MariaDB

MariaDB를 서비스 컨테이너로 사용하는 Testcontainers 샘플 코드입니다.

com.github.jaewookmun.testcontainers.FruitRepositoryContainerTest 테스트 클래스를 확인해주세요.


[비고]
1. 의존성
```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
	annotationProcessor 'org.projectlombok:lombok'

	// test
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
//	testImplementation 'org.springframework.boot:spring-boot-testcontainers'
	testImplementation platform('org.testcontainers:testcontainers-bom:1.19.3')
	testImplementation 'org.testcontainers:testcontainers'
	testImplementation 'org.testcontainers:junit-jupiter'
	testImplementation 'org.testcontainers:mariadb'
}
```

2. Testcontainers 적용 코드
```java
@SpringBootTest
@Testcontainers
public class FruitRepositoryContainerTest {
    @Autowired
    FruitRepository fruitRepository;

    @Container
    static MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb:latest")
            .withDatabaseName("market")
            .withUsername("tester")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariadb::getJdbcUrl);
        registry.add("spring.datasource.username", mariadb::getUsername);
        registry.add("spring.datasource.password", mariadb::getPassword);
    }

    @Test
    void save2() {
        Fruit fruit = new Fruit("Banana", "yellow");
        fruitRepository.save(fruit);
    }
}
```