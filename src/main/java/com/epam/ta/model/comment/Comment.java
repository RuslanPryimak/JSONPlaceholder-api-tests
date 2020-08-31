package com.epam.ta.model.comment;

import lombok.Data;

@Data
public class Comment {

    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;

}
