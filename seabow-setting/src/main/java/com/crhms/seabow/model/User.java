package com.crhms.seabow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Role> roles;

    private String salt;

}
