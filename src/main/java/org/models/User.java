package org.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Vehicle> vehicles = new ArrayList<>();

    public User(){

    }
    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void addVehicle(Vehicle vehicle){
        vehicle.setUser(this);
        vehicles.add(vehicle);
    }
    public void removeAuto(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id = " + getId() +
                ", name = `" + getName() + "`" +
                ", age = " + getAge() +
                "}";
    }
}