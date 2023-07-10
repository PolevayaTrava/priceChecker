package com.example.pricechecker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("roles")
    private List<User> users;
}
