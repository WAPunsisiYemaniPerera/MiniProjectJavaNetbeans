/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.PetModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author 3C Tech
 */
public class PetController {
    private PetModel petModel;
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

    public PetController() {
        petModel = new PetModel();
    }

    public Vector<Vector<String>> getAllPets() {
        return petModel.getAllPets();
    }

    public void addPet(String petID, String petName, String petType, String petAge, String ownerName, String telephone, String address) {
        petModel.addPet(petID, petName, petType, petAge, ownerName, telephone, address);
    }

    public void updatePet(int id, String petID, String petName, String petType, String petAge, String ownerName, String telephone, String address) {
        petModel.updatePet(id, petID, petName, petType, petAge, ownerName, telephone, address);
    }

    public void deletePet(int id) {
        petModel.deletePet(id);
    }

    public int getNextPetID() {
        return petModel.getMaxPetID() + 1;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
