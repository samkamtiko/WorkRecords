package controller;

import model.User;
import model.UserGroup;
import model.UserHandler;

import java.util.ArrayList;

public class UserHandlerFake implements UserHandler {

    private ArrayList<User> users;

    public UserHandlerFake() {
        users = new ArrayList<>();
    }

    @Override
    public User getById(String id) {
        for(User user: users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
     }

    @Override
    public ArrayList<User> getListOfUsert() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(String id, User user) {
        User usr = null;
        for(User u: users) {
            if (u.getId().equals(id)) {
                usr = u;
                break;
            }
        }
        if (usr == null) {
            return;
        }

        usr.setName(user.getName());
        usr.setSalary(user.getSalary());
        usr.setPassword(user.getPassword());
        usr.setGroup(user.getGroup());
        usr.setEmail(user.getEmail());
    }

    public void createUser(String id) {
        User user = new User();
        user.setId(id);
        user.setName("name");
        user.setPassword("password");
        user.setSalary(0);
        user.setEmail("login");
        user.setGroup(UserGroup.PERFORMER);
        addUser(user);
    }
 }
