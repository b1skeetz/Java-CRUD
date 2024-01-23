import org.models.Vehicle;
import org.models.User;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User("Andrey",64);
        Vehicle ferrari = new Vehicle("Mercedes", "black");
        ferrari.setUser(user);
        user.addVehicle(ferrari);
        Vehicle ford = new Vehicle("Lada", "pink");
        ford.setUser(user);
        user.addVehicle(ford);
        User selectedUser = userService.findUser(13);
        selectedUser.setName("Kiriko");
        selectedUser.getVehicles().get(0).setColor("green");
        userService.updateUser(selectedUser);
        //userService.saveUser(user);
        //userService.deleteUser(15);
    }
}