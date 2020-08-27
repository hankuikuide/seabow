package com.crhms.seabow.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "lession")
@Data
public class Lession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
}
