package dev.kcterala.template;

import dev.kcterala.template.configs.cache.CacheProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(CacheProperties.class)
@SpringBootApplication
public class TemplateApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

}
