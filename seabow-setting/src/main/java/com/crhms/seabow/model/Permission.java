package com.crhms.seabow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String permissiom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissiom='" + permissiom + '\'' +
                '}';
    }
}
