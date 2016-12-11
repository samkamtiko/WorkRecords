package http;

import java.io.*;

public class HttpParser {

    public static final int HTTP_REQUEST_MAX_SIZE = 8192;

    public static HttpRequest parseRequest(int numBytes, byte[] data) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(data, 0, numBytes)));
        String header = reader.readLine();
        String[] splittedHeader = header.split(" ");

        HttpRequest request = new HttpRequest();

        HttpRequest.HTTPRequestType type =
                HttpRequest.HTTPRequestType.fromString(splittedHeader[0]);
        request.setType(type);

        HttpRequest.HTTPProtocol proto =
                HttpRequest.HTTPProtocol.fromString(splittedHeader[2]);
        request.setProtocol(proto);

        String route = splittedHeader[1];
        request.setRoute(route);

        while(true) {
            String line = reader.readLine();
            if (!line.equals("")) {
                String[] values = line.split(":");
                request.addOption(values[0], values[1].substring(1));
            } else {
                // Empty string, and data after that
                String httpData = "";
                while(reader.ready()) {
                    httpData += reader.readLine();
                }
                request.setData(httpData);
                break;
            }
        }

        // For now assume that request could be only split into headers/data
        if (request.getData().length() != request.getContentLength()) {
            request.setPartial(true);
        }

        return request;
    }

    public static String parseData(byte[] data, int numBytes) throws IOException {
        String res = "";
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(data, 0, numBytes)));

        while(reader.ready()) {
            String line = reader.readLine();
            res += line;
        }
        return res;
    }
}