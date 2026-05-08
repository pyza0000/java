package com.example.demo1;

import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Server listening on port 9090...");
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String msg = in.readLine();
                System.out.println("Client says: " + msg);
                out.println("Hello from server!");
            }
        }
    }
}