package com.voxloud.reactive.controller;

import java.time.Duration;
import java.util.stream.IntStream;

import com.voxloud.reactive.client.Post;
import com.voxloud.reactive.client.PostWithUser;
import com.voxloud.reactive.client.TypicodeClient;
import com.voxloud.reactive.client.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class NumberController {

    @Autowired
    private TypicodeClient typicodeClient;

    @GetMapping(value = "/numbers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getNumbers() {

        return Flux.fromStream(IntStream.range(0, 10).boxed()
                                        .peek((number) -> {
                                            log.info("int: " + number);
                                        }))
                   .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "/aggregate")
    public Flux<PostWithUser> getAggregate() {
        return typicodeClient.getPosts().flatMap(post -> {
            log.info("Post: " + post.getId());
            return typicodeClient.getUser(post.getUserId()).flatMap(user -> {
                log.info("User: " + user.getId());
                PostWithUser postWithUser = new PostWithUser();
                postWithUser.setId(post.getId());
                postWithUser.setBody(post.getBody());
                postWithUser.setTitle(post.getTitle());
                postWithUser.setUser(user);
                return Mono.just(postWithUser);
            });
        });
    }
}
