/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author 3C Tech
 */
public class PetModel {
    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/petshop";
    
    private Connection sqlConn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public PetModel() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Vector<Vector<String>> getAllPets() {
        Vector<Vector<String>> data = new Vector<>();
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT * FROM pets");
            rs = pst.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Vector<String> row = new Vector<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return data;
    }

    public void addPet(String petID, String petName, String petType, String petAge, String ownerName, String telephone, String address) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("INSERT INTO pets(PetID, PetName, PetType, PetAge, OwnerName, Telephone, Address) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, petID);
            pst.setString(2, petName);
            pst.setString(3, petType);
            pst.setString(4, petAge);
            pst.setString(5, ownerName);
            pst.setString(6, telephone);
            pst.setString(7, address);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updatePet(int id, String petID, String petName, String petType, String petAge, String ownerName, String telephone, String address) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("UPDATE pets SET PetID =?, PetName =?, PetType =?, PetAge =?, OwnerName =?, Telephone =?, Address=? where id =?");
            pst.setString(1, petID);
            pst.setString(2, petName);
            pst.setString(3, petType);
            pst.setString(4, petAge);
            pst.setString(5, ownerName);
            pst.setString(6, telephone);
            pst.setString(7, address);
            pst.setInt(8, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void deletePet(int id) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("DELETE FROM pets WHERE ID=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public int getMaxPetID() {
        int maxPetID = 0;
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT MAX(PetID) FROM pets");
            rs = pst.executeQuery();
            if (rs.next()) {
                maxPetID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return maxPetID;
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
