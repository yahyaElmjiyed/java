package src;

import src.dao.ReservationDAO;
import src.dao.SalleDAO;
import src.dao.TerrainDAO;
import src.dao.UserDAO;
import src.dao.EventDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationDAO reservationDAO = new ReservationDAO();
        SalleDAO salleDAO = new SalleDAO();
        TerrainDAO terrainDAO = new TerrainDAO();
        UserDAO userDAO = new UserDAO();
        EventDAO eventDAO = new EventDAO();

        int choixPrincipal;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gestion des Réservations");
            System.out.println("2. Gestion des Salles");
            System.out.println("3. Gestion des Terrains");
            System.out.println("4. Gestion des Utilisateurs");
            System.out.println("5. Gestion des Événements");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            choixPrincipal = scanner.nextInt();

            switch (choixPrincipal) {
                case 1 -> gestionReservations(scanner, reservationDAO);
                case 2 -> gestionSalles(scanner, salleDAO);
                case 3 -> gestionTerrains(scanner, terrainDAO);
                case 4 -> gestionUtilisateurs(scanner, userDAO);
                case 5 -> gestionEvenements(scanner, eventDAO);
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixPrincipal != 0);

        scanner.close();
    }

    // Gestion des Réservations
    public static void gestionReservations(Scanner scanner, ReservationDAO reservationDAO) {
        int choixReservation;
        do {
            System.out.println("\n=== Gestion des Réservations ===");
            System.out.println("1. Ajouter une réservation");
            System.out.println("2. Afficher toutes les réservations");
            System.out.println("3. Modifier une réservation");
            System.out.println("4. Supprimer une réservation");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choixReservation = scanner.nextInt();

            switch (choixReservation) {
                case 1 -> {
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez l'ID de l'utilisateur : ");
                    int idUser = scanner.nextInt();
                    System.out.print("Entrez l'ID de l'événement : ");
                    int idEvent = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez la date de réservation (YYYY-MM-DD) : ");
                    String date = scanner.nextLine();
                    System.out.print("Entrez une description : ");
                    String description = scanner.nextLine();
                    reservationDAO.addReservation(idEvent, idUser, date, description);
                }
                case 2 -> {
                    System.out.println("Liste des réservations :");
                    reservationDAO.getAllReservations().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Entrez l'ID de la réservation à modifier : ");
                    int idReservation = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez la nouvelle date (YYYY-MM-DD) : ");
                    String newDate = scanner.nextLine();
                    System.out.print("Entrez la nouvelle description : ");
                    String newDescription = scanner.nextLine();
                    reservationDAO.updateReservation(idReservation, newDate, newDescription);
                }
                case 4 -> {
                    System.out.print("Entrez l'ID de la réservation à supprimer : ");
                    int idReservation = scanner.nextInt();
                    reservationDAO.deleteReservation(idReservation);
                }
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixReservation != 0);
    }

    // Gestion des Salles
    public static void gestionSalles(Scanner scanner, SalleDAO salleDAO) {
        int choixSalle;
        do {
            System.out.println("\n=== Gestion des Salles ===");
            System.out.println("1. Ajouter une salle");
            System.out.println("2. Afficher toutes les salles");
            System.out.println("3. Modifier une salle");
            System.out.println("4. Supprimer une salle");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choixSalle = scanner.nextInt();

            switch (choixSalle) {
                case 1 -> {
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nom de la salle : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la capacité de la salle : ");
                    int capacite = scanner.nextInt();
                    salleDAO.addSalle(nom, capacite);
                }
                case 2 -> {
                    System.out.println("Liste des salles :");
                    salleDAO.getAllSalles().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Entrez l'ID de la salle à modifier : ");
                    int idSalle = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nouveau nom : ");
                    String newNom = scanner.nextLine();
                    System.out.print("Entrez la nouvelle capacité : ");
                    int newCapacite = scanner.nextInt();
                    salleDAO.updateSalle(idSalle, newNom, newCapacite);
                }
                case 4 -> {
                    System.out.print("Entrez l'ID de la salle à supprimer : ");
                    int idSalle = scanner.nextInt();
                    salleDAO.deleteSalle(idSalle);
                }
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixSalle != 0);
    }

    // Gestion des Terrains
    public static void gestionTerrains(Scanner scanner, TerrainDAO terrainDAO) {
        int choixTerrain;
        do {
            System.out.println("\n=== Gestion des Terrains ===");
            System.out.println("1. Ajouter un terrain");
            System.out.println("2. Afficher tous les terrains");
            System.out.println("3. Modifier un terrain");
            System.out.println("4. Supprimer un terrain");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choixTerrain = scanner.nextInt();

            switch (choixTerrain) {
                case 1 -> {
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nom du terrain : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le type du terrain : ");
                    String type = scanner.nextLine();
                    terrainDAO.addTerrain(nom, type);
                }
                case 2 -> {
                    System.out.println("Liste des terrains :");
                    terrainDAO.getAllTerrains().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Entrez l'ID du terrain à modifier : ");
                    int idTerrain = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nouveau nom : ");
                    String newNom = scanner.nextLine();
                    System.out.print("Entrez le nouveau type : ");
                    String newType = scanner.nextLine();
                    terrainDAO.updateTerrain(idTerrain, newNom, newType);
                }
                case 4 -> {
                    System.out.print("Entrez l'ID du terrain à supprimer : ");
                    int idTerrain = scanner.nextInt();
                    terrainDAO.deleteTerrain(idTerrain);
                }
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixTerrain != 0);
    }

    // Gestion des Utilisateurs
    public static void gestionUtilisateurs(Scanner scanner, UserDAO userDAO) {
        int choixUtilisateur;
        do {
            System.out.println("\n=== Gestion des Utilisateurs ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Afficher tous les utilisateurs");
            System.out.println("3. Modifier un utilisateur");
            System.out.println("4. Supprimer un utilisateur");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choixUtilisateur = scanner.nextInt();

            switch (choixUtilisateur) {
                case 1 -> {
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrez l'email : ");
                    String email = scanner.nextLine();
                    System.out.print("Entrez le type (ETUDIANT/PROFESSEUR) : ");
                    String type = scanner.nextLine();
                    userDAO.addUser(nom, prenom, email, type);
                }
                case 2 -> {
                    System.out.println("Liste des utilisateurs :");
                    userDAO.getAllUsers().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Entrez l'ID de l'utilisateur à modifier : ");
                    int idUser = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nouveau nom : ");
                    String newNom = scanner.nextLine();
                    System.out.print("Entrez le nouveau prénom : ");
                    String newPrenom = scanner.nextLine();
                    System.out.print("Entrez le nouvel email : ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Entrez le nouveau type (ETUDIANT/PROFESSEUR) : ");
                    String newType = scanner.nextLine();
                    userDAO.updateUser(idUser, newNom, newPrenom, newEmail, newType);
                }
                case 4 -> {
                    System.out.print("Entrez l'ID de l'utilisateur à supprimer : ");
                    int idUser = scanner.nextInt();
                    userDAO.deleteUser(idUser);
                }
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixUtilisateur != 0);
    }

    // Gestion des Événements
    public static void gestionEvenements(Scanner scanner, EventDAO eventDAO) {
        int choixEvenement;
        do {
            System.out.println("\n=== Gestion des Événements ===");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Afficher tous les événements");
            System.out.println("3. Modifier un événement");
            System.out.println("4. Supprimer un événement");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choixEvenement = scanner.nextInt();

            switch (choixEvenement) {
                case 1 -> {
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nom de l'événement : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la description : ");
                    String description = scanner.nextLine();
                    System.out.print("Entrez l'ID de l'utilisateur associé : ");
                    int idUser = scanner.nextInt();
                    eventDAO.addEvent(nom, description, idUser);
                }
                case 2 -> {
                    System.out.println("Liste des événements :");
                    eventDAO.getAllEvents().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Entrez l'ID de l'événement à modifier : ");
                    int idEvent = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Entrez le nouveau nom : ");
                    String newNom = scanner.nextLine();
                    System.out.print("Entrez la nouvelle description : ");
                    String newDescription = scanner.nextLine();
                    eventDAO.updateEvent(idEvent, newNom, newDescription);
                }
                case 4 -> {
                    System.out.print("Entrez l'ID de l'événement à supprimer : ");
                    int idEvent = scanner.nextInt();
                    eventDAO.deleteEvent(idEvent);
                }
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixEvenement != 0);
    }
}
