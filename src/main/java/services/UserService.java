package services;

import DAO.UserDao;
import org.models.Vehicle;
import org.models.User;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public UserService(){

    }
    public User findUser(int id){
        return userDao.findById(id);
    }

    public void saveUser(User user){
        userDao.save(user);
    }
    public void deleteUser(int id){
        userDao.delete(id);
    }
    public void updateUser(User user){
        userDao.update(user);
    }
    public List<User> findAllUsers(){
        return userDao.findAll();
    }
    public Vehicle findVehicleById(int id){
        return userDao.findVehicleById(id);
    }
}