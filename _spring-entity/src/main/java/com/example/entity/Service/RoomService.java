package com.example.entity.Service;

import com.example.entity.models.Room;
import com.example.entity.models.RoomType;
import com.example.entity.rep.IRoomRep;
import com.example.entity.rep.IRoomTypeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRep iRoomRep;


    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public Optional<Room> getById(UUID key) {
        return iRoomRep.findById(key);
    }

    @Override
    public Room save(Room entity) {
        return iRoomRep.save(entity);
    }

    @Override
    public void deleteById(UUID key) {
        iRoomRep.deleteById(key);
    }
}
