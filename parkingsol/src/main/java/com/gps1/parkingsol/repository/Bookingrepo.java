package com.gps1.parkingsol.repository;

import com.gps1.parkingsol.entity.Booking;
import com.gps1.parkingsol.entity.Parking;
import com.gps1.parkingsol.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Bookingrepo extends JpaRepository<Booking,Long> {
    List<Booking> findByUser(User user);

}
