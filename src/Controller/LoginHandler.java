package Controller;

import Http.HttpRequest;
import Http.HttpResponse;

public class LoginHandler implements GenericHandler {

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        HttpResponse response = new HttpResponse(200);
        response.setData("<html>\n" +
                "<header><title>This is title</title></header>\n" +
                "<body>\n" +
                "Login\n" +
                "</body>\n" +
                "</html>");
        return response;
    }
}