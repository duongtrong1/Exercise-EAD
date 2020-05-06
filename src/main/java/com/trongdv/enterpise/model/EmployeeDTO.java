package com.trongdv.enterpise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private long id;
    private String rollNumber;
    private String fullName;
    private String year;
    private String address;
    private String email;
    private String phone;
    private String identityCard;
    private Instant createdAt;
    private Instant updatedAt;
    private int status;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.rollNumber = employee.getRollNumber();
        this.fullName = employee.getFullName();
        this.year = employee.getYear();
        this.address = employee.getAddress();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.identityCard = employee.getIdentityCard();
        this.createdAt = employee.getCreatedAt();
        this.updatedAt = employee.getUpdatedAt();
        this.status = employee.getStatus();
    }
}
