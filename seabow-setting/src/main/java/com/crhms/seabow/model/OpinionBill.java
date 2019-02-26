package com.crhms.seabow.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "OPN_BILL")
public class OpinionBill {

    @Id
    private Long Id;


    private Long HisId;

    private String trickState;
}
