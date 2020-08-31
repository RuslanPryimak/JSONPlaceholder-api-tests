package com.epam.ta.model.post;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Post {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
