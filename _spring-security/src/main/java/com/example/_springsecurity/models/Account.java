package com.example._springsecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String username;
    private String password;
    private boolean enable;

    @Enumerated(EnumType.STRING)
    private  ERole role;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    public boolean getEnable() {
        return this.enable;
    }
}
