package controller;

import http.HttpRequest;
import http.HttpResponse;

public class UsersNewHandler implements GenericHandler {

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        return new HttpResponse(200);
    }
}