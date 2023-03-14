package com.example.entity.rep;

import com.example.entity.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRoomTypeRep extends JpaRepository<RoomType, UUID> {
}
