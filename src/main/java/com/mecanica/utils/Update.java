package com.mecanica.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Update {
    public <T> T by(final T entity, final T entityUpdate) {
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
    private <T> T Erro(final T entity, final T entityUpdate)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (Objects.nonNull(entity)) {
            for (final Field field : getDeclaredFields(entity)) {
                final Stream<String> declaredFields = Arrays.asList(entityUpdate.getClass().getFields())
                .stream().map(Field::getName)
                .filter(c -> c.equals(field.getName()));
                if (declaredFields.count() == 1) {
                    final String fieldNameEntity = field.getName();
                    final Field listField = entityUpdate.getClass().getDeclaredField(fieldNameEntity);
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
        final List<Field> declaredFields = Arrays.asList(entity.getClass().getFields());
        return declaredFields;
    }
}