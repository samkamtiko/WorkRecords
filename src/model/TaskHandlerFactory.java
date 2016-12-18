package model;

/**
 * Created by ilya on 12/18/16.
 */
public class TaskHandlerFactory {
    private static TaskHandlerFactory ourInstance = new TaskHandlerFactory();

    public static TaskHandlerFactory getInstance() {
        return ourInstance;
    }

    public TaskHandler getTaskHandler() {
        return TaskHandlerMongoDB.getInstance();
    }

    private TaskHandlerFactory() {
    }
}
