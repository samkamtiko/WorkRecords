package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.Task;
import model.TaskHandler;

import java.io.IOException;
import java.util.HashMap;

public class TasksNewHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        try {
            return HttpResponse.responseStatic("tasksnew.html");
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        }
    }

    @Override
    public HttpResponse handlePostRequest(HttpRequest request) {
        TaskHandler handler = getTaskHandler();
        Task task = new Task();
        HashMap<String, String> params = HttpRequest.parseParams(request.getData());
        task.setName(params.get("name"));
        task.setDescription(params.get("description"));
        handler.addTask(task);
        // TODO: implement
        return HttpResponse.redirect("/tasks/list", "");
    }
}
