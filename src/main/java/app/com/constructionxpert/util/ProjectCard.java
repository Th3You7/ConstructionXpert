package app.com.constructionxpert.util;

import app.com.constructionxpert.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCard {
    private ProjectStatus status;
    private long count;
    private String color;
    private String icon;

    public ProjectCard(String color, ProjectStatus status, long count, String icon) {
        this.color = color;
        this.status = status;
        this.count = count;
        this.icon = icon;
    }


}
