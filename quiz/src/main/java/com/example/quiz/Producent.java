package com.example.quiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

public class Producent implements Runnable {
    private BlockingQueue<Produkt> kolejka;
    private int port;
    private QuizServer server;

    public Producent(BlockingQueue<Produkt> kolejka, int port, QuizServer server) {
        this.kolejka = kolejka;
        this.port = port;
        this.server = server;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!server.isGameOver()) {
                Socket clientSocket = serverSocket.accept();
                String ipAddress = clientSocket.getInetAddress().getHostAddress();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                String line = in.readLine();

                if (line != null && !server.isGameOver()) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 2) {
                        // Tworzymy "Produkt" i wstawiamy do kolejki
                        Produkt produkt = new Produkt(parts[0], parts[1], ipAddress);
                        kolejka.put(produkt);
                    }
                }
                clientSocket.close();
            }
        } catch (Exception e) {
            if (!server.isGameOver()) e.printStackTrace();
        }
    }
}