package lk.techtalks.consul.client.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ConsulClientDemoApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder(ConsulClientDemoApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        return args -> {
            for (int i=0;i<10;i++) {
                executorService.submit(() -> {
                    String webServiceResponse = restTemplate.getForObject("http://web/", String.class);
                    System.out.println("Web Response for Request : " + webServiceResponse);
                });
            }
        };
    }
}
