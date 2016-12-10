package http;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HttpRequest {

    public enum HTTPRequestType {
        GET,
        POST,
        PUT;

        public static HTTPRequestType fromString(String type) throws IOException {
            switch (type) {
                case "GET":
                    return HTTPRequestType.GET;
                case "POST":
                    return HTTPRequestType.POST;
                case "PUT":
                    return HTTPRequestType.PUT;
                default:
                    throw new IOException("Unsupported type");
            }
        }
    }

    public enum HTTPProtocol {
        HTTP1_0,
        HTTP1_1;

        public static HTTPProtocol fromString(String proto) throws IOException {
            switch (proto) {
                case "HTTP/1.0":
                    return HTTPProtocol.HTTP1_0;
                case "HTTP/1.1":
                    return HTTPProtocol.HTTP1_1;
                default:
                    throw new IOException("Unknown protocol");
            }
        }
    }

    private HashMap<String, String> mHeader;
    private String mData;
    private HTTPRequestType mType;
    private String mRoute;
    private HTTPProtocol mProtocol;

    public HttpRequest() {
        mHeader = new LinkedHashMap<>();
    }

    public void setType(HTTPRequestType type) {
        mType = type;
    }

    public HTTPRequestType getType() {
        return mType;
    }

    public void addOption(String name, String value) {
        mHeader.put(name, value);
    }

    public String getOption(String name) {
        if (mHeader.containsKey(name)) {
            return mHeader.get(name);
        }
        return "";
    }

    public void setData(String data) {
        mData = data;
    }

    public String getData() {
        return mData;
    }

    public void setProtocol(HTTPProtocol proto) {
        mProtocol = proto;
    }

    public HTTPProtocol getProtocol() {
        return mProtocol;
    }

    public void setRoute(String route) {
        mRoute = route;
    }

    public String getRoute() {
        return mRoute;
    }

    @Override
    public String toString() {
        String output = "HttpRequest: \n";

        output += mType.toString() + "\n";
        output += mRoute.toString() + "\n";
        output += mProtocol.toString() + "\n";

        for(String key : mHeader.keySet()) {
            output += key + ": " + mHeader.get(key) + "\n";
        }

        output += "DATA: \n";
        output += mData + "\n";

        return output;
    }

}