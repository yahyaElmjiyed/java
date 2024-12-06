package src.dao;


import src.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe EventDAO pour gérer les événements
public class EventDAO {
    // Ajouter un événement
    public void addEvent(String eventName, String eventDescription, int userId) {
        String query = "INSERT INTO events (event_name, event_description, id_user) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, eventName);
            pstmt.setString(2, eventDescription);
            pstmt.setInt(3, userId);
            pstmt.executeUpdate();
            System.out.println("Événement ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lire tous les événements
    public List<String> getAllEvents() {
        List<String> events = new ArrayList<>();
        String query = "SELECT * FROM events";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String event = "ID: " + rs.getInt("id_event") +
                               ", Nom: " + rs.getString("event_name") +
                               ", Description: " + rs.getString("event_description") +
                               ", Utilisateur ID: " + rs.getInt("id_user");
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Mettre à jour un événement
    public void updateEvent(int eventId, String newName, String newDescription) {
        String query = "UPDATE events SET event_name = ?, event_description = ? WHERE id_event = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newDescription);
            pstmt.setInt(3, eventId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Événement mis à jour avec succès !");
            } else {
                System.out.println("Aucun événement trouvé avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un événement
    public void deleteEvent(int eventId) {
        String query = "DELETE FROM events WHERE id_event = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, eventId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Événement supprimé avec succès !");
            } else {
                System.out.println("Aucun événement trouvé avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
