/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.util.Service;

import jakarta.persistence.EntityNotFoundException;
import java.lang.reflect.ParameterizedType;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author ADMIN
 */
public class Crud<T extends Object, D extends Object> implements ICrud<T, D> {

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public Crud() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Autowired
    private JpaRepository<T, Integer> jpaRepository;

    private void setDefaultValue(T entity, String fieldName, Object defaultValue) {
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(entity, defaultValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Xử lý exception nếu cần thiết
            e.printStackTrace();
        }
    }

    @Override
    public T Create(D Dto) {
        T newEntity;
        try {
            newEntity = BeanUtils.instantiateClass(entityClass);
        } catch (BeanInstantiationException e) {
            throw new RuntimeException("Không thể tạo đối tượng của kiểu " + entityClass, e);
        }
        // Copy thuộc tính từ DTO vào đối tượng mới
        BeanUtils.copyProperties(Dto, newEntity);
        setDefaultValue(newEntity, "isDelete", false);

        // Gọi phương thức save của JpaRepository
        return jpaRepository.save(newEntity);
    }

    @Override
    public T Update(D Dto, int id) {
        Optional<T> optionalEntity = jpaRepository.findById(id);

        if (optionalEntity.isPresent()) {
            T existingEntity = optionalEntity.get();

            BeanUtils.copyProperties(Dto, existingEntity);

            setDefaultValue(existingEntity, "isDelete", false);

            return jpaRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Không tìm thấy đối tượng có ID: " + id);
        }
    }

    @Override
    public T Delete(int id) {
        Optional<T> optionalEntity = jpaRepository.findById(id);

        if (optionalEntity.isPresent()) {
            T existingEntity = optionalEntity.get();

            setDefaultValue(existingEntity, "isDelete", true);

            return jpaRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Không tìm thấy đối tượng có ID: " + id);
        }
    }

    @Override
    public List<T> getAll() {
        // Lấy tất cả các thực thể từ JpaRepository
        List<T> allEntities = jpaRepository.findAll();

        // Lọc ra các thực thể với trường isDelete là false
        List<T> nonDeletedEntities = allEntities.stream()
                .filter(entity -> !isDeleted(entity))
                .collect(Collectors.toList());

        return nonDeletedEntities;
    }

    // Phương thức kiểm tra xem thực thể có trạng thái bị xóa không
    private boolean isDeleted(T entity) {
        try {
            Field field = entity.getClass().getDeclaredField("isDelete");
            field.setAccessible(true);
            return field.getBoolean(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Không thể truy cập trường isDelete", e);
        }
    }

}
