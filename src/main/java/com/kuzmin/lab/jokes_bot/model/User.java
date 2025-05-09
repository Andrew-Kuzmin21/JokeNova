package com.kuzmin.lab.jokes_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @Column(name = "username")
    private String username;

}
