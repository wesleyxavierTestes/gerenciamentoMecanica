package com.mecanica.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Update {
    public <T, Y> Y by(final T entity, final Y entityUpdate) {
        try {
            return Erro(entity, entityUpdate);
        } catch (Exception e) {
            return entityUpdate;
        }
    }

    /**
     * Valida Entidade e os Objetos ou Arrays filhos E seta uma nova instancia de
     * Validations
     * 
     * @param <T>
     * @param entity     Classes com anotation do javax.validation
     * @param entityName
     * @return Lista de Validations
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    private <T, Y> Y Erro(final T entity, final Y entityUpdate)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (Objects.nonNull(entity)) {
            List<Field> entityDeclaredFields = getDeclaredFields(entity);
            List<Field> declaredFields = getDeclaredFields(entityUpdate);

            for (final Field field : entityDeclaredFields) {
                Constructor[] classes = field.getClass().getConstructors();
                Optional<Field> fieldUpdate = declaredFields.stream().filter(c -> c.getName().equals(field.getName()))
                        .findFirst();

                if (fieldUpdate.isPresent()) {
                    final Field listField = fieldUpdate.get();
                    listField.setAccessible(true);
                    Object objectValue = field.get(entity);
                    listField.set(entityUpdate, objectValue);
                }
            }
        }
        return entityUpdate;
    }

    /**
     * Lista as propriedades do objeto via reflexão
     * 
     * @param <T>
     * @param entity
     * @return
     */
    private <T> List<Field> getDeclaredFields(final T entity) {
        Class<? extends Object> entityClass = entity.getClass();
        List<Field> fields = new ArrayList<>();
        Field[] fieldsEntityClass = entityClass.getFields();
        if (fieldsEntityClass != null && fieldsEntityClass.length > 0) {
            for (Field field : fieldsEntityClass) {
                field.setAccessible(true);
                fields.add(field);
            }
        }
        Class<?> superclass = entityClass.getSuperclass();
        while (superclass != null) {
            Field[] declaredFields = superclass.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (fields == null) {
                        fields = new ArrayList<>();
                    }
                    field.setAccessible(true);
                    fields.add(field);
                }
            }
            superclass = superclass.getSuperclass();
        }
            
        return fields;
    }
}