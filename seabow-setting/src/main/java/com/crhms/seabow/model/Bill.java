package com.crhms.seabow.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DW_BILL")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String hospitalId;

    @Column
    private String hospitalName;

}
