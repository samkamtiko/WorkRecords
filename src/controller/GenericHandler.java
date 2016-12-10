package controller;

import http.HttpRequest;
import http.HttpResponse;

public abstract class GenericHandler {

    public HttpResponse handleGetRequest(HttpRequest request) {
        return HttpResponse.notFound();
    }

    public HttpResponse handlePostRequest(HttpRequest request) {
        return HttpResponse.notFound();
    }
}