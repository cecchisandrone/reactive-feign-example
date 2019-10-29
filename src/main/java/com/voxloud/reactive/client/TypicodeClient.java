package com.voxloud.reactive.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "typicode-client", url = "https://jsonplaceholder.typicode.com")
public interface TypicodeClient {

    @GetMapping("/users")
    Flux<User> getUsers();

    @GetMapping("/users/{id}")
    Mono<User> getUser(@PathVariable Integer id);

    @GetMapping("/posts")
    Flux<Post> getPosts();
}