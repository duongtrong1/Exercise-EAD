package com.trongdv.enterpise.service;

import com.trongdv.enterpise.model.Employee;
import com.trongdv.enterpise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createOrUpdate(Employee employee) {
        employee.setCreatedAt(Instant.now());
        employee.setUpdatedAt(Instant.now());
        employee.setStatus(1);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> employeesWithPaginate(Specification specification, int page, int limit) {
        return employeeRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }
}
