package com.example.entity.Service;

import com.example.entity.models.RoomType;
import com.example.entity.rep.IRoomTypeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomTypeService implements IRoomTypeService {

    @Autowired
    private IRoomTypeRep iRoomTypeRep;

    @Override
    public List<RoomType> getAll() {
        return iRoomTypeRep.findAll();
    }

    @Override
    public Optional<RoomType> getById(UUID key) {
        return iRoomTypeRep.findById(key);
    }

    @Override
    public RoomType save(RoomType entity) {
        return null;
    }

    @Override
    public void deleteById(UUID key) {
        iRoomTypeRep.deleteById(key);
    }
}
