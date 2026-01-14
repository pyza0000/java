import javax.swing.*;
import java.awt.event.*;

class CaloriesException extends Exception {
    CaloriesException(String message) {
        super(message);
    }
}

class LowBmiException extends CaloriesException {
    LowBmiException(String message) {
        super(message);
    }
}

class HighBmiException extends CaloriesException {
    HighBmiException(String message) {
        super(message);
    }
}

public class Main {

    static double calculateBmi(double weight, double height) throws CaloriesException {
        double bmi = weight / (height * height);

        if (bmi < 17) {
            throw new LowBmiException("Zbyt niskie BMI");
        }
        if (bmi > 30) {
            throw new HighBmiException("Zbyt duże BMI");
        }
        return bmi;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalkulator BMI");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(100, 250, 200, 25);
        frame.add(resultLabel);

        JLabel weightLabel = new JLabel("Podaj masę (kg):");
        weightLabel.setBounds(50, 50, 120, 25);
        frame.add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setBounds(180, 50, 120, 25);
        frame.add(weightField);

        JLabel heightLabel = new JLabel("Podaj wzrost (m):");
        heightLabel.setBounds(50, 90, 120, 25);
        frame.add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(180, 90, 120, 25);
        frame.add(heightField);

        JButton calculateButton = new JButton("Oblicz BMI");
        calculateButton.setBounds(80, 150, 120, 30);
        frame.add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(weightField.getText());
                    double height = Double.parseDouble(heightField.getText());

                    double bmiResult = calculateBmi(weight, height);
                    resultLabel.setText("BMI = " + bmiResult);

                } catch (CaloriesException ex) {
                    resultLabel.setText("Błąd: " + ex.getMessage());
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Błąd danych wejściowych");
                }
            }
        });
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
// wiecej catche, wiecej rodziny wyjatkow etc, zmiana nazwy z calories na cos