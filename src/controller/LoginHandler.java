package controller;

import http.HttpRequest;
import http.HttpResponse;
import view.StaticContent;

import java.io.IOException;

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
}