/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.DBSearch;
import Model.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.Home;
import view.Login;
/**
 *
 * @author 3C Tech
 */
public class LoginController {
    public static void login(Login loginFrame, String usName, String pass) {
        try {
            String username = null;
            String password = null;
            ResultSet rs = new DBSearch().searchLogin(usName);
            
            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
            
            if (username != null && password != null) {
                if (password.equals(pass)) {
                    System.out.println("Login Successful");
                    loginFrame.dispose();
                    new Home().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please check the credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please check the Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
            DBConnection.closeCon();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
