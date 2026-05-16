package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class QuizClient extends JFrame {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

    private JTextField nickField;
    private JTextField answerField;
    private JButton sendButton;

    public QuizClient() {

        setTitle("CLIENT");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Your Answer:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        answerField = new JTextField(15);
        mainPanel.add(answerField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        sendButton = new JButton("Send Answer>>");
        sendButton.setPreferredSize(new Dimension(200, 50));
        mainPanel.add(sendButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Your Nick:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        nickField = new JTextField(10);
        mainPanel.add(nickField, gbc);

        add(mainPanel, BorderLayout.CENTER);


        sendButton.addActionListener(e -> sendAnswer());

        answerField.addActionListener(e -> sendAnswer());
    }

    private void sendAnswer() {
        String nick = nickField.getText().trim();
        String answer = answerField.getText().trim();

        if (nick.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wypełnij pola Nick oraz Answer!", "Błąd", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {


            out.println(nick + "|" + answer);


            answerField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nie można połączyć z serwerem. Upewnij się, że serwer działa.", "Błąd Połączenia", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizClient().setVisible(true));
    }
}