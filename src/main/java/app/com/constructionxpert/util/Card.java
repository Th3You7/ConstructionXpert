package app.com.constructionxpert.util;

import app.com.constructionxpert.entity.User;
import app.com.constructionxpert.enums.ProjectStatus;
import app.com.constructionxpert.enums.TaskStatus;
import app.com.constructionxpert.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private ProjectStatus projectStatus;
    private TaskStatus taskStatus;
    private long count;
    private String color;
    private String icon;
    private UserRole role;

    public Card(String color, TaskStatus status, long count, String icon) {
        this.color = color;
        this.taskStatus = status;
        this.count = count;
        this.icon = icon;
    }

    public Card(String color, ProjectStatus status, long count, String icon) {
        this.color = color;
        this.projectStatus = status;
        this.count = count;
        this.icon = icon;
    }

    public Card(String color, UserRole role, long count) {
        this.color = color;
        this.role = role;
        this.count = count;
    }


}
