package controller;

import http.HttpRequest;
import http.HttpResponse;

public class UsersIdHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        System.out.println("USERID handler: !!!" + request.getMatchedGroup(0));
        return super.handleGetRequest(request);
    }
}
