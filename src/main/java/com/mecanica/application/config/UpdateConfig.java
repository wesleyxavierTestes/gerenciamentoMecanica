package com.mecanica.application.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.mecanica.application.exceptions.ValidacaoControllerBaseException;

public class UpdateConfig {
    public static <T, Y> void by(final T entity, final Y entityUpdate) {
        try {
            new UpdateConfig().Erro(entity, entityUpdate);
        } catch (Exception e) {
            throw new ValidacaoControllerBaseException("Falha ao atualizar");
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
    private <T, Y> void Erro(final T entity, final Y entityUpdate)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (Objects.nonNull(entity)) {
            List<Field> entityDeclaredFields = getDeclaredFields(entity);
            List<Field> declaredFields = getDeclaredFields(entityUpdate);

            for (final Field field : entityDeclaredFields) {
                Optional<Field> optionalFieldUpdate = declaredFields.stream()
                        .filter(c -> c.getName().equals(field.getName())).findFirst();

                if (optionalFieldUpdate.isPresent()) {
                    final Field fieldUpdate = optionalFieldUpdate.get();
                    fieldUpdate.setAccessible(true);
                    Object objectValue = field.get(entity);
                    Object objectValueUpdate = fieldUpdate.get(entityUpdate);
                    
                    if (Objects.nonNull(objectValue) && !objectValue.equals(objectValueUpdate)) {
                        fieldUpdate.set(entityUpdate, objectValue);
                    } else if (Objects.nonNull(objectValueUpdate) && !objectValueUpdate.equals(objectValue)) {
                        fieldUpdate.set(entityUpdate, objectValue);
                    }
                }
            }
        }
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