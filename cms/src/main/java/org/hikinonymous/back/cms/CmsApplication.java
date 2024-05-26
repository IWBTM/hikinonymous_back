package org.hikinonymous.back.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "org.hikinonymous.back.core.entity")
@SpringBootApplication
@ComponentScan(basePackages = {"org.hikinonymous.back.core", "org.hikinonymous.back.core"})
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}