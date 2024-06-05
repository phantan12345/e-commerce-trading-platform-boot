/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.util;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author ADMIN
 */
public class GenericSpecifications {

    public static <T> Specification<T> fieldEquals(String fieldName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), value);
    }

    public static <T> Specification<T> fieldContains(String fieldName, String value) {
        return (root, query, builder) -> builder.like(root.get(fieldName), "%" + value + "%");
    }

    public static <T> Specification<T> hasThan(String fieldName, String value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(fieldName), value);
    }

    public static <T> Specification<T> hasLess(String fieldName, String value) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(fieldName), value);
    }

    public static <T> Specification<T> sort(String fieldName, String direction) {
        return (root, query, builder) -> {
            if ("asc".equalsIgnoreCase(direction)) {
                query.orderBy(builder.asc(root.get(fieldName)));
            } else if ("desc".equalsIgnoreCase(direction)) {
                query.orderBy(builder.desc(root.get(fieldName)));
            }
            return null; // return null because we're only modifying the query, not adding predicates
        };
    }

    public static <T> Specification<T> createSpecification(List<Specification<T>> Specifications) {
        Specification<T> combinedSpec = null;

        if (Specifications.size() >= 0) {
            for (Specification<T> spec : Specifications) {
                if (combinedSpec == null) {
                    combinedSpec = Specification.where(spec);
                } else {
                    combinedSpec = combinedSpec.and(spec);
                }
            }
        }
        return combinedSpec;
    }
}
