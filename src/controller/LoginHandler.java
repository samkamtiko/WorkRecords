package controller;

import http.HttpRequest;
import http.HttpResponse;
import view.StaticContent;

import java.io.IOException;
import java.util.HashMap;

public class LoginHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        HttpResponse response;
        try {
            response = new HttpResponse(200);
            response.setData(StaticContent.get("login.html"));
        } catch (IOException e) {
            e.printStackTrace();
            response = HttpResponse.notFound();
        }
        return response;
    }

    @Override
    public HttpResponse handlePostRequest(HttpRequest request) {
        HashMap<String, String> params = HttpRequest.parseParams(request.getData());
        System.out.println("LOGIN: " + params.get("login") + " PASSWD: " + params.get("password"));
        return HttpResponse.redirect("/users/1", params.get("login"));
    }
}