package Controller;

import Http.HttpRequest;
import Http.HttpResponse;

public interface GenericHandler {

    HttpResponse handleRequest(HttpRequest request);
}