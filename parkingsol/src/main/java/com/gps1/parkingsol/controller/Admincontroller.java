package com.gps1.parkingsol.controller;

import com.gps1.parkingsol.entity.Admin;
import com.gps1.parkingsol.entity.Booking;
import com.gps1.parkingsol.entity.Parking;
import com.gps1.parkingsol.entity.User;
import com.gps1.parkingsol.service.Adminservice;
import com.gps1.parkingsol.service.Bookingservice;
import com.gps1.parkingsol.service.ParkingService;
import com.gps1.parkingsol.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class Admincontroller {
    @Autowired
    private Adminservice adminservice;

    @Autowired
    public Userservice userservice;

    @Autowired
    private ParkingService parkingservice;

    @Autowired
    private Bookingservice bookingservice;

    @PostMapping("/admin/verify/credentials")
    public String verifyCredentials(@ModelAttribute("admin") Admin admin, Model model) {


        // Perform the check with the service
        if (adminservice.verifycredentials(admin.getEmail(), admin.getPassword())) {
            model.addAttribute("admin", new Admin());
            model.addAttribute("user", new User());
            model.addAttribute("parking", new Parking());
            return "redirect:/admin/home";
        }

        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }


    @GetMapping("/admin/home")
    public String adminhomepage(Model model){
        model.addAttribute("adminList",adminservice.getAlladmin());
        model.addAttribute("userList",userservice.getAlluser());
        model.addAttribute("parkingList",parkingservice.getAllparking());
        model.addAttribute("bookingList",bookingservice.getAllbooking());
        return "AdminHomePage";
    }


    @PostMapping("/add/admin")
    public String createadmin(Admin admin){
        adminservice.createadmin(admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/update/admin/{id}")
    public String updateadmin(@PathVariable Long id, Model model){
        model.addAttribute("admin",adminservice.getadminbyid(id));
        return "UpdateAdmin";
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin){
        adminservice.createadmin(admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/delete/admin/{id}")
    public String deleteadmin(@PathVariable Long id,Admin admin){
        adminservice.deleteadmin(admin,id);
        return "redirect:/admin/home";
    }
    // Show Signup Form
    @GetMapping("/user/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Make sure this matches the signup.html filename
    }

    // Process Signup Form
    @PostMapping("/user/signup")
    public String processSignup(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if (userservice.isEmailAlreadyExists(user.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Email already registered. Please login.");
            return "redirect:/user/signup";
        }
        userservice.createUser(user);
        redirectAttributes.addFlashAttribute("messageSuccess", "Registration successful! Please login.");
        return "redirect:/user/login";
    }





    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "UserLogin";
    }

    @PostMapping("/user/login")
    public String processLoginForm(User user, RedirectAttributes redirectAttributes) {
        if (userservice.verifycredentials(user.getEmail(), user.getPassword())) {
            user = userservice.findbyemail(user.getEmail());
            redirectAttributes.addAttribute("userId", user.getId());
            return "redirect:/user/home";
        } else {
            redirectAttributes.addAttribute("error", "Invalid email or password");
            return "redirect:/user/login";
        }
    }

    @PostMapping("/place/parking")
    public String placebooking(Booking booking, Long userId, RedirectAttributes redirectAttributes) {
        double totalAmount = booking.getPrice() * booking.getQuantity();
        booking.setAmount(totalAmount);
        booking.setDate(new Date());

        User user = userservice.getuserbyid(userId);
        booking.setUser(user);

        bookingservice.createBooking(booking);

        redirectAttributes.addAttribute("userId", userId);
        redirectAttributes.addAttribute("messageSuccess", "Booking has been confirmed");

        return "redirect:/user/home";
    }


    @PostMapping("/parking/search")
    public String parkingsearch(String name, Long userId , Model model){
        Parking parking = parkingservice.findbyname(name);
        User user = userservice.getuserbyid(userId);
        model.addAttribute("bookingList",bookingservice.findbookingbyuser(user));
        if(parking!=null){
            model.addAttribute("parking",parking);
        }else{
            model.addAttribute("messageError","Sorry parking not found");

        }
        model.addAttribute("userId",userId);

        return "BuyParkingPage";//BuyProductPage
    }

    @GetMapping("/user/home")
    public String userHome(@ModelAttribute("userId") Long userId,Model model,@ModelAttribute("error") String error,@ModelAttribute ("messageSuccess") String messageSuccess) {
        User user = userservice.getuserbyid(userId);
        model.addAttribute("bookingList", bookingservice.findbookingbyuser(user));
        if(!error.isEmpty()){
            model.addAttribute("error",error);
        }
        if(!messageSuccess.isEmpty()){
            model.addAttribute("messageSuccess",messageSuccess);
        }
        return "BuyParkingPage";
    }
}