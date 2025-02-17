package community.whatever.onembackendjava;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = MysqlTestContainer.ContainerConfig.class)
public class MysqlTestContainer {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER =
        new MySQLContainer<>("mysql:8.0.41")
            .withDatabaseName("mysql")
            .withUsername("root")
            .withPassword("root")
            .withInitScript("init.sql");


    public static class ContainerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + MY_SQL_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + MY_SQL_CONTAINER.getUsername(),
                "spring.datasource.password=" + MY_SQL_CONTAINER.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
