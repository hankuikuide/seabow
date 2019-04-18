package com.crhms.seabow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private Integer id;

    private String name;

    private String password;

    private String userName;

    private String phone;

    private String address;

    private String email;

    private Integer state;
}
