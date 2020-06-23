package org.crazy.utils;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import org.crazy.common.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author Crazy.X
 * @version 2.1
 */
public class BeanMapping {

    /**
     * 映射一个新的实例
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型的实例
     */
    public static <T, R> R toBean(@NonNull T source, @NonNull Class<R> target) {
        return toBean(source, target, null);
    }

    /**
     * 映射一个新的实例
     * @param source     数据源
     * @param target     目标
     * @param biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型的实例
     */
    public static <T, R> R toBean(@NonNull T source, @NonNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        R instance = getInstanceObject(source, target);
        if (instance != null && biConsumer != null) {
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
     * @return 返回R类型的实例List集合
     */
    public static <T, R> List<R> toList(@NonNull Collection<T> source, @NonNull Class<R> target) {
        return toList(source, target, null);
    }

    /**
     * 映射一个新的List
     * @param source     数据源
     * @param target     目标
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型的实例List集合
     */
    public static <T, R> List<R> toList(@NonNull Collection<T> source, @NonNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        List<R> targetCollection = new ArrayList<>();
        getInstanceCollection(targetCollection, source, target, biConsumer);
        return targetCollection;
    }

    /**
     * 映射一个新的范围List
     * @param source 数据源
     * @param target 目标
     * @param skip   跳过
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型范围的实例List集合
     */
    public static <T, R> List<R> toListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip) {
        return toListRange(source, target, skip, source.size());
    }


    /**
     * 映射一个新的范围List
     * @param source     数据源
     * @param target     目标
     * @param skip       跳过
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型范围的实例List集合
     */
    public static <T, R> List<R> toListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip, BiConsumer<T, R> biConsumer) {
        return toListRange(source, target, skip, source.size(), biConsumer);
    }

    /**
     * 映射一个新的范围List
     * @param source 数据源
     * @param target 目标
     * @param skip   跳过
     * @param limit  截止
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型范围的实例List集合
     */
    public static <T, R> List<R> toListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip, @NonNull int limit) {
        return toListRange(source, target, skip, limit, null);
    }

    /**
     * 映射一个新的范围List
     * @param source     数据源
     * @param target     目标
     * @param skip       跳过
     * @param limit      截止(包含)
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型范围的实例List集合
     */
    public static <T, R> List<R> toListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip, @NonNull int limit, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        if (skip > source.size() || limit < skip || limit < 0 || skip == limit) {
            return Collections.emptyList();
        }
        List<R> targetCollection = new ArrayList<>();
        int end = limit > source.size() ? source.size() : limit;
        getInstanceCollection(targetCollection, source.subList(skip < 0 ? 0 : skip, end), target, biConsumer);
        toList(source, target, null);
        return targetCollection;
    }

    /**
     * 映射一个新的Set
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型的实例Set集合
     */
    public static <T, R> Set<R> toSet(@NonNull Collection<T> source, @NonNull Class<R> target) {
        return toSet(source, target, null);
    }

    /**
     * 映射一个新的Set
     * @param source     数据源
     * @param target     目标
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型的实例Set集合
     */
    public static <T, R> Set<R> toSet(@NonNull Collection<T> source, @NonNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        Set<R> targetCollection = new HashSet<>();
        getInstanceCollection(targetCollection, source, target, biConsumer);
        return targetCollection;
    }

    /**
     * 映射一个新的JSON实例
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回一个新的R类型的Json字符串
     */
    public static <T, R> String toJsonBean(@NonNull T source, @NonNull Class<R> target) {
        return toJsonBean(source, target, null);
    }

    /**
     * 映射一个新的JSON字符串实例
     * @param source     数据源
     * @param target     目标
     * @param biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型的Json字符串
     */
    public static <T, R> String toJsonBean(@NonNull T source, @NonNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        return JSON.toJSONString(toBean(source, target, biConsumer));
    }

    /**
     * 映射一个新的Json字符串List
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型的实例List集合的Json字符串
     */
    public static <T, R> String toJsonList(@NonNull Collection<T> source, @NonNull Class<R> target) {
        return toJsonList(source, target, null);
    }

    /**
     * 映射一个新的Json字符串List
     * @param source     数据源
     * @param target     目标
     * @param biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型的实例List集合的Json字符串
     */
    public static <T, R> String toJsonList(@NonNull Collection<T> source, @NonNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        List<R> targetCollection = new ArrayList<>();
        getInstanceCollection(targetCollection, source, target, biConsumer);
        return JSON.toJSONString(targetCollection);
    }

    /**
     * 映射一个新的范围JsonList
     * @param source 数据源
     * @param target 目标
     * @param skip   跳过
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型范围的实例JsonList集合
     */
    public static <T, R> String toJsonListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip) {
        return toJsonListRange(source, target, skip, source.size());
    }


    /**
     * 映射一个新的范围JsonList
     * @param source     数据源
     * @param target     目标
     * @param skip       跳过
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型范围的实例JsonList集合
     */
    public static <T, R> String toJsonListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip, BiConsumer<T, R> biConsumer) {
        return toJsonListRange(source, target, skip, source.size(), biConsumer);
    }

    /**
     * 映射一个新的范围JsonList
     * @param source 数据源
     * @param target 目标
     * @param skip   跳过
     * @param limit  截止
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型范围的实例JsonList集合
     */
    public static <T, R> String toJsonListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip, @NonNull int limit) {
        return toJsonListRange(source, target, skip, limit, null);
    }

    /**
     * 映射一个新的范围JsonList
     * @param source     数据源
     * @param target     目标
     * @param skip       跳过
     * @param limit      截止(包含)
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型范围的实例JsonList集合
     */
    public static <T, R> String toJsonListRange(@NonNull List<T> source, @NonNull Class<R> target, @NonNull int skip, @NonNull int limit, BiConsumer<T, R> biConsumer) {
        return JSON.toJSONString(toListRange(source, target, skip, limit, biConsumer));
    }

    /**
     * 映射一个新的Json字符串Set
     * @param source 数据源
     * @param target 目标
     * @param <T>    数据源类型
     * @param <R>    目标类型
     * @return 返回R类型的实例Set集合的Json字符串
     */
    public static <T, R> String toJsonSet(@NonNull Collection<T> source, @NonNull Class<R> target) {
        return toJsonSet(source, target, null);
    }

    /**
     * 映射一个新的Json字符串Set
     * @param source     数据源
     * @param target     目标
     * @param biConsumer biConsumer 数据源字段与目标字段不同,可通过手动映射
     * @param <T>        数据源类型
     * @param <R>        目标类型
     * @return 返回R类型的实例Set集合的Json字符串
     */
    public static <T, R> String toJsonSet(@NonNull Collection<T> source, @NonNull Class<R> target, BiConsumer<T, R> biConsumer) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "target must not be null");
        Set<R> targetCollection = new HashSet<>();
        getInstanceCollection(targetCollection, source, target, biConsumer);
        return JSON.toJSONString(targetCollection);
    }

    private static <T, R> R getInstanceObject(T source, Class<R> target) {
        R instance;
        try {
            instance = target.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("目标类:" + target.getName() + "不能被实例化");
            return null;
        } catch (NoSuchMethodException | InvocationTargetException e) {
            System.err.println("目标类:" + target.getName() + "缺少默认构造方法");
            e.printStackTrace();
            return null;
        }
        Set<Field> declaredFields = new HashSet<>();
        getSourceFields(source.getClass(), declaredFields);

        for (Field field : declaredFields) {
            HashMap<String, Field> fieldMap = new HashMap<>();

            getInstanceFields(instance.getClass(), fieldMap);
            if (fieldMap.containsKey(field.getName())) {
                Field instanceField = fieldMap.get(field.getName());
                field.setAccessible(true);
                instanceField.setAccessible(true);
                try {
                    Object val = field.get(source);
                    instanceField.set(instance, val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    private static <T> void getSourceFields(Class<T> sourceCla, Set<Field> declaredFields) {
        boolean serialize = false;
        if (!Object.class.equals(sourceCla.getSuperclass())) {
            getSourceFields(sourceCla.getSuperclass(), declaredFields);
        }
        Class<?>[] interfaces = sourceCla.getInterfaces();
        for (Class<?> inter : interfaces) {
            if (Serializable.class.equals(inter)) {
                serialize = true;
                break;
            }
        }
        Field[] fields = sourceCla.getDeclaredFields();
        // Static property does not map
        for (Field field : fields) {
            if (serialize) {
                if (!Modifier.isStatic(field.getModifiers()) || !field.getName().contains("serial")) {
                    declaredFields.add(field);
                }
            } else {
                if (!Modifier.isStatic(field.getModifiers())) {
                    declaredFields.add(field);
                }
            }
        }
    }

    private static <T> void getInstanceFields(Class<?> instanceCla, HashMap<String, Field> declaredFields) {
        if (!Object.class.equals(instanceCla.getSuperclass())) {
            getInstanceFields(instanceCla.getSuperclass(), declaredFields);
        }
        Field[] fields = instanceCla.getDeclaredFields();
        for (Field field : fields) {
            declaredFields.put(field.getName(), field);
        }
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
