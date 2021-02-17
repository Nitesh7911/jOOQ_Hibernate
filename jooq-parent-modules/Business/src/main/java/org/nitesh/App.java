package org.nitesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories("com.nitesh.domain.*")
//@ComponentScan(basePackages = {"com.nitesh.domain.*", "org.business.*"})
@SpringBootApplication
//@EntityScan("com.nitesh.domain.*")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}