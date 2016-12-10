package controller;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpServer;

public class UnauthorizedHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        return HttpResponse.redirect("/login");
    }
}
