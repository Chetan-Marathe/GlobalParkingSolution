package com.gps1.parkingsol.repository;

import com.gps1.parkingsol.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Messagerepo extends JpaRepository<Message,Long> {
}
