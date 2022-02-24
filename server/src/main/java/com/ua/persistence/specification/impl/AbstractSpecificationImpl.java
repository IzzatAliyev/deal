package com.ua.persistence.specification.impl;

import org.springframework.data.jpa.domain.Specification;
import com.ua.persistence.entity.BaseEntity;
import com.ua.persistence.specification.AbstractSpecification;
import com.ua.util.SpecificationUtil;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public class AbstractSpecificationImpl<E extends BaseEntity> implements AbstractSpecification<E> {

    @Override
    public Specification<E> generateCriteriaPredicate(Map<String, String[]> parameterMap, Class<E> entityClass) {
        return (Specification<E>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = SpecificationUtil.generateSpecificationPredicates(parameterMap, entityClass, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
