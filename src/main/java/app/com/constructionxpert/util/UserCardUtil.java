package app.com.constructionxpert.util;


import app.com.constructionxpert.enums.UserRole;

public class UserCardUtil {
    public static String getColor(UserRole role) {
        String color = "";
        switch (role) {
            case EMPLOYER_RESPONSIBLE:
                color = "#f58653";
                break;

            case SUPPLIER:
                color = "#46a977";
                break;

            case EMPLOYER_MEMBER:
                color = "#3e2d9b";
                break;
        }

        return color;
    }
}
