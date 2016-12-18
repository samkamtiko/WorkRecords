package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.TaskHandler;
import model.UserHandler;

public abstract class GenericHandler {

    private UserHandler userHandler;
    private TaskHandler taskHandler;

    public void setUserHandler(UserHandler handler) {
        userHandler = handler;
    }
    public void setTaskHandler(TaskHandler handler) {
        taskHandler = handler;
    }

    public UserHandler getUserHandler() {
        return userHandler;
    }
    public TaskHandler getTaskHandler() {
        return taskHandler;
    }

    public HttpResponse handleGetRequest(HttpRequest request) {
        return HttpResponse.notFound();
    }

    public HttpResponse handlePostRequest(HttpRequest request) {
        return HttpResponse.notFound();
    }
}