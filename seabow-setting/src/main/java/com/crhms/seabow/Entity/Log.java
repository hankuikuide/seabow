package com.crhms.seabow.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String operator;
    private Date createDate;
    private String content;
    private String name;

}
