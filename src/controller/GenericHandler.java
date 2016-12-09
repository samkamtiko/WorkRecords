package controller;

import http.HttpRequest;
import http.HttpResponse;

public interface GenericHandler {

    HttpResponse handleRequest(HttpRequest request);
}