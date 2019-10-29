package com.voxloud.reactive.client;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class PostWithUser {

    private int id;

    private String title;

    private String body;

    private User user;
}
