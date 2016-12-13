package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.User;
import model.UserHandler;
import model.UserHandlerFactory;

import java.util.HashMap;

public class UsersNewHandler extends GenericHandler {

    @Override
    public HttpResponse handlePostRequest(HttpRequest request) {
        UserHandler handler = UserHandlerFactory.getInstance().getUserHandler();
        HashMap<String, String> params = HttpRequest.parseParams(request.getData());
        User user = new User();
        user.setLogin(params.get("login"));
        user.setPassword(params.get("password"));
        user.setName(params.get("name"));
        handler.addUser(user);
        return HttpResponse.redirect("/users/" + user.getId(), user.getId());
    }
}