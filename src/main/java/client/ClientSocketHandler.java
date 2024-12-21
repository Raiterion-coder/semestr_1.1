package client;

import java.io.*;
import java.net.Socket;

public class ClientSocketHandler {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientSocketHandler(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String readMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
}
