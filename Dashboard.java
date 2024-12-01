import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Dashboard Screen
class DashboardScreen {
    JFrame frame;
    JPanel contentPanel;

    public DashboardScreen(User user) {
        // Create main frame
        frame = new JFrame("Dashboard - Tourism Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + user.name + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(welcomeLabel, BorderLayout.NORTH);

        // Navigation panel (left side menu)
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(10, 1, 5, 5));
        navigationPanel.setPreferredSize(new Dimension(200, 0));
        navigationPanel.setBackground(Color.LIGHT_GRAY);

        // Add menu buttons to navigation panel
        String[] menuItems = {"Add Personal Details", "Update Personal Details", "View Details",
                "Delete Personal Details", "Check Package", "Book Package",
                "Payment", "About", "Logout"};

        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.addActionListener(e -> handleNavigation(item, user)); // Pass user to new frames
            navigationPanel.add(button);
        }
        frame.add(navigationPanel, BorderLayout.WEST);

        // Content panel (right side)
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(createWelcomePanel(), "Welcome"); // Default welcome panel
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Create welcome panel
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome to the Tourism Management System!", JLabel.CENTER);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // Handle navigation
    private void handleNavigation(String action, User user) {
        switch (action) {
            case "Add Personal Details":
                new AddDetailsScreen(user);
                break;
            case "Update Personal Details":
                new UpdateDetailsScreen(user);
                break;
            case "View Details":
                new ViewDetailsScreen(user);
                break;
            case "Delete Personal Details":
                new DeleteDetailsScreen(user);
                break;
            case "Check Package":
                new CheckPackageScreen(user);
                break;
            case "Book Package":
                new BookPackageScreen(user);
                break;
            case "Payment":
                new PaymentScreen(user);
                break;
            case "About":
                new AboutScreen();
                break;
            case "Logout":
                frame.dispose();
                new LoginScreen(); // Assuming you have a LoginScreen class
                break;
        }
    }

    public static void main(String[] args) {
        User user = new User("John Doe");
        new DashboardScreen(user);
    }
}

// Example User class
class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}

class AddDetailsScreen {
    public AddDetailsScreen(User user) {
        // Create the main frame
        JFrame frame = new JFrame("Personal Information Form");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Personal Information Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(user.name);
        JLabel titleFieldLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel dobLabel = new JLabel("Date of Birth:");
        JTextField dobField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        // Adding components to form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(titleFieldLabel);
        formPanel.add(titleField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(genderLabel);
        formPanel.add(genderPanel);

        // Add formPanel to frame
        frame.add(formPanel, BorderLayout.CENTER);

        // Save button panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        buttonPanel.add(saveButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Save button action listener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String title = titleField.getText();
                String address = addressField.getText();
                String dob = dobField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String gender = maleButton.isSelected() ? "Male" : "Female";

                // Save details to a file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                    writer.write("Name: " + name + ", Title: " + title + ", Address: " + address +
                            ", DOB: " + dob + ", Email: " + email + ", Phone: " + phone + ", Gender: " + gender);
                    writer.newLine();
                    JOptionPane.showMessageDialog(frame, "Details saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving details: " + ex.getMessage());
                }
            }
        });

        // Set frame to visible
        frame.setVisible(true);
    }
}
class UpdateDetailsScreen {
    public UpdateDetailsScreen(User user) {
        JFrame frame = new JFrame("Update Personal Details");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Update Personal Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(user.name);
        JLabel addressLabel = new JLabel("New Address:");
        JTextField addressField = new JTextField();
        JLabel phoneLabel = new JLabel("New Phone:");
        JTextField phoneField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        frame.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update");
        buttonPanel.add(updateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();

            // Perform update logic here
            JOptionPane.showMessageDialog(frame, "Details updated successfully for " + name);
        });

        frame.setVisible(true);
    }
}

class ViewDetailsScreen {
    public ViewDetailsScreen(User user) {
        JFrame frame = new JFrame("View Details");
        frame.setSize(400, 300);
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setText("Name: " + user.name + "\nAddress: " + user.address + "\nPhone: " + user.phone);
        frame.add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

class DeleteDetailsScreen {
    public DeleteDetailsScreen(User user) {
        JFrame frame = new JFrame("Delete Personal Details");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        JLabel confirmationLabel = new JLabel("Are you sure you want to delete the details for " + user.name + "?", JLabel.CENTER);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            // Perform deletion logic here
            JOptionPane.showMessageDialog(frame, "Details deleted successfully!");
            frame.dispose();
        });

        frame.add(confirmationLabel, BorderLayout.CENTER);
        frame.add(deleteButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

class CheckPackageScreen {
    public CheckPackageScreen(User user) {
        JFrame frame = new JFrame("Check Package");
        frame.setSize(400, 300);
        JTextArea packageDetails = new JTextArea("Package details for " + user.name);
        packageDetails.setEditable(false);
        frame.add(new JScrollPane(packageDetails), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

class BookPackageScreen {
    public BookPackageScreen(User user) {
        JFrame frame = new JFrame("Book Package");
        frame.setSize(400, 300);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel packageLabel = new JLabel("Select Package:");
        JComboBox<String> packageDropdown = new JComboBox<>(new String[]{"Package A", "Package B", "Package C"});
        JLabel dateLabel = new JLabel("Select Date:");
        JTextField dateField = new JTextField();
        JButton bookButton = new JButton("Book Now");

        panel.add(packageLabel);
        panel.add(packageDropdown);
        panel.add(dateLabel);
        panel.add(dateField);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(bookButton, BorderLayout.SOUTH);

        bookButton.addActionListener(e -> {
            String selectedPackage = (String) packageDropdown.getSelectedItem();
            String date = dateField.getText();
            JOptionPane.showMessageDialog(frame, "Package " + selectedPackage + " booked for " + date);
        });

        frame.setVisible(true);
    }
}

class PaymentScreen {
    public PaymentScreen(User user) {
        JFrame frame = new JFrame("Payment");
        frame.setSize(400, 300);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel amountLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField();
        JLabel cardLabel = new JLabel("Card Details:");
        JTextField cardField = new JTextField();
        JButton payButton = new JButton("Pay Now");

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(cardLabel);
        panel.add(cardField);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(payButton, BorderLayout.SOUTH);

        payButton.addActionListener(e -> {
            String amount = amountField.getText();
            String cardDetails = cardField.getText();
            JOptionPane.showMessageDialog(frame, "Payment of " + amount + " processed using card " + cardDetails);
        });

        frame.setVisible(true);
    }
}

class AboutScreen {
    public AboutScreen() {
        JFrame frame = new JFrame("About");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel for content
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Adding a heading label
        JLabel heading = new JLabel("About the Tourism Management System", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adding content description
        JLabel description = new JLabel("<html><div style='text-align: center;'>"
            + "The Tourism Management System helps users plan and manage their travels effectively. "
            + "Explore destinations, book accommodations, and ensure a hassle-free experience.</div></html>");
        description.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding an image
        ImageIcon originalIcon = new ImageIcon("girl.jpeg");

        // Resize the image
        Image resizedImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Width, Height
        ImageIcon icon = new ImageIcon(resizedImage); // Replace with your image path
        JLabel imageLabel = new JLabel(icon, JLabel.CENTER);

        // Adding components to the panel
        panel.add(heading, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(description, BorderLayout.SOUTH);

        // Adding panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}

