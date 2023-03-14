package com.example.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roomType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "id_room_type", columnDefinition = "VARCHAR(255)", nullable = false)
    private UUID idRoomType;

    private String name;

    @OneToMany(mappedBy = "roomType")
    @JsonIgnore
    private List<Room> roomList;
}
