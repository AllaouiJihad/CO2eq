import Config.DatabaseConnection;
import Entity.User;
import Repository.UserRepository;
import Service.UserService;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        DatabaseConnection.getInstance();
        Scanner scan = new Scanner(System.in);
        System.out.println("enter name :");
        String name = scan.nextLine();
        System.out.println("enter age");
        int age = scan.nextInt();
        User user = new User(name,age);

        UserRepository userRepository = new UserRepository(DatabaseConnection.getInstance().getConnection());
        UserService userService = new UserService(userRepository);



    }
}