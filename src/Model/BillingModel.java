/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author 3C Tech
 */
public class BillingModel {
    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/petshop";
    
    private Connection sqlConn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public BillingModel() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveBillingData(double subTotal, double tax, double total) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("INSERT INTO billing (SubTotal, Tax, Total, BillDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)");
            pst.setDouble(1, subTotal);
            pst.setDouble(2, tax);
            pst.setDouble(3, total);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (sqlConn != null) sqlConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
