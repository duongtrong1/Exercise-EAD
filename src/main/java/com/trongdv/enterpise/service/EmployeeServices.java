package com.trongdv.enterpise.service;

import com.trongdv.enterpise.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface EmployeeServices {

    void create(Employee employee);

    Optional<Employee> getById(Long id);

    void delete(Long id);

    Page<Employee> employeesWithPaginate(Specification specification, int page, int limit);
}
