package com.gps1.parkingsol.repository;

import com.gps1.parkingsol.entity.Admin;
import com.gps1.parkingsol.service.Adminservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Adminrepo extends JpaRepository<Admin,Long> {

    public Admin findByemail(String email);


    @Transactional
    @Query(value = "SELECT setval(pg_get_serial_sequence('admin', 'id'), (SELECT MAX(id) FROM admins))", nativeQuery = true)
    void resetAdminSequence();




}
