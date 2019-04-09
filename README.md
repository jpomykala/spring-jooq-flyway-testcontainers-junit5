# spring-jooq-flyway-testcontainers-junit5
Example project with configured Spring Boot, JooQ, TestContainers, flyway (as maven plugin) MySQL container and JUnit5 🚀

### Used versions
```xml
<spring-boot.version>2.1.1</spring-boot.version>
<java.version>11</java.version>
<org.flyway.version>5.2.4</org.flyway.version>
<org.jooq.version>3.11.7</org.jooq.version>
<mysql-connector-java.version>8.0.11</mysql-connector-java.version>
<testcontainers.version>1.10.5</testcontainers.version>
```


### Running

`mvn clean test`

Test loads firs migation `V1__Init.sql` manually. 



### Main test configuration

```java
@ContextConfiguration(initializers = AbstractTestConfiguration.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@JooqTest
public abstract class AbstractTestConfiguration {

  @ClassRule
  public static MySQLContainer mysql = new MySQLContainer().withDatabaseName("playground");


  public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
      mysql.start();
      configurableApplicationContext.getEnvironment().getSystemProperties().put("spring.datasource.url", mysql.getJdbcUrl() + "?useSSL=false");
      configurableApplicationContext.getEnvironment().getSystemProperties().put("spring.datasource.username", mysql.getUsername());
      configurableApplicationContext.getEnvironment().getSystemProperties().put("spring.datasource.password", mysql.getPassword());
    }
  }
}
```
