package model;

import java.util.ArrayList;

public class TaskHandlerMongoDB implements TaskHandler {

    private static TaskHandlerMongoDB instance = new TaskHandlerMongoDB();

    public static TaskHandlerMongoDB getInstance() {
        return instance;
    }

    @Override
    public Task getById(String id) {
        return null;
    }

    @Override
    public ArrayList<Task> getListForUser(User user) {
        return null;
    }

    @Override
    public void addTask(Task task) {

    }
}
