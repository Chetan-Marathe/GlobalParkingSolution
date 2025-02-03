package com.gps1.parkingsol.service;

import com.gps1.parkingsol.entity.User;
import com.gps1.parkingsol.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userservice {
    @Autowired
    private Userrepo userrepo;

    public List<User> getAlluser(){
        return userrepo.findAll();
    }

    public User getuserbyid(Long id){
        return userrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updateuser(User user,Long id){
        userrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        userrepo.save(user);
    }

    public  void deleteuser(User user , Long id){
        userrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        userrepo.deleteById(id);
    }

    public boolean createuser(User user) {
        try {
            userrepo.save(user);  // Save the user to the database
            return true;  // If the user was successfully saved, return true
        } catch (Exception e) {
            e.printStackTrace();  // Log any exception (optional)
            return false;  // If an exception occurs, return false
        }
    }


    public User findbyemail(String email){
        return userrepo.findByemail(email);
    }



    public boolean verifycredentials(String email ,String password){
        User user = userrepo.findByemail(email);
        if(user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
