package com.kuzmin.lab.jokes_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

@Accessors(chain = true)
@Data
@Entity(name = "roles")
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
