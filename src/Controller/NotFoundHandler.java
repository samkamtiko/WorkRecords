package Controller;

import Http.HttpRequest;
import Http.HttpResponse;

public class NotFoundHandler implements GenericHandler {

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        return new HttpResponse(404);
    }
}
