package com.crhms.seabow.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subTitles")
@Data
public class Subtitle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int lessionId;

    private  String startTime;

    private  String endTime;

    private String duration;

    private String content;

    private int length;

    private int sort;

}