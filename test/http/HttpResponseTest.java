package http;

import junit.framework.TestCase;

public class HttpResponseTest extends TestCase {

    public void testReturnCode() {
        HttpResponse response = new HttpResponse(200);
        assertEquals(response.getReturnCode().intValue(), 200);
    }
}
