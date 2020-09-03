package com.epam.ta.model.todo;

import lombok.Data;

@Data
public class Todo {

    private Integer userId;
    private Integer id;
    private String title;
    private boolean completed;

}
