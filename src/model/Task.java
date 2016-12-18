package model;

public class Task {
    private String id;
    private String name;
    private String description;
    private User assigner;
    private Integer salaryForTask;
    private TaskState state;

    public Task() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public Integer getSalaryForTask() {
        return salaryForTask;
    }

    public void setSalaryForTask(Integer salaryForTask) {
        this.salaryForTask = salaryForTask;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
