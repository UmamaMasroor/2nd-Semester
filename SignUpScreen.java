import javax.swing.*;
import java.awt.*;

// Signup Screen
class SignupScreen {
    JFrame frame;

    public SignupScreen() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout
        frame.getContentPane().setBackground(Color.PINK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18 ));
        frame.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.BOLD, 18 ));
        gbc.gridx = 1;
        frame.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18 ));
        frame.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        frame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18 ));
        frame.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        frame.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel securityQuestionLabel = new JLabel("Security Question:");
        securityQuestionLabel.setFont(new Font("Arial", Font.BOLD, 18 ));
        frame.add(securityQuestionLabel, gbc);

        JTextField securityQuestionField = new JTextField(15);
        gbc.gridx = 1;
        frame.add(securityQuestionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel securityAnswerLabel = new JLabel("Answer:");
        securityAnswerLabel.setFont(new Font("Arial", Font.BOLD, 18 ));
        frame.add(securityAnswerLabel, gbc);

        JTextField securityAnswerField = new JTextField(15);
        gbc.gridx = 1;
        frame.add(securityAnswerField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton signupButton = new JButton("Sign Up");
        frame.add(signupButton, gbc);

        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String securityQuestion = securityQuestionField.getText();
            String securityAnswer = securityAnswerField.getText();

            User user = new User(username, name, password, securityQuestion, securityAnswer);
            FileManager.saveUser(user);
            JOptionPane.showMessageDialog(frame, "Signup successful!");
            frame.dispose();
            new LoginScreen();
        });

        frame.setVisible(true);
    }

}
