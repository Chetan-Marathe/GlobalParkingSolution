package com.gps1.parkingsol.repository;

import com.gps1.parkingsol.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface Parkingrepo extends JpaRepository<Parking,Long> {

    public Parking findByname(String name);

    @Transactional
    @Query(value = "SELECT setval(p_get_serial_sequence('user', 'id'), (SELECT MAX(id) FROM user))", nativeQuery = true)
    void resetUserSequence();
}

