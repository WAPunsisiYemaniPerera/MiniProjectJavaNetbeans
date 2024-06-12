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
public class AppointmentModel {
    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/petshop";
    
    private Connection sqlConn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public AppointmentModel() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Vector<Vector<String>> getAllAppointments() {
        Vector<Vector<String>> data = new Vector<>();
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT * FROM appointments");
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

    public void addAppointment(String appointmentNo, String petID, String petName, String ownerName, String telephone, String appointmentDate, String appointmentTime) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("INSERT INTO appointments(AppointmentNo, PetID, PetName, OwnerName, Telephone, AppointmentDate, AppointmentTime) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, appointmentNo);
            pst.setString(2, petID);
            pst.setString(3, petName);
            pst.setString(4, ownerName);
            pst.setString(5, telephone);
            pst.setString(6, appointmentDate);
            pst.setString(7, appointmentTime);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updateAppointment(int appointmentNo, String petID, String petName, String ownerName, String telephone, String appointmentDate, String appointmentTime) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("UPDATE appointments SET AppointmentNo=?, PetID=?, PetName=?, OwnerName=?, Telephone=?, AppointmentDate=?, AppointmentTime=? WHERE AppointmentNo=?");
            pst.setString(1, appointmentNo + "");
            pst.setString(2, petID);
            pst.setString(3, petName);
            pst.setString(4, ownerName);
            pst.setString(5, telephone);
            pst.setString(6, appointmentDate);
            pst.setString(7, appointmentTime);
            pst.setInt(8, appointmentNo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void deleteAppointment(int appointmentNo) {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("DELETE FROM appointments WHERE AppointmentNo=?");
            pst.setInt(1, appointmentNo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public int getMaxAppointmentNo() {
        int maxAppointmentNo = 0;
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT MAX(AppointmentNo) FROM appointments");
            rs = pst.executeQuery();
            if (rs.next()) {
                maxAppointmentNo = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return maxAppointmentNo;
    }

    public Vector<String> getPetDetails(String petID) {
        Vector<String> petDetails = new Vector<>();
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT PetName, OwnerName, Telephone FROM pets WHERE PetID = ?");
            pst.setString(1, petID);
            rs = pst.executeQuery();
            if (rs.next()) {
                petDetails.add(rs.getString("PetName"));
                petDetails.add(rs.getString("OwnerName"));
                petDetails.add(rs.getString("Telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return petDetails;
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
