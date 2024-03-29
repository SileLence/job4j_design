package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class);
    private static final String HTTP_HEADER = "HTTP/1.1 200 OK\r\n\r\n";
    
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write(HTTP_HEADER.getBytes());
                    String str = in.readLine();
                    if (str.contains("msg=")) {
                        String value = str.substring(str.indexOf("=") + 1, str.lastIndexOf(" "));
                        if ("exit".equalsIgnoreCase(value)) {
                            server.close();
                        } else if ("hello".equalsIgnoreCase(value)) {
                            out.write("Hello!".getBytes());
                        } else {
                            out.write("What?".getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (Exception exception) {
            LOGGER.error("Error occur: ", exception);
        }
    }
}
