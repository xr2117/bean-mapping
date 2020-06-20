package org.crazy.utils;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.crazy.common.Assert;
import org.crazy.exception.BeanMappingException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author Crazy.X
 * @version 1.0
 */
@Slf4j
public class BeanMapping {

    /**
     * 映射一个新的实例
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return R
     */
    public static <T, R> R toBean(@NotNull T source, @NotNull Class<R> target) {
        return toBean(source, target, null);
    }

    /**
     * 映射一个新的实例
     * @param source     数据源
     * @param target     目标
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @param biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @return R
     */
    public static <T, R> R toBean(@NotNull T source, @NotNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        R instance = getInstanceObject(source, target);
        if (biConsumer != null) {
            biConsumer.accept(source, instance);
        }
        return instance;
    }

    /**
     * 映射一个新的List
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return List<R>
     */
    public static <T, R> List<R> toList(@NotNull Collection<T> source, @NotNull Class<R> target) {
        return toList(source, target, null);
    }

    /**
     * 映射一个新的List
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return List<R>
     */
    public static <T, R> List<R> toList(@NotNull Collection<T> source, @NotNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        List<R> targetCollection = new ArrayList<>();
        getInstanceCollection(targetCollection, source, target, biConsumer);
        return targetCollection;
    }

    /**
     * 映射一个新的Set
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return Set<R>
     */
    public static <T, R> Set<R> toSet(@NotNull Collection<T> source, @NotNull Class<R> target) {
        return toSet(source, target, null);
    }

    /**
     * 映射一个新的Set
     * @param source     数据源
     * @param target     目标
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @return Set<R>
     */
    public static <T, R> Set<R> toSet(@NotNull Collection<T> source, @NotNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        Set<R> targetCollection = new HashSet<>();
        getInstanceCollection(targetCollection, source, target, biConsumer);
        return targetCollection;
    }

    private static <T, R> R getInstanceObject(T source, Class<R> target) {
        R instance = null;
        try {
            instance = target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Class<?> cla = source.getClass();
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field field : declaredFields) {
            Field declaredField;
            try {
                assert instance != null;
                declaredField = instance.getClass().getDeclaredField(field.getName());
                if (declaredField != null && field.getType().equals(declaredField.getType())) {
                    field.setAccessible(true);
                    declaredField.setAccessible(true);
                    try {
                        Object val = field.get(source);
                        declaredField.set(instance, val);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchFieldException e) {
                log.error("对象映射出错, 原对象类型: {}, 目标对象类型: {}", source, target);
                throw new BeanMappingException();
            }

        }
        return instance;
    }

    private static <T, R> void getInstanceCollection(Collection<R> targetCollection, Collection<T> source, Class<R> target, BiConsumer<T, R> biConsumer) {
        for (T t : source) {
            R instance = getInstanceObject(t, target);
            if (biConsumer != null) {
                biConsumer.accept(t, instance);
            }
            targetCollection.add(instance);
        }
    }
}
