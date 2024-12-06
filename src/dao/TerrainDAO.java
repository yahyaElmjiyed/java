package src.dao;

import src.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TerrainDAO {
    // Ajouter un terrain
    public void addTerrain(String nom, String type) {
        String query = "INSERT INTO terrains (nom, type) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, type);
            pstmt.executeUpdate();
            System.out.println("Terrain ajouté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Afficher tous les terrains
    public List<String> getAllTerrains() {
        List<String> terrains = new ArrayList<>();
        String query = "SELECT * FROM terrains";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                terrains.add("ID: " + rs.getInt("id_terrain") +
                        ", Nom: " + rs.getString("nom") +
                        ", Type: " + rs.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terrains;
    }

    // Mettre à jour un terrain
    public void updateTerrain(int id, String newNom, String newType) {
        String query = "UPDATE terrains SET nom = ?, type = ? WHERE id_terrain = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newNom);
            pstmt.setString(2, newType);
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Terrain mis à jour avec succès !");
            } else {
                System.out.println("Aucun terrain trouvé avec cet ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Supprimer un terrain
    public void deleteTerrain(int id) {
        String query = "DELETE FROM terrains WHERE id_terrain = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Terrain supprimé avec succès !");
            } else {
                System.out.println("Aucun terrain trouvé avec cet ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
