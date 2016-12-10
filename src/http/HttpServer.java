package http;

import java.io.IOException;
import java.net.*;

public class HttpServer {

    private InetAddress mAddress;
    private int mPort;

    private static HttpServer ourInstance = new HttpServer();

    public static HttpServer getInstance() {
        return ourInstance;
    }

    private HttpServer() {

    }

    public HttpServer(String address, int port) throws UnknownHostException {
        mAddress = InetAddress.getByName(address);
        mPort = port;
    }

    public void start() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(mAddress, mPort);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true);

        serverSocket.bind(socketAddress);

        System.out.println("Listening on " + socketAddress.toString());

        while(true) {
            Socket clientSocket = serverSocket.accept();

            HttpClientHandler handler = new HttpClientHandler(clientSocket);
            new Thread(handler).start();
        }

    }

    private synchronized void setPort(int port) {
        mPort = port;
    }

    private synchronized void setAddress(InetAddress addr) {
        mAddress = addr;
    }

    public synchronized int getPort() {
        return mPort;
    }

    public synchronized InetAddress getAddress() {
        return mAddress;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide address and port to listen");
            return;
        }

        try {
            HttpServer server = HttpServer.getInstance();
            server.setAddress(InetAddress.getByName(args[0]));
            server.setPort(Integer.parseInt(args[1]));
            server.start();
        } catch (UnknownHostException e) {
            System.err.println("Unknown hostname: " + args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}