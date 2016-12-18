package http;

import junit.framework.TestCase;

import java.util.HashMap;

public class HttpRequestTest extends TestCase {

    public void testParseParams() {
        String paramString = "name=abc&type=10";
        HashMap<String, String> params = HttpRequest.parseParams(paramString);
        assertEquals("abc", params.get("name"));
        assertEquals("10", params.get("type"));
    }

    public void testParseEmptyParams() {
        String paramString = "name=abc&type=";
        HashMap<String, String> params = HttpRequest.parseParams(paramString);
        assertEquals("abc", params.get("name"));
        assertEquals("", params.get("type"));
    }
}
