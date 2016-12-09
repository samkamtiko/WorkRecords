package controller;

import http.HttpRequest;
import http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginHandler implements GenericHandler {

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        HttpResponse response;
        try {
            response = new HttpResponse(200);
            response.setData(new String(Files.readAllBytes(
                    Paths.get(new File("").getAbsolutePath() + "/src/view/static/login.html"))));
        } catch (IOException e) {
            e.printStackTrace();
            response = new HttpResponse(404);
        }
        return response;
    }
}