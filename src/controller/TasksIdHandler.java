package controller;

import http.HttpRequest;
import http.HttpResponse;

public class TasksIdHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        String id = request.getMatchedGroup(0);

        // FIXME: update
        HttpResponse resp = new HttpResponse(200);
        resp.setData("Task not found");
        return resp;
    }
}
