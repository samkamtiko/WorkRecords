package controller;

import model.Task;
import model.TaskHandler;
import model.TaskState;
import model.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskHandlerFake implements TaskHandler {

    private ArrayList<Task> tasks;

    public TaskHandlerFake() {
        tasks = new ArrayList<>();
    }

    @Override
    public Task getById(String id) {
        for(Task task: tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Task> getListForUser(User user) {
        return tasks.stream()
                .filter(task -> task.getAssigner().getId().equals(user.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void createTask() {
        Task task = new Task();
        task.setId("1");
        task.setName("Task");
        task.setDescription("Simple task");

        User assigner = new User();
        assigner.setId("2");
        assigner.setName("Name");
        task.setAssigner(assigner);

        task.setSalaryForTask(10);
        task.setState(TaskState.CREATED);

        addTask(task);
    }
}
