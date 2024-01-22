package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.models.Vehicle;
import org.models.User;
import utils.EntityManagerFactoryUtil;

import java.util.List;

public class UserDao {

    public User findById(int id) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> findByIdQuery = manager.createQuery("select u from User u where u.id = ?1", User.class);
        findByIdQuery.setParameter(1, id);
        User user = findByIdQuery.getSingleResult();
        manager.close();
        return user;
    }

    public Vehicle findVehicleById(int id) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Vehicle> findByIdQuery = manager.createQuery("select v from Vehicle v where v.id = ?1", Vehicle.class);
        findByIdQuery.setParameter(1, id);
        Vehicle vehicle = findByIdQuery.getSingleResult();
        manager.close();
        return vehicle;
    }

    public void save(User user) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(user);
            for (Vehicle vehicle : user.getVehicles()) {
                manager.persist(vehicle);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }
        manager.close();
    }
    public void delete(int id){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            TypedQuery<User> findByIdQuery = manager.createQuery("select u from User u where u.id = ?1", User.class);
            findByIdQuery.setParameter(1, id);
            User userForDelete = findByIdQuery.getSingleResult();
            for (int i = 0; i < userForDelete.getVehicles().size(); i++) {
                manager.remove(userForDelete.getVehicles().get(i));
            }
            manager.remove(userForDelete);
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }
        manager.close();
    }
    public void update(User user){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            Query updateUser = manager.createQuery("update User u set u.name = ?1, u.age = ?2, u.vehicles = ?3 where u.id = ?4", User.class);
            updateUser.setParameter(1, user.getName());
            updateUser.setParameter(2, user.getAge());
            updateUser.setParameter(3, user.getVehicles());
            updateUser.setParameter(4, user.getId());
            updateUser.executeUpdate();
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }

        manager.close();
    }
    public List<User> findAll(){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> getAllUsersQuery = manager.createQuery("select u from User u", User.class);
        List<User> users = getAllUsersQuery.getResultList();
        manager.close();
        return users;
    }
}