/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author 3C Tech
 */
public class DatabaseHandler {
    private final String url = "jdbc:mysql://localhost:3306/petshop";
    private final String user = "root";
    private final String dbPassword = "";

    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO login (Username, password, name) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, "root", "");
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.executeUpdate();
        }
    }
}
