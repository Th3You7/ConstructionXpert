package app.com.constructionxpert.util;


import app.com.constructionxpert.enums.UserRole;

public class UserCardUtil {
    public static String getColor(UserRole role) {
        String color = "";
        switch (role) {
            case EMPLOYER_MEMBER:
                color = "#f58653";
                break;

            case SUPPLIER:
                color = "#f58653";
                break;

            case EMPLOYER_RESPONSIBLE:
                color = "#3e2d9b";
                break;
        }

        return color;
    }
}
