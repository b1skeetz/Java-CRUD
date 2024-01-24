import DAO.UserDao;
import org.models.Vehicle;
import org.models.User;
import services.UserService;

import java.util.ArrayList;
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
                    String modelCreate, colorCreate;
                    System.out.println("=======Create=======");
                    System.out.print("Enter name: ");
                    String createName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int createAge = Integer.parseInt(scanner.nextLine());
                    System.out.print("Vehicles amount: ");
                    int vehicleAmount = Integer.parseInt(scanner.nextLine());
                    List<Vehicle> createVehicles = new ArrayList<>();
                    for(int i = 0; i < vehicleAmount; i++){
                        System.out.println("Enter model: ");
                        modelCreate = scanner.nextLine();
                        System.out.println("Enter color: ");
                        colorCreate = scanner.nextLine();
                        createVehicles.add(new Vehicle(modelCreate, colorCreate));
                    }
                    User createUser = new User();
                    createUser.setName(createName);
                    createUser.setAge(createAge);
                    createUser.setVehicles(createVehicles);
                    UserDao.save(createUser);
                    break;
                case 2:
                    String model, color;
                    System.out.println("=======Update=======");
                    System.out.print("Pick user by it's Id: ");
                    int existId = Integer.parseInt(scanner.nextLine());
                    User existingUser = UserDao.findById(existId);
                    System.out.print("Enter name: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int updateAge = Integer.parseInt(scanner.nextLine());
                    System.out.print("Vehicles amount: ");
                    /*int vehicleAmount = Integer.parseInt(scanner.nextLine());

                    for(int i = 0; i < vehicleAmount; i++){
                        System.out.println("Enter model: ");
                        model = scanner.nextLine();
                        System.out.println("Enter color: ");
                        color = scanner.nextLine();
                        createVehicles.add(new Vehicle(model, color));
                    }
                    User createUser = new User();
                    createUser.setName(createName);
                    createUser.setAge(createAge);
                    createUser.setVehicles(createVehicles);
                    UserDao.save(createUser);*/
                    break;
                case 3:
                    System.out.println("=======Delete=======");
                    System.out.print("Pick user by it's Id: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    System.out.print(String.format("Are you sure about it? User %s will be deleted forever! [y/n]: "));
                    String affirmation = scanner.nextLine();
                    System.out.println();
                    if(affirmation.equalsIgnoreCase("y") || affirmation.equalsIgnoreCase("yes")){
                        UserDao.delete(deleteId);
                        System.out.println("Delete operation is done");
                    } else if (affirmation.equalsIgnoreCase("n") || affirmation.equalsIgnoreCase("no")){
                        deleteId = 0;
                        System.out.println("Delete operation is canceled");
                    }
                    break;
                default:
                    System.out.println("This kind of operation doesn't exist! Try one more time.");
                    break;
            }
        }
    }
}