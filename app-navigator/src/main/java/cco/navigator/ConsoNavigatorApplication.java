package cco.navigator;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("cco.navigator")
@EnableJpaRepositories("cco.navigator.repository")
@EntityScan("cco.navigator.dao")
@EnableTransactionManagement
public class ConsoNavigatorApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ConsoNavigatorApplication.class);
    app.run(args);
  }

  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }
}
