package com.example.entity.controller;

import com.example.entity.Service.IRoomTypeService;
import com.example.entity.Service.RoomService;
import com.example.entity.models.Room;
import com.example.entity.models.RoomType;
import com.example.entity.models.User;
import com.example.entity.rep.IRoomRep;
import com.example.entity.rep.IRoomTypeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class Demo {

    @Autowired
    private IRoomRep iRoomRep;
    @Autowired
    private IRoomTypeService iRoomTypeService;

    @Autowired
    private RoomService roomService;

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/demo")
    public String createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            String err = "";

            for (String key : errors.keySet()) {
                err += "Lỗi ở: " + key + ", lí do: " + errors.get(key) + "\n";
            }
            return err;
        }
        return "Các trường truyền vào hợp lệ!";
    }

    @PostMapping("/add-roomtype")
    public ResponseEntity<RoomType> roomResponseEntity(@RequestBody RoomType roomType) {
        iRoomTypeService.save(roomType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-roomtype")
    public ResponseEntity<List<RoomType>> responseEntity() {
        List<RoomType> roomTypeList = iRoomTypeService.getAll();
        return new ResponseEntity<>(roomTypeList, HttpStatus.OK);
    }

    @PostMapping("/add-room")
    public ResponseEntity<Room> roomResponseEntity(@RequestBody Room room) {
        roomService.save(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-room")
    public ResponseEntity<List<Room>> listResponseEntity() {
        List<Room> roomList = iRoomRep.findAll();
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @DeleteMapping("/delete-roomtype/")
    public ResponseEntity<RoomType> deleteArea(@RequestParam UUID id) {
        Optional<RoomType> roomType = iRoomTypeService.getById(id);
        if (roomType.isPresent()) {
            iRoomTypeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/demo/{id}")
    public ResponseEntity<Room> hello(@PathVariable("id") UUID id) {
        Optional<Room> room = iRoomRep.findById(id);
        if (room.isPresent()) {
            iRoomRep.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

}
