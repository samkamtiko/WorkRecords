package controller;

import http.HttpRequest;
import http.HttpResponse;
import junit.framework.TestCase;

public class UsersIdHandlerTest extends TestCase {

    // TODO: check for html output
    public void testSimpleRequest() {
        UserHandlerFake fakeHandler = new UserHandlerFake();
        fakeHandler.createUser("1");

        UsersIdHandler handler = new UsersIdHandler();
        handler.setUserHandler(fakeHandler);
        HttpRequest req = new HttpRequest();
        req.setType(HttpRequest.HTTPRequestType.GET);
        req.addMatchedGroup("1");

        HttpResponse resp = handler.handleGetRequest(req);
        assertEquals(new Integer(200), resp.getReturnCode());
    }

    public void testMissingUser() {
        UserHandlerFake fakeHandler = new UserHandlerFake();

        UsersIdHandler handler = new UsersIdHandler();
        handler.setUserHandler(fakeHandler);

        HttpRequest req = new HttpRequest();
        req.setType(HttpRequest.HTTPRequestType.GET);
        req.addMatchedGroup("1");

        HttpResponse resp = handler.handleGetRequest(req);
        assertEquals(new Integer(200), resp.getReturnCode());
        assertEquals("User not found", resp.getData());
    }
}
