package controller;

import http.HttpRequest;
import http.HttpResponse;

import java.io.IOException;

public class StaticFileHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        try {
            String filename = request.getRoute().substring(1);
            return HttpResponse.responseStatic(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        }
    }
}
