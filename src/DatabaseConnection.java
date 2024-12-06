package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Informations de connexion
    private static final String URL = "jdbc:mysql://localhost:3307/ecole_gestion"; // Remplacez par votre URL
    private static final String USER = "root"; // Remplacez par votre utilisateur
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe

    // Méthode pour obtenir une connexion
    public static Connection getConnection() throws SQLException {
        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retourner une nouvelle connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC non trouvé", e);
        }
    }
}
