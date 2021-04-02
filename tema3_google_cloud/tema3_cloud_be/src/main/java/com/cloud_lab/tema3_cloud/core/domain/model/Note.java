package com.cloud_lab.tema3_cloud.core.domain.model;

public class Note {
    private final int id;

    private final String title;

    private final String description;

    private final int userId;

    public Note(int id, String title, String description, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }
}
