package Model;

public class UserHandlerFactory {
    private static UserHandlerFactory ourInstance = new UserHandlerFactory();

    public static UserHandlerFactory getInstance() {
        return ourInstance;
    }

    public UserHandler getUserHandler() {
        return UserHandler.getInstance();
    }

    private UserHandlerFactory() {
    }
}
