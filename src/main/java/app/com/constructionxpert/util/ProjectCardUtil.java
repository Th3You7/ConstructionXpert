package app.com.constructionxpert.util;

import app.com.constructionxpert.enums.ProjectStatus;

public class ProjectCardUtil {
    public static String getColor(ProjectStatus status) {
        String color = "";
        switch (status) {
            case COMPLETED:
                color = "#46a977";
                break;

            case IN_PROGRESS:
                color = "#f58653";
                break;

            case ON_HOLD:
                color = "#94c21f";
                break;

            case CANCELLED:
                color = "#fc5656";
                break;

            case PLANNING:
                color = "#3e2d9b";
                break;
        }

        return color;



    }
    public static String getIcon(ProjectStatus status) {


        String icon = "";
        switch (status) {
            case COMPLETED:
                icon = "fas fa-check-circle";
                break;

            case IN_PROGRESS:
                icon = "fas fa-spinner";
                break;

            case ON_HOLD:
                icon = "fas fa-pause-circle";
                break;

            case CANCELLED:
                icon = "fas fa-times-circle";
                break;

            case PLANNING:
                icon = "fas fa-clipboard-list";
                break;
        }

        return icon;
    }
}
