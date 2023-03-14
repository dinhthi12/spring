package com.example.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private UUID idRoom;

    private String nameRoom;

    private int cap;

    @ManyToOne
    @JoinColumn(name = "id_room_type", nullable = false, referencedColumnName = "id_room_type")
    private RoomType roomType;
}
