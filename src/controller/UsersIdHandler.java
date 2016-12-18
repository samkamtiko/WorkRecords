package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.User;
import model.UserHandler;
import view.StaticContent;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.io.IOException;

public class UsersIdHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        String id = request.getMatchedGroup(0);
        UserHandler handler = getUserHandler();
        User user = handler.getById(id);
        HttpResponse response = new HttpResponse(200);
        if (user == null) {
            response.setData("User not found");
        } else {
            try {
                String file = StaticContent.get("userid.html");
                TemplateEngine eng = new TemplateEngine();
                eng.setTemplate(file);
                eng.addVariable("name", user.getName());
                eng.addVariable("id", user.getId());
                eng.addVariable("login", user.getEmail());
                eng.addVariable("password", user.getPassword());
                eng.addVariable("group", "");
                eng.addVariable("salary", "");

                response.setData(eng.generate());
            } catch (IOException e) {
                e.printStackTrace();
                return new HttpResponse(404);
            } catch (MissingParameterException e) {
                e.printStackTrace();
                return new HttpResponse(404);
            }
        }
        return response;
    }
}
