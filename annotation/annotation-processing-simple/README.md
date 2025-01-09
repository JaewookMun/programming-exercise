# annotation processor 사용 연습

실제 annotation processing 적용 전 사용방식을 익히기 위한 샘플 프로젝트

cf. gradle 에서 멀티 모듈을 사용

settings.gradle 표기사항
``` text
include 'processor'
```

build.gradle 표기사항
```text
dependencies {
    compileOnly project(':processor')
    annotationProcessor project(':processor')
    ...
    
```

':processor' 에서 콜론(:)은 루트 프로젝트를 의미하며 project(':processor')는 루트 프로젝트 하위에 있는 모듈을 가리킨다.


## References
- Baeldung tutorial <br>
  https://www.baeldung.com/java-annotation-processing-builder
- Medium guid <br>
  https://medium.com/@AlexanderObregon/java-annotation-processors-enhancing-code-at-compile-time-633b40e63521