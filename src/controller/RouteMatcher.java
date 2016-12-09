package controller;

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

    public GenericHandler getMatch(String route) {
        for(String key: handlers.keySet()) {
            if (key.equals(route)) {
                System.out.println("Match found: " + key);
                return handlers.get(key);
            }
        }

        // TODO: Should also match static files
        System.out.println("Match not found");
        return new NotFoundHandler();
    }
}