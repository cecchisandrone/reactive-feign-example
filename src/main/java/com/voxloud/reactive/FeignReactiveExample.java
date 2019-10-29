package com.voxloud.reactive;

import com.voxloud.reactive.client.TypicodeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication(exclude = ReactiveLoadBalancerAutoConfiguration.class)
@EnableReactiveFeignClients(clients = TypicodeClient.class)
public class FeignReactiveExample {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignReactiveExample.class).properties(java.util.Collections.singletonMap("server.port", "8081"))
                                                                .run(args);
    }
}
