package com.gps1.parkingsol.controller;

import com.gps1.parkingsol.service.Bookingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Bookingcontroller {

    @Autowired
    private Bookingservice bookingservice;
}
