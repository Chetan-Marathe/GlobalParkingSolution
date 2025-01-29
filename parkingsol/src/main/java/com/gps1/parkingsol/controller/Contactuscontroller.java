package com.gps1.parkingsol.controller;

import com.gps1.parkingsol.entity.Message;
import com.gps1.parkingsol.service.Messageservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Contactuscontroller {

    @Autowired
    public Messageservice messageservice;

    @PostMapping("/send/message")
    public String sendMessage(Message message, Model model){
        messageservice.createmessage(message);
        model.addAttribute("confirmation","Your Message has been Successfully send");
        return "contactpage";
    }
}
