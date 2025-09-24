import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGame extends JFrame {
    private int randomNumber;
    private int attemptsLeft = 5;

    private final JTextField guessField;
    private final JLabel messageLabel;

    public NumberGame() {
        // Random number generate
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;

        // Frame settings
        setTitle("Guess the Number");
        setSize(600, 400); // Badi window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Center alignment ke liye
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between controls
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JLabel label = new JLabel("Enter your guess (1-100):", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label, gbc);

        guessField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(guessField, gbc);

        JButton guessButton = new JButton("Guess");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(guessButton, gbc);

        messageLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        // Action listener
        guessButton.addActionListener(new ActionListenerImpl());
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attemptsLeft--;

            if (guess == randomNumber) {
                JOptionPane.showMessageDialog(this, " Correct! You guessed the number.");
                resetGame();
            } else if (attemptsLeft > 0) {
                if (guess < randomNumber) {
                    messageLabel.setText("Too low! Attempts left: " + attemptsLeft);
                } else {
                    messageLabel.setText("Too high! Attempts left: " + attemptsLeft);
                }
            } else {
                JOptionPane.showMessageDialog(this, " Out of attempts! The number was: " + randomNumber);
                resetGame();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    private void resetGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attemptsLeft = 5;
        messageLabel.setText("Attempts left: " + attemptsLeft);
        guessField.setText("");
    }

    public static void main(String[] args) {
        new NumberGame().setVisible(true);
    }

    private class ActionListenerImpl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkGuess();
        }
    }
}
