package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.PrintWriter;
import java.net.Socket;

public class HelloController {

    @FXML
    private TextField answerField;

    @FXML
    private TextField nickField;

    @FXML
    protected void handleSendAnswer() {
        String nick = nickField.getText().trim();
        String answer = answerField.getText().trim();

        if (nick.isEmpty()) {
            System.out.println("Wpisz nick");
            return;
        }

        if (answer.isEmpty()) {
            System.out.println("Wpisz odpowiedz");
            return;
        }

        try {
            Socket socket = new Socket("localhost", 9090);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(nick + "|" + answer);

            out.close();
            socket.close();

            System.out.println("Wyslano odpowiedz");
            answerField.clear();

        } catch (Exception e) {
            System.out.println("Blad polaczenia z serwerem");
            e.printStackTrace();
        }
    }
}