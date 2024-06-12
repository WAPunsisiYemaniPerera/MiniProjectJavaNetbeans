/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.DatabaseHandler;
import Model.User;
import view.Home;
import view.UserRegistration;
/**
 *
 * @author 3C Tech
 */
public class UserController {
    private final UserRegistration view;
    private final DatabaseHandler model;

    public UserController(UserRegistration view) {
        this.view = view;
        this.model = new DatabaseHandler();
    }

    public void registerButtonClicked() {
        String name = view.getNameText();
    String username = view.getUsernameText();
    String password = view.getPasswordText();
    String retypePassword = view.getRetypePasswordText();

    // Check if any field is empty
    if (name.isEmpty() || username.isEmpty() || password.isEmpty() || retypePassword.isEmpty()) {
        view.showMessage("Please fill all the fields!");
        return;
    }

    // Check if passwords match
    if (!password.equals(retypePassword)) {
        view.showMessage("Passwords do not match!");
        return;
    }

    try {
        User user = new User(name, username, password);
        model.registerUser(user);
        view.showMessage("User registered successfully!");
        view.clearFields();
    } catch (SQLException ex) {
        ex.printStackTrace();
        view.showMessage("Error registering user!");
    }
    }

    public void backButtonClicked() {
        System.out.println("Back button pressed");
        view.dispose(); // Close current JFrame
        // Open Home JFrame
        Home home = new Home();
        home.setVisible(true);
        view.dispose(); // Close current JFrame
    }

    public void resetButtonClicked() {
        view.clearFields();
    }
}
