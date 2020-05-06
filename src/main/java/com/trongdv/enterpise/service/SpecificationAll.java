package com.trongdv.enterpise.service;

import com.trongdv.enterpise.model.Employee;
import com.trongdv.enterpise.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SpecificationAll implements Specification<Employee> {

    private SearchCriteria criteria;

    public SpecificationAll(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("orderBy")) {
            if (criteria.getValue().toString().equals("desc")) {
                criteriaQuery.orderBy(builder.desc(root.get(criteria.getKey())));
            }
            if (criteria.getValue().toString().equals("asc")) {
                criteriaQuery.orderBy(builder.asc(root.get(criteria.getKey())));
            }
        }

        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
