package com.gps1.parkingsol.service;

import com.gps1.parkingsol.entity.Booking;
import com.gps1.parkingsol.entity.Parking;
import com.gps1.parkingsol.entity.User;
import com.gps1.parkingsol.repository.Bookingrepo;
import com.gps1.parkingsol.repository.Parkingrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Bookingservice {

    @Autowired
    private Bookingrepo bookingrepo;

    @Autowired
    private Parkingrepo parkingrepo;

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

    public void createBooking(Booking booking) {
        System.out.println("Booking ID: " + booking.getId());
        System.out.println("Parking ID: " + booking.getId());
        System.out.println("Duration: " + booking.getDuration());
        bookingrepo.save(booking); // Save the booking first

        // Find the corresponding parking slot
        Optional<Parking> optionalParking = parkingrepo.findById(booking.getId());
        if (optionalParking.isPresent()) {
            Parking parking = optionalParking.get();
            parking.setDuration(booking.getDuration()); // Update the duration in Parking entity
            parkingrepo.save(parking); // Save the updated parking slot
        }
    }


    public List<Booking> findbookingbyuser(User user){
        return bookingrepo.findByUser(user);
    }
}
