package com.gps1.parkingsol.controller;

import com.gps1.parkingsol.entity.Parking;
import com.gps1.parkingsol.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Parkingcontroller {

    @Autowired
    private ParkingService parkingservice;

    @PostMapping("/add/parking")
    public String addparking(Parking parking){
        parkingservice.createparking(parking);
        return "redirect:/admin/home";
    }
    @GetMapping("/update/parking/{id}")
    public String updateparking(@PathVariable Long id, Model model){
        model.addAttribute("product",parkingservice.getparkingbyid(id));
        return "UpdateParking";
    }

    @PostMapping("/update/parking")
    public String Updateparking(Parking parking){
        parkingservice.updateparking(parking, parking.getId());
        return "redirect:/admin/home";
    }

    @GetMapping("/delete/parking/{id}")
    public String delteparking(@PathVariable Long id,Parking parking){
        parkingservice.deleteparking(parking,id);
        return "redirect:/admin/home";
    }
}
