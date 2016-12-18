package controller;

import http.HttpRequest;
import http.HttpResponse;
import junit.framework.TestCase;

public class TasksIdHandlerTest extends TestCase {

    public void testSimpleRequest() {
        TaskHandlerFake fakeHandler = new TaskHandlerFake();
        fakeHandler.createTask();

        TasksIdHandler handler = new TasksIdHandler();
        handler.setTaskHandler(fakeHandler);

        HttpRequest req = new HttpRequest();
        req.addMatchedGroup("1");

        HttpResponse resp = handler.handleGetRequest(req);
        assertEquals(new Integer(200), resp.getReturnCode());

    }

}