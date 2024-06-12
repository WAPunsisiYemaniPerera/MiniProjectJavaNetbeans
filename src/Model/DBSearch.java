/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
/**
 *
 * @author 3C Tech
 */
public class DBSearch {
    public ResultSet searchLogin(String usName) {
        ResultSet rs = null;
        try {
            Connection conn = DBConnection.getStatementConnection().getConnection();
            String query = "SELECT * FROM login WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usName);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
