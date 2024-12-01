import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Base class: User
class User {
    protected String username, name, password, securityQuestion, securityAnswer;

    public User() {}

    public User(String username, String name, String password, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    // Encapsulation: Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
