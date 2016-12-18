package controller;

import http.HttpRequest;
import view.StaticContent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RouteMatcher {

    private static RouteMatcher matcher;

    private static HashMap<String, GenericHandler> handlers;
    private static final String ID = "([a-f\\d]{24})";

    static {
        handlers = new LinkedHashMap<>();
        handlers.put("^/login$", new LoginHandler());

        handlers.put("^/users/" + ID + "$", new UsersIdHandler());
        handlers.put("^/users/new$", new UsersNewHandler());
        handlers.put("^/users/list$", new UsersListHandler());
        handlers.put("^/users/" + ID + "/edit$", new UsersEditHandler());

        handlers.put("^/tasks/" + ID + "$", new TasksIdHandler());
        handlers.put("^/tasks/new$", new TasksNewHandler());
        handlers.put("^/tasks/list$", new TasksListHandler());
        handlers.put("^/tasks/" + ID + "/edit$", new TasksEditHandler());
    }

    public static synchronized RouteMatcher getInstance() {
        if (matcher == null) {
            matcher = new RouteMatcher();
        }

        return matcher;
    }

    private boolean isAuthorized(HttpRequest request) {
        // TODO: check if the user is in a database
        // return !request.getOption("Cookie").equals("");
        return true;
    }

    public GenericHandler getMatch(HttpRequest request) {
        String route = request.getRoute();
        for(String key: handlers.keySet()) {
            Matcher matcher = Pattern.compile(key).matcher(route);
            if (matcher.find()) {
                System.out.println("Match found: " + key);
                // If not login and not authorized, then redirect to login
                if (!route.equals("/login") && !route.equals("/users/new") && !isAuthorized(request)) {
                    System.out.println("Not authorized");
                    return new UnauthorizedHandler();
                }

                for(int idx = 0; idx < matcher.groupCount(); idx += 1) {
                    request.addMatchedGroup(matcher.group(idx + 1));
                }
                return handlers.get(key);
            }
        }

        if (StaticContent.isFileExist(route.substring(1))) {
            // TODO: some arguments could be set into the handler
            return new StaticFileHandler();
        }

        System.out.println("Match not found");
        return new NotFoundHandler();
    }
}