package com.gps1.parkingsol.service;

import com.gps1.parkingsol.entity.Booking;
import com.gps1.parkingsol.entity.User;
import com.gps1.parkingsol.repository.Bookingrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Bookingservice {

    @Autowired
    private Bookingrepo bookingrepo;

    public List<Booking> getAllbooking(){
        return bookingrepo.findAll();
    }

    public Booking bookingbyid(Long id){
        return bookingrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updatebooking(Booking booking,Long id){
        bookingrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        bookingrepo.save(booking);
    }

    public  void deletebooking(Booking booking , Long id){
        bookingrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        bookingrepo.deleteById(id);
    }

    public void createbooking( Booking booking){
        bookingrepo.save(booking);
    }

    public List<Booking> findbookingbyuser(User user){
        return bookingrepo.findByUser(user);
    }
}
