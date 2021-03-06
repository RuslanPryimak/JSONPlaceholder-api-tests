package com.epam.ta.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public Post(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Post(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}