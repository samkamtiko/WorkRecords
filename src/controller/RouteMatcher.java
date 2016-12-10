package controller;

import http.HttpRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RouteMatcher {

    private static RouteMatcher matcher;

    private static HashMap<String, GenericHandler> handlers;

    static {
        handlers = new LinkedHashMap<>();
        handlers.put("/login", new LoginHandler());
        handlers.put("/users/[0-9]+", new UsersIdHandler());
        handlers.put("/users/new", new UsersNewHandler());
    }

    public static synchronized RouteMatcher getInstance() {
        if (matcher == null) {
            matcher = new RouteMatcher();
        }

        return matcher;
    }

    private boolean isAuthorized(HttpRequest request) {
        // TODO: check if the user is in a database
        return !request.getOption("Cookie").equals("");
    }

    public GenericHandler getMatch(HttpRequest request) {
        String route = request.getRoute();
        for(String key: handlers.keySet()) {
            // TODO: match should be performed by using regexp
            if (key.equals(route)) {
                System.out.println("Match found: " + key);
                // If not login and not authorized, then redirect to login
                if (!route.equals("/login") && !isAuthorized(request)) {
                    System.out.println("Not authorized");
                    return new UnauthorizedHandler();
                }
                return handlers.get(key);
            }
        }

        // TODO: Should also match static files
        System.out.println("Match not found");
        return new NotFoundHandler();
    }
}