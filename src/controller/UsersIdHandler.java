package controller;

import http.HttpRequest;
import http.HttpResponse;

public class UsersIdHandler implements GenericHandler {

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        return new HttpResponse(200);
    }
}
