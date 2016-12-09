package controller;

import http.HttpRequest;
import http.HttpResponse;

public class NotFoundHandler implements GenericHandler {
    
    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        return new HttpResponse(404);
    }
}
