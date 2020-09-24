package com.epam.ta.model.user;

public enum UserData {
    LEANNE_GRAHAM(1, "Leanne Graham", "Bret"),
    ERVIN_HOWELL(2, "Ervin Howell", "Antonette"),
    CLEMENTINE_BAUCH(3, "Clementine Bauch", "Samantha"),
    PATRICIA_LEBSACK(4, "Patricia Lebsack", "Karianne"),
    CHELSEY_DIETRICH(5, "Chelsey Dietrich", "Kamren");

    private int userId;

    private String name;

    private String username;

    UserData(int userId, String name, String username) {
        this.userId = userId;
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
