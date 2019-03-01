package com.crhms.seabow.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "DW_BILL")
public class Bill implements  Serializable {

    private static final long serialVersionUID = -3258839839160856613L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String hospitalId;

    @Column
    private String hospitalName;

    @Column
    private Date createDate;

    @Column
    private  Date updateDate;

    @Column
    private  Date excuteDate;

    private  String docterName;

    private  String dockerId;

    private  String patientName;

    private  String patientId;


}
