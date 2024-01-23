package org.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public User() {

    }

    public void addVehicle(Vehicle vehicle){
        vehicle.setUser(this);
        vehicles.add(vehicle);
    }
    public void removeAuto(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    @Override
    public String toString() {
        StringBuilder vehicles = new StringBuilder();
        for (Vehicle vehicle : getVehicles()) {
            vehicles.append(vehicle.getColor()).append(" ").append(vehicle.getModel()).append("\n");
        }
        return String.format("Mr. (Miss) %s %d years old has:\n%s", getName(), getAge(), vehicles);
    }
}