package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.User;
import model.UserHandler;
import model.UserHandlerFactory;
import model.UserHandlerMongoDB;

public class UsersIdHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        String id = request.getMatchedGroup(0);
        UserHandler handler = UserHandlerFactory.getInstance().getUserHandler();
        User user = handler.getById(id);
        HttpResponse response = new HttpResponse(200);
        if (user != null) {
            response.setData(user.toString());
        } else {
            response.setData("User not found");
        }
        return response;
    }
}
