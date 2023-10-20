package com.example.demo;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = VideoRepositoryTestcontainersTest.DataSourceInitializer.class)
public class VideoRepositoryTestcontainersTest {

    @Autowired VideoRepository repository;
    @Container
    static final PostgreSQLContainer<?> database =
            new PostgreSQLContainer<>("postgres:13.12")
                    .withUsername("postgres");

    static class DataSourceInitializer //
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, //
                    "spring.datasource.url=" + database.getJdbcUrl(), //
                    "spring.datasource.username=" + database.getUsername(), //
                    "spring.datasource.password=" + database.getPassword(), //
                    "spring.jpa.hibernate.ddl-auto=create-drop");
        }
    }
    @BeforeEach
    void setUp(){
        repository.saveAll(
                List.of(
                        new VideoEntity(
                                "alicja",
                                "Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up."
                        ),
                        new VideoEntity(
                                "bob",
                                "Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up."
                        ),
                        new VideoEntity(
                                "alicja",
                                "Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up."
                        )));
    }
    @Test
    void findAllShouldProduceAllVideos(){
        List<VideoEntity> videos = repository.findAll();
        assertThat(videos).hasSize(3);
    }
}
