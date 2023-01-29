### KH JAVA B3과정 Semi Project <Onion Talk>

### 프로젝트 패키지 구조

```bash
\---main
    +---java
    |   +---client
    |   |   +---thread
    |   |   \---view
    |   +---model
    |   +---server
    |   |   \---thread
    |   \---util
    |       +---command
    |       +---dto
    |       \---oracle
    \---resources
        \---images
```

### 참고 자료

- 현재 프로젝트 Java 버전 : `OpenJDK 11 GA (build 11+28)`
- 참조 링크
  - [Maven Repository](https://mvnrepository.com/)
  - [OpenJDK Archive](https://jdk.java.net/archive/)
  - [Log4j2](https://logging.apache.org/log4j/2.x/)
  - [Lombok](https://projectlombok.org/features/)

---

### 유틸리티

| dependency | version |                                     download link                                     |
| :--------- | :-----: | :-----------------------------------------------------------------------------------: |
| log4j-core | 2.17.1  | [링크](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.17.1) |
| log4j-api  | 2.17.1  | [링크](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api/2.17.1)  |
| lombok     | 1.18.24 |      [링크](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.24)      |

### Database 커넥션 주의사항

DB 연결정보는 리포에 업로드 되어있지 않으며 `src/main/resources` 경로에 `connection.properties` 파일 생성 필요
