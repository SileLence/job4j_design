package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    
    private static final String HTTP_HEADER = "HTTP/1.1 200 OK\r\n\r\n";
    private static final String PARAMETER_PREFIX = "/?=";
    private static final String HELLO = "Hello";
    private static final String EXIT = "Exit";
    private static final String WHAT = "What?";
    
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write(HTTP_HEADER.getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains(PARAMETER_PREFIX)) {
                            String param = str.split(PARAMETER_PREFIX)[1];
                            if (param.contains(HELLO)) {
                                out.write(HELLO.getBytes());
                            } else if (param.contains(EXIT)) {
                                server.close();
                            } else {
                                out.write(WHAT.getBytes());
                            }
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
