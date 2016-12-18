package model;

import java.util.ArrayList;

public interface TaskHandler {

    Task getById(String id);
    ArrayList<Task> getListForUser(User user);
    ArrayList<Task> getAllTasks();
    void addTask(Task task);
    void updateTask(Task task);
}
