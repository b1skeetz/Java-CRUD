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
            System.out.println("Choose operation:\n" +
                    "1. Create\n" +
                    "2. Update\n" +
                    "3. Delete\n" +
                    "4. Exit");
            int operation = Integer.parseInt(scanner.nextLine());
            switch(operation){
                case 1:
                    String modelCreate, colorCreate;
                    System.out.println("=======Create=======");
                    System.out.print("Enter name: ");
                    String createName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    Integer createAge = Integer.valueOf(scanner.nextLine());
                    if(createName.isEmpty()){
                        createName = "null";
                    }
                    System.out.print("Vehicles amount: ");
                    int vehicleAmount = Integer.parseInt(scanner.nextLine());
                    List<Vehicle> createVehicles = new ArrayList<>();
                    User createUser = new User();
                    for(int i = 0; i < vehicleAmount; i++){
                        System.out.println("Enter model: ");
                        modelCreate = scanner.nextLine();
                        System.out.println("Enter color: ");
                        colorCreate = scanner.nextLine();
                        Vehicle vehicle = new Vehicle(modelCreate, colorCreate);
                        vehicle.setUser(createUser);
                        createVehicles.add(vehicle);
                    }
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
                    int updateAge = Integer.valueOf(scanner.nextLine());

                    if(updateName.isEmpty()){
                        updateName = existingUser.getName();
                    }
                    if(updateAge == 0){
                        updateAge = existingUser.getAge();
                    }
                    existingUser.setName(updateName);
                    existingUser.setAge(updateAge);
                    for(int i = 0; i < existingUser.getVehicles().size(); i++){
                        System.out.println("Enter model: ");
                        model = scanner.nextLine();
                        System.out.println("Enter color: ");
                        color = scanner.nextLine();
                        if(model.isEmpty()){
                            existingUser.getVehicles().get(i).setModel(existingUser.getVehicles().get(i).getModel());
                        }
                        if(color.isEmpty()){
                            existingUser.getVehicles().get(i).setColor(existingUser.getVehicles().get(i).getColor());
                        }
                        existingUser.getVehicles().get(i).setModel(model);
                        existingUser.getVehicles().get(i).setColor(color);
                    }
                    UserDao.update(existingUser);
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
                case 4:
                    System.out.println("=======Exit=======");
                    System.out.println("Do you want to exit? [y/n]: ");
                    String exit = scanner.nextLine();
                    System.out.println();
                    if(exit.equalsIgnoreCase("y") || exit.equalsIgnoreCase("yes")){
                        System.out.println("Good bye!");
                        isWork = false;
                    } else if (exit.equalsIgnoreCase("n") || exit.equalsIgnoreCase("no")){
                        System.out.println("You are still here");
                    }
                    break;
                default:
                    System.out.println("This kind of operation doesn't exist! Try one more time.");
                    break;
            }
        }
    }
}