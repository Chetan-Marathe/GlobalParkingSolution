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

    public boolean isEmailAlreadyExists(String email) {
        return userrepo.findByemail(email) != null;
    }

    public void createUser(User user) {
        // Hash password if needed before saving (recommended)
        userrepo.save(user);
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
