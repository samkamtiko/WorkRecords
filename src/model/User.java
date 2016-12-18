package model;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private UserGroup group;
    private Integer salary;

    public User() {
        salary = 0;
        group = UserGroup.PERFORMER;
        password = "";
        id = "";
        name = "";
        email = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String out = "";
        out += name + "\n";
        out += email + "\n";
        out += password + "\n";
        return out;
    }
}
