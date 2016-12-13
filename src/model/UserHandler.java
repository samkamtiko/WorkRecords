package model;

import java.util.ArrayList;

public interface UserHandler {
    User getById(String id);
    ArrayList<User> getListOfUsert();
    void addUser(User user);
}
