package controller;

import http.HttpRequest;
import http.HttpResponse;

public class Controller {

    public static HttpResponse processRequest(HttpRequest request) {
        RouteMatcher matcher = RouteMatcher.getInstance();
        GenericHandler handler = matcher.getMatch(request);
        switch(request.getType()) {
            case GET:
                return handler.handleGetRequest(request);
            case POST:
                return handler.handlePostRequest(request);
            default:
                return HttpResponse.notFound();
        }
    }
}
