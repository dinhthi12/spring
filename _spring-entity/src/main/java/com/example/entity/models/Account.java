package com.example.entity.models;

import com.example.entity.Validate.ValidateUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    //@ValidateUsername //custom
    private String username;

    private String password;

    @OneToOne(mappedBy = "account")
    private User user;
}
