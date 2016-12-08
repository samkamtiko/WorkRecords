package Http;

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
                request.addOption(values[0], values[1]);
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

        return request;
    }
}