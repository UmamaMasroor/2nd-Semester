import javax.swing.*;
import java.awt.*;

// Login Screen
class LoginScreen {
    JFrame frame;

    public LoginScreen() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout to center components

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18 ));
//      usernameLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(usernameLabel, gbc);

        // Username Field
        JTextField usernameField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(usernameField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(passwordLabel, gbc);

        // Password Field
        JPasswordField passwordField = new JPasswordField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(passwordField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK); // Match the frame background
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(buttonPanel, gbc);

        // Button Listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = FileManager.getUser(username);

            if (user != null && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + user.name);
                frame.dispose();
                new DashboardScreen(user);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        signupButton.addActionListener(e -> {
            frame.dispose();
            new SignupScreen();
        });

        frame.setVisible(true);
    }
}
