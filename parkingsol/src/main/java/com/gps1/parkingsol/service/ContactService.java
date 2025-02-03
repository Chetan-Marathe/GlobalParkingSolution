package com.gps1.parkingsol.service;

import com.gps1.parkingsol.entity.Message;
import com.gps1.parkingsol.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepo messageRepository;

    public List<Message> getAllmessage(){
        return messageRepository.findAll();
    }

    public Message getmessagebyid(Long id){
        return messageRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updatemessage(Message message ,Long id){
        messageRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        messageRepository.save(message);
    }

    public  void deletemessage(Message message,Long id){
        messageRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        messageRepository.deleteById(id);
    }

    public void createmessage( Message message ){
        messageRepository.save(message);
    }

}
