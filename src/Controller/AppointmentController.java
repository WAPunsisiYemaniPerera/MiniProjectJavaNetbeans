/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.AppointmentModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;


/**
 *
 * @author 3C Tech
 */
public class AppointmentController {
    private AppointmentModel appointmentModel;
    private final String DB_URL = "jdbc:mysql://localhost:3306/petshop"; 
    private final String USER = "root"; 
    private final String PASS = ""; 
    
    public Connection getConnections() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public AppointmentController() {
        appointmentModel = new AppointmentModel();
    }


    public Vector<Vector<String>> getAllAppointments() {
        return appointmentModel.getAllAppointments();
    }

    public void addAppointment(String appointmentNo, String petID, String petName, String ownerName, String telephone, String appointmentDate, String appointmentTime) {
        appointmentModel.addAppointment(appointmentNo, petID, petName, ownerName, telephone, appointmentDate, appointmentTime);
    }

    public void updateAppointment(int appointmentNo, String petID, String petName, String ownerName, String telephone, String appointmentDate, String appointmentTime) {
        appointmentModel.updateAppointment(appointmentNo, petID, petName, ownerName, telephone, appointmentDate, appointmentTime);
    }

    public void deleteAppointment(int appointmentNo) {
        appointmentModel.deleteAppointment(appointmentNo);
    }

    public int getNextAppointmentNo() {
        return appointmentModel.getMaxAppointmentNo() + 1;
    }

    public Vector<String> getPetDetails(String petID) {
        return appointmentModel.getPetDetails(petID);
    }
}
