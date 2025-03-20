package app.com.constructionxpert.exception;

public class UserEmailNoExistException extends RuntimeException {
    public UserEmailNoExistException(String message) {
        super(message);
    }
}
