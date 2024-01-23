import org.models.Vehicle;
import org.models.User;
import services.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        boolean isWork = true;
        while(isWork){
            List<User> users = userService.findAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
}