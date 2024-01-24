package DAO;

import jakarta.persistence.*;
import org.hibernate.Transaction;
import org.models.Vehicle;
import org.models.User;
import utils.EntityManagerFactoryUtil;

import java.util.List;

public class UserDao {

    public static User findById(int id) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        User user;
        try{
            TypedQuery<User> findByIdQuery = manager.createQuery("select u from User u where u.id = ?1", User.class);
            findByIdQuery.setParameter(1, id);
            user = findByIdQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        manager.close();
        return user;
    }

    public static Vehicle findVehicleById(int id) {
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        Vehicle vehicle;
        try{
            TypedQuery<Vehicle> findByIdQuery = manager.createQuery("select v from Vehicle v where v.id = ?1", Vehicle.class);
            findByIdQuery.setParameter(1, id);
            vehicle = findByIdQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        manager.close();
        return vehicle;
    }

    public static Vehicle findVehicleByUser(User user, int id){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        Vehicle vehicle;
        try{
            TypedQuery<Vehicle> findByIdQuery = manager.createQuery("select v from Vehicle v where v.id = ?1 and v.user.id = ?2", Vehicle.class);
            findByIdQuery.setParameter(1, id);
            findByIdQuery.setParameter(2, user.getId());
            vehicle = findByIdQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        manager.close();
        return vehicle;
    }

    public static void save(User user) {
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
    public static void delete(int id){
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
    public static void update(User user){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            Query updateUser = manager.createQuery("update User u set u.name = ?1, u.age = ?2 where u.id = ?3");
            updateUser.setParameter(1, user.getName());
            updateUser.setParameter(2, user.getAge());
            updateUser.setParameter(3, user.getId());
            updateUser.executeUpdate();

            Query updateVehicle = manager.createQuery("update Vehicle v set v.model = ?1, v.color = ?2 " +
                    "where v.id = ?3");
            for(int i = 0; i < user.getVehicles().size(); i++){
                updateVehicle.setParameter(1, user.getVehicles().get(i).getModel());
                updateVehicle.setParameter(2, user.getVehicles().get(i).getColor());
                updateVehicle.setParameter(3, user.getVehicles().get(i).getId());
                updateVehicle.executeUpdate();
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }
        manager.close();
    }
    public static List<User> findAll(){
        EntityManager manager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> getAllUsersQuery = manager.createQuery("select u from User u", User.class);
        List<User> users = getAllUsersQuery.getResultList();
        manager.close();
        return users;
    }
}