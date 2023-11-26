import jakarta.persistence.EntityManager;
import org.models.Vehicle;
import org.models.User;
import services.UserService;
import utils.EntityManagerFactoryUtil;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserService();
        User user = new User("Kirill",41);
        Vehicle ferrari = new Vehicle("Ferrari", "red");
        ferrari.setUser(user);
        user.addVehicle(ferrari);
        Vehicle ford = new Vehicle("Ford", "black");
        ford.setUser(user);
        user.addVehicle(ford);
        //userService.saveUser(user);
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        User userToDelete = manager.find(User.class, 14);
        userService.deleteUser(userToDelete);
        manager.close();
    }
}