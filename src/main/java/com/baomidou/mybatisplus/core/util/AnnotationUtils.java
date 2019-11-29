package com.baomidou.mybatisplus.core.util;

import com.baomidou.mybatisplus.annotation.TableId;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/11/29.
 * Description:
 */
public final class AnnotationUtils {
    private AnnotationUtils() {
    }

    public static Object getFirst(Object o, Class annotation) {
        if (o == null || annotation == null) {
            return null;
        }
        return AnnotationUtils.getStream(o, annotation)
                .findFirst()
                .orElse(null);
    }


    private static Stream<Object> getStream(Object o, Class annotation) {
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        return Stream.of(fields)
                .parallel()
                .map(field -> {
                    Annotation desc = field.getAnnotation(annotation);
                    if (desc != null) {
                        try {
                            field.setAccessible(true);
                            return field.get(o);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull);
    }
}
