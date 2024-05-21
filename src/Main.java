import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class Main extends JFrame {
    private Function function = new Function();
    private IntegralCalculator calculator = new IntegralCalculator(function);

    private JTextField intervalsInput;
    private JTextField threadsInput;
    private JLabel resultLabel;

    public Main() {
        setTitle("Integral Calculator");
        setSize(800, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel intervalsLabel = new JLabel("Intervals:");
        intervalsInput = new JTextField();

        JLabel threadsLabel = new JLabel("Threads:");
        threadsInput = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("Result: ");

        calculateButton.addActionListener(new CalculateButtonListener());

        pane.add(intervalsLabel);
        pane.add(intervalsInput);
        pane.add(threadsLabel);
        pane.add(threadsInput);
        pane.add(calculateButton);
        pane.add(resultLabel);

        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int intervals = Integer.parseInt(intervalsInput.getText());
                int threads = Integer.parseInt(threadsInput.getText());
                long startTime = System.currentTimeMillis();
                double result = calculator.calculate(1, 2, intervals, threads);
                long endTime = System.currentTimeMillis();
                resultLabel.setText("Result: " + result + ", Time: " + (endTime - startTime) + " ms");
            } catch (NumberFormatException | InterruptedException | ExecutionException ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
