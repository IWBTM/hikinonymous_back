package org.hikinonymous.back.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "org.hikinonymous.back.core.entity")
@ComponentScan(basePackages = {"org.hikinonymous.back.core", "org.hikinonymous.back.cms"})
@EnableJpaRepositories(basePackages = "org.hikinonymous.back.core.repository.*")
@SpringBootApplication
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}