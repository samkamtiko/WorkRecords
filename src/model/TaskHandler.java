package model;

import java.util.ArrayList;

public interface TaskHandler {

    Task getById(String id);
    ArrayList<Task> getListForUser(User user);
    void addTask(Task task);
}
