package com.gps1.parkingsol.service;

import com.gps1.parkingsol.entity.Parking;
import com.gps1.parkingsol.repository.Parkingrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {
    @Autowired
    private Parkingrepo parkingrepo;
    public List<Parking> getAllparking(){
        return parkingrepo.findAll();
    }

    public Parking getparkingbyid(Long id){
        return parkingrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updateparking(Parking parking,Long id){
        parkingrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        parkingrepo.save(parking);
    }

    public  void deleteparking(Parking parking,Long id){
        parkingrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        parkingrepo.deleteById(id);
    }

    public Parking findbyname(String name){
        return parkingrepo.findByname(name);
    }

    public void createparking( Parking parking ){
        parkingrepo.save(parking);
    }


}
