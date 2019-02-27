package com.crhms.seabow.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String permissiom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}
