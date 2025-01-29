package com.gps1.parkingsol.controller;
import com.gps1.parkingsol.entity.Message;
import com.gps1.parkingsol.entity.Admin;
import com.gps1.parkingsol.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {

    @Autowired
    private ParkingService parkingService;

    @GetMapping({"/home","/"})
    public String homepage(){
        return "homepage";
    }
    @GetMapping("/parkings")
    public String parkingpage( Model model ){
        model.addAttribute("parkingList",parkingService.getAllparking());
        return "Parking";
    }

    @GetMapping("/contact")
    public String contactpage(){
        return "contactpage";
    }

    @GetMapping("/aboutus")
    public String aboutus(){
        return "aboutus";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("admin",new Admin());
        return "Login";
    }

    @GetMapping("/contactus")
    public String contactpage(Model model){
        model.addAttribute("message",new Message());
        return "contactpage";
    }

}
