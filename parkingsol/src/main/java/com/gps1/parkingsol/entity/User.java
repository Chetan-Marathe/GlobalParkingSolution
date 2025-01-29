package com.gps1.parkingsol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String email;

    public String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    public List<Booking> bookings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getOrders() {
        return bookings;
    }

    public void setOrders(List<Booking> orders) {
        this.bookings = orders;
    }

    public User(Long id, String name, String email, String password, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bookings = bookings;
    }
    public User(){

    }
}
