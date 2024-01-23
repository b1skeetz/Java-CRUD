import org.models.Vehicle;
import org.models.User;
import services.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO Add Vehicle DAO to certain user
        // TODO Add VehicleService
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        boolean isWork = true;
        while(isWork){
            System.out.println("========================");
            List<User> users = userService.findAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
            System.out.println("========================");
            System.out.println("Choose operation: \n" +
                    "1. Create\n" +
                    "2. Update\n" +
                    "3. Delete\n");
            int operation = Integer.parseInt(scanner.nextLine());
            switch(operation){
                case 1:
                    // TODO Create user with vehicles clause
                    break;
                case 2:
                    // TODO Update user with vehicles clause
                    break;
                case 3:
                    // TODO Delete user with vehicles clause
                    break;
                default:
                    System.out.println("This kind of operation doesn't exist! Try one more time.");
                    break;
            }
        }
    }
}