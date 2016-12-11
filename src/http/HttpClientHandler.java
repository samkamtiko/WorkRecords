package http;

import controller.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClientHandler implements Runnable {

    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private InputStreamReader mInputStreamReader;
    private HttpRequest mPartialRequest;

    public HttpClientHandler(Socket socket) throws IOException {
        mSocket = socket;

        mOutputStream = mSocket.getOutputStream();
        mInputStream = mSocket.getInputStream();

        mInputStreamReader = new InputStreamReader(mInputStream);
        mPartialRequest = null;
    }

    private void handleRequest(byte[] buffer, int numBytes) throws IOException {
        HttpRequest request;
        if (mPartialRequest != null) {
            mPartialRequest.setData(HttpParser.parseData(buffer, numBytes));
            request = mPartialRequest;
            request.setPartial(false);
            mPartialRequest = null;
        } else {
            request = HttpParser.parseRequest(numBytes, buffer);
            if (request.isPartial()) {
                mPartialRequest = request;
                return;
            }
        }
        System.out.println(request);
        HttpResponse response = Controller.processRequest(request);
        sendResponse(response);
    }

    private void sendResponse(HttpResponse response) throws IOException {
        mOutputStream.write(response.asByteArray());
    }

    @Override
    public void run() {
        byte[] buffer = new byte[HttpParser.HTTP_REQUEST_MAX_SIZE];
        while(true) {
            try {
                int numBytes = mInputStream.read(buffer);
                if (numBytes == -1) {
                    return;
                }
                handleRequest(buffer, numBytes);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

    }
}