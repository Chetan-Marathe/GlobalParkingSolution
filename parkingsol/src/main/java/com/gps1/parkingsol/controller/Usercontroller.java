package com.gps1.parkingsol.controller;

import com.gps1.parkingsol.entity.User;
import com.gps1.parkingsol.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Usercontroller {
    @Autowired
    private Userservice userservice;

    @PostMapping("/add/user")
    public String adduser(User user){
        userservice.createuser(user);
        return "Login";
    }

    @GetMapping("/update/user/{id}")
    public String updateuser(@PathVariable Long id, Model model){
        model.addAttribute("user",userservice.getuserbyid(id));
        return "UpdateUser";
    }

    @PostMapping("/update/user")
    public String updateuser(User user){
        userservice.createuser(user);
        return "redirect:/admin/home";
    }

    @GetMapping("/delete/user/{id}")
    public String deleteuser(@PathVariable Long id,User user){
        userservice.deleteuser(user,id);
        return "redirect:/admin/home";
    }
}
