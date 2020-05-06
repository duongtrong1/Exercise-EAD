package com.trongdv.enterpise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = "rollNumber"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone"),
        @UniqueConstraint(columnNames = "identityCard")})
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "rollNumber")
    private String rollNumber;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "year")
    private String year;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "identityCard")
    private String identityCard;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "updatedAt")
    private Instant updatedAt;

    @Column(name = "status")
    private int status; // active, inactive
}
