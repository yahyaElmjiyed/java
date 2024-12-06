package src.dao;

import src.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe DAO pour gérer les utilisateurs
public class UserDAO {

    // Ajouter un utilisateur
    public void addUser(String nom, String prenom, String email, String type) {
        String query = "INSERT INTO utilisateurs (nom, prenom, email, type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lire tous les utilisateurs
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String user = "ID: " + rs.getInt("id_user") +
                              ", Nom: " + rs.getString("nom") +
                              ", Prénom: " + rs.getString("prenom") +
                              ", Email: " + rs.getString("email") +
                              ", Type: " + rs.getString("type");
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Mettre à jour un utilisateur
    public void updateUser(int userId, String newNom, String newPrenom, String newEmail, String newType) {
        String query = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, type = ? WHERE id_user = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newNom);
            pstmt.setString(2, newPrenom);
            pstmt.setString(3, newEmail);
            pstmt.setString(4, newType);
            pstmt.setInt(5, userId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Utilisateur mis à jour avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un utilisateur
    public void deleteUser(int userId) {
        String query = "DELETE FROM utilisateurs WHERE id_user = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

