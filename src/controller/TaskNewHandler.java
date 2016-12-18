package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.TaskHandler;

public class TaskNewHandler extends GenericHandler {

    @Override
    public HttpResponse handlePostRequest(HttpRequest request) {
        TaskHandler handler = getTaskHandler();
        // TODO: implement
        return HttpResponse.notFound();
    }
}
