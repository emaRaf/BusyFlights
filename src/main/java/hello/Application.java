package hello;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import crazyair.AppProperties;
import service.ServiceFactory;

@SpringBootApplication
public class Application {

    @Autowired
    ServiceFactory serviceFactory;

    @Autowired
    private AppProperties appProperties;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServiceFactory getServiceFactory() {
	return new ServiceFactory(getAppProperties());
    }

    @Bean
    public AppProperties getAppProperties() {
	return new AppProperties();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	return args -> {

	    System.out.println("Let's inspect the beans provided by Spring Boot:");

	    final String[] beanNames = ctx.getBeanDefinitionNames();
	    Arrays.sort(beanNames);
	    for (final String beanName : beanNames) {
		System.out.println(beanName);
	    }

	};
    }

}