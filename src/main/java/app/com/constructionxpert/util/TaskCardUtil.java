package app.com.constructionxpert.util;

import app.com.constructionxpert.enums.ProjectStatus;
import app.com.constructionxpert.enums.TaskStatus;

public class TaskCardUtil {
    public static String getColor(TaskStatus status) {
        String color = "";
        switch (status) {
            case COMPLETED:
                color = "#46a977";
                break;

            case IN_PROGRESS:
                color = "#f58653";
                break;
            case CANCELED:
                color = "#fc5656";
                break;

            case PENDING:
                color = "#3e2d9b";
                break;
        }

        return color;



    }
    public static String getIcon(TaskStatus status) {


        String icon = "";
        switch (status) {
            case COMPLETED:
                icon = "fas fa-check-circle";
                break;

            case IN_PROGRESS:
                icon = "fas fa-spinner";
                break;

            case CANCELED:
                icon = "fas fa-times-circle";
                break;

            case PENDING:
                icon = "fas fa-clipboard-list";
                break;
        }

        return icon;
    }
}
