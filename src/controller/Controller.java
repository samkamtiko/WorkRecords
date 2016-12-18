package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.TaskHandler;
import model.TaskHandlerFactory;
import model.UserHandler;
import model.UserHandlerFactory;

public class Controller {

    private static UserHandler userHandler = UserHandlerFactory.getInstance().getUserHandler();
    private static TaskHandler taskHandler = TaskHandlerFactory.getInstance().getTaskHandler();

    public static HttpResponse processRequest(HttpRequest request) {
        RouteMatcher matcher = RouteMatcher.getInstance();
        GenericHandler handler = matcher.getMatch(request);
        handler.setUserHandler(userHandler);
        handler.setTaskHandler(taskHandler);
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
