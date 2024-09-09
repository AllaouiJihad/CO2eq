import Config.DatabaseConnection;
import Entity.User;
import Repository.UserRepository;
import Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        int choix;
        UserRepository userRepository = new UserRepository(DatabaseConnection.getInstance().getConnection());
        UserService userService = new UserService(userRepository);
        do {
            System.out.println("***************************************************");
            System.out.println("*        *** Bienvenue sur GreenPulse ***         *");
            System.out.println("***************************************************");
            System.out.println("*                                                 *");
            System.out.println("*       1. Ajouter utilisateur                    *");
            System.out.println("*       2. Modifier utilisateur                   *");
            System.out.println("*       3. Supprimer utilisateur                  *");
            System.out.println("*       4. Afficher les utilisateurs              *");
            System.out.println("*       5. Afficher un utilisateur                *");
            System.out.println("*       6. Ajouter votre consommation de carbone  *");
            System.out.println("*       7. Afficher consommation d'un utilisateur *");
            System.out.println("*       8. Rapport quotidien                      *");
            System.out.println("*       0. Quitter                                *");
            System.out.println("*                                                 *");
            System.out.println("**********************************************");
            System.out.println("Veuillez choisir une option :");
            Scanner scan = new Scanner(System.in);
            choix = scan.nextInt();
            scan.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Enter name:");
                    String name = scan.nextLine();
                    System.out.println("Enter age:");
                    int age = scan.nextInt();

                    User user = new User(name, age);



                    userService.createUser(user).ifPresent(u -> System.out.println("User created , Id :" + u.getId() + " ,name :" + u.getName() + ", age :" + u.getAge()));
                    break;
                case 2:
                    System.out.println("Enter ID of the user to update:");
                    int id = scan.nextInt();
                    scan.nextLine();

                    Optional<User> existingUser = userService.findUserById(id);
                    if (existingUser.isPresent()) {
                        System.out.println("Enter new name:");
                        String newName = scan.nextLine();

                        System.out.println("Enter new age:");
                        int newAge = scan.nextInt();

                        User userToUpdate = new User(id, newName, newAge);

                        Optional<User> updatedUser = userService.updateUser(userToUpdate);
                        if (updatedUser.isPresent()) {
                            System.out.println("User updated successfully: " + updatedUser.get().getName());
                        } else {
                            System.out.println("Failed to update user.");
                        }
                    } else {
                        System.out.println("User with ID " + id + " not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter ID of the user to delete:");
                    int idToDelete = scan.nextInt();
                    scan.nextLine();

                    Optional<User> userToDelete = userService.findUserById(idToDelete);
                    if (userToDelete.isPresent()) {
                        boolean isDeleted = userService.deleteUser(idToDelete);
                        if (isDeleted) {
                            System.out.println("User with ID " + idToDelete + " deleted successfully.");
                        } else {
                            System.out.println("Failed to delete user with ID " + idToDelete + ".");
                        }
                    } else {
                        System.out.println("User with ID " + idToDelete + " not found.");
                    }
                    break;
                case 4:
                    // Récupérer la liste de tous les utilisateurs
                    List<User> users = userService.getAll();

                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        System.out.println("List of Users:");
                        for (User u : users) {
                            System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Age: " + u.getAge());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter the ID of the user to display:");
                    int idToFind = scan.nextInt();
                    scan.nextLine();  // Nettoyer le buffer de l'entrée

                    Optional<User> foundUser = userService.findUserById(idToFind);
                    if (foundUser.isPresent()) {
                        User findUser = foundUser.get();
                        System.out.println("User found: ID: " + findUser.getId() + ", Name: " + findUser.getName() + ", Age: " + findUser.getAge());
                    } else {
                        System.out.println("User with ID " + idToFind + " not found.");
                    }
                    break;





            }


        } while (choix != 0);
    }

    }
