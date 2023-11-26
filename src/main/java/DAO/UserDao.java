package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.models.Vehicle;
import org.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.EntityManagerFactoryUtil;

import java.util.List;

public class UserDao {
    public User findById(int id) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        manager.close();
        return manager.find(User.class, id);
    }

    public Vehicle findVehicleById(int id) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        manager.close();
        return manager.find(Vehicle.class, id);
    }

    public void save(User user) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(user);
            for (Vehicle vehicle : user.getVehicles()) {
                manager.persist(vehicle);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        manager.close();
    }
    public void delete(User user){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            /*for (Vehicle vehicle : user.getVehicles()) {
                manager.remove(vehicle);
            }*/
            manager.remove(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        manager.close();
    }
    public List<User> findAll(){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        List<User> users = null;
        try {
            users = (List<User>) manager.createQuery("From User").getResultList();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        manager.close();
        return users;
    }
}