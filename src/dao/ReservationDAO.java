package src.dao;

import src.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe DAO pour gérer les réservations
public class ReservationDAO {

    // Ajouter une réservation
    public void addReservation(int eventId, int userId, String dateReservation, String description) {
        String query = "INSERT INTO reservations (id_event, id_user, date_reservation, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, dateReservation);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
            System.out.println("Réservation ajoutée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lire toutes les réservations
    public List<String> getAllReservations() {
        List<String> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String reservation = "ID Réservation: " + rs.getInt("id_reservation") +
                                     ", ID Événement: " + rs.getInt("id_event") +
                                     ", ID Utilisateur: " + rs.getInt("id_user") +
                                     ", Date: " + rs.getString("date_reservation") +
                                     ", Description: " + rs.getString("description");
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Mettre à jour une réservation
    public void updateReservation(int reservationId, String newDate, String newDescription) {
        String query = "UPDATE reservations SET date_reservation = ?, description = ? WHERE id_reservation = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newDate);
            pstmt.setString(2, newDescription);
            pstmt.setInt(3, reservationId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Réservation mise à jour avec succès !");
            } else {
                System.out.println("Aucune réservation trouvée avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une réservation
    public void deleteReservation(int reservationId) {
        String query = "DELETE FROM reservations WHERE id_reservation = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Réservation supprimée avec succès !");
            } else {
                System.out.println("Aucune réservation trouvée avec l'ID fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

