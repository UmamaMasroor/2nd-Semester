import javax.swing.*;
import java.io.*;

class FileManager {
    private static final String FILE_NAME = "users.txt";

    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.username + "," + user.name + "," + user.password + "," +
                    user.securityQuestion + "," + user.securityAnswer);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user data: " + e.getMessage());
        }
    }

    public static User getUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data: " + e.getMessage());
        }
        return null;
    }
}


