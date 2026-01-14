import javax.swing.*;
import java.awt.event.*;

public class HelloWorldSwing {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello World App");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("");
        label.setBounds(100, 50, 150, 30);
        frame.add(label);

        JButton button = new JButton("Hello World");
        button.setBounds(80, 100, 120, 30);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World!"); //wypisanie w konsoli
                label.setText("Hello World!");       //wyświetlenie w okienku
            }
        });


        frame.setVisible(true);
    }
}
