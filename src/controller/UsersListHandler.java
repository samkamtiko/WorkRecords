package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.User;
import model.UserHandler;
import view.StaticContent;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UsersListHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        UserHandler handler = getUserHandler();
        ArrayList<User> users = handler.getListOfUsert();
        ArrayList<String> userNames = users.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(ArrayList::new));

        try {
            String respHtml = StaticContent.get("listofusers.html");
            TemplateEngine eng = new TemplateEngine();
            eng.setTemplate(respHtml);
            eng.addCollection("users", userNames);
            String renderedHtml = eng.generate();
            HttpResponse resp = new HttpResponse(200);
            resp.setData(renderedHtml);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        } catch (MissingParameterException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        }
    }
}
