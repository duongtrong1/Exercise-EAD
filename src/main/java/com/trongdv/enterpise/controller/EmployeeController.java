package com.trongdv.enterpise.controller;

import com.trongdv.enterpise.model.*;
import com.trongdv.enterpise.service.EmployeeServices;
import com.trongdv.enterpise.service.SpecificationAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping
    public ResponseEntity<Object> getListPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(defaultValue = "1", required = false) @NumberFormat(style = NumberFormat.Style.NUMBER) Integer page,
            @RequestParam(defaultValue = "10", required = false) @NumberFormat(style = NumberFormat.Style.NUMBER) Integer limit) {

        Specification specification = Specification.where(null);
        if (search != null && search.length() > 0) {
            specification = new SpecificationAll(new SearchCriteria("fullName", ":", search))
                    .or(new SpecificationAll(new SearchCriteria("address", ":", search))
                            .or(new SpecificationAll(new SearchCriteria("year", ":", search))
                                    .or(new SpecificationAll(new SearchCriteria("phone", ":", search))
                                            .or(new SpecificationAll(new SearchCriteria("email", ":", search))
                                                    .or(new SpecificationAll(new SearchCriteria("rollNumber", ":", search))
                                                            .or(new SpecificationAll(new SearchCriteria("identityCard", ":", search))
                                                                    .or(specification)))))));
        }

        specification = new SpecificationAll(new SearchCriteria("createdAt", "orderBy", "desc"))
                .and(specification);

        Page<Employee> employeePage = employeeServices.employeesWithPaginate(specification, page, limit);
        return new ResponseEntity<>(new ResponseDatePagination<>(
                HttpStatus.OK.value(), "Success",
                employeePage.stream().map(EmployeeDTO::new).collect(Collectors.toList()),
                new ResponseDatePagination.Page(page, limit, employeePage.getTotalPages(), employeePage.getTotalElements())),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Employee employee) {
        try {
            employeeServices.create(employee);
            return new ResponseEntity<>(new ResponseData<>(HttpStatus.OK.value(), "Success", employee), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<Employee> employee = employeeServices.getById(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Id not found", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.OK.value(), "Success", employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        Optional<Employee> employee = employeeServices.getById(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Id not found", null), HttpStatus.NOT_FOUND);
        }
        employeeServices.delete(id);
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.OK.value(), "Success", null), HttpStatus.OK);
    }
}
