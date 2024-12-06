package src.dao;


import src.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe DAO pour gérer les salles
public class SalleDAO {

    // Ajouter une salle
    public void addSalle(String nomSalle, int capacite) {
        String query = "INSERT INTO salles (nom_salle, capacite) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nomSalle);
            pstmt.setInt(2, capacite);
            pstmt.executeUpdate();
            System.out.println("Salle ajoutée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lire toutes les salles
    public List<String> getAllSalles() {
        List<String> salles = new ArrayList<>();
        String query = "SELECT * FROM salles";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String salle = "ID Salle: " + rs.getInt("id_salle") +
                               ", Nom: " + rs.getString("nom_salle") +
                               ", Capacité: " + rs.getInt("capacite");
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }

    // Mettre à jour une salle
    public void updateSalle(int salleId, String newNomSalle, int newCapacite) {
        String query = "UPDATE salles SET nom_salle = ?, capacite = ? WHERE id_salle = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newNomSalle);
            pstmt.setInt(2, newCapacite);
            pstmt.setInt(3, salleId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Salle mise à jour avec succès !");
            } else {
                System.out.println("Aucune salle trouvée avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une salle
    public void deleteSalle(int salleId) {
        String query = "DELETE FROM salles WHERE id_salle = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, salleId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Salle supprimée avec succès !");
            } else {
                System.out.println("Aucune salle trouvée avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
