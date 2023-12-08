package com.example.demo.search.utils;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ReflectionUtils;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : songtc
 * @since : 2023/11/07 10:54
 */
public class MergeUtils {

    /**
     * 获取属性名
     *
     * @param func 类中提供型方法
     */
    private static <T, R> String getFieldName(SFunction<T, R> func) {
        return Introspector.decapitalize(PropertyNamer
                .methodToProperty(LambdaUtils.extract(func).getImplMethodName()));
    }

    /**
     * 获取类对象中对应的属性数组
     *
     * @param clazz 类对象
     * @param func  类对象的提供型方法
     */
    @SafeVarargs
    private static <T, R> Field[] getFields(T clazz, SFunction<T, R>... func) {
        List<Field> fields = new ArrayList<>();
        Arrays.stream(clazz.getClass().getDeclaredFields()).forEach(field -> {
            for (SFunction<T, R> fun : func) {
                if (field.getName().equals(getFieldName(fun))) fields.add(field);
            }
        });
        return fields.toArray(new Field[0]);
    }

    // ========= COVER =========

    /**
     * 往目标对象覆盖写入浅拷贝数据
     *
     * @param data 数据对象
     * @param dest 目标对象
     */
    public static void shallowCopyCoverFieldState(@NotNull final Object data, @NotNull final Object dest) {
        shallowCopyCoverFieldState(data, dest, data.getClass().getDeclaredFields());
    }

    /**
     * 往目标对象覆盖写入浅拷贝数据
     *
     * @param data 数据对象
     * @param dest 目标对象
     * @param func 类对象的提供型方法
     */
    @SafeVarargs
    public static <T, R> void shallowCopyCoverFieldState(@NotNull final T data, @NotNull final Object dest, SFunction<T, R>... func) {
        shallowCopyCoverFieldState(data, dest, getFields(data, func));
    }

    /**
     * 往目标对象覆盖写入浅拷贝数据
     *
     * @param data   数据对象
     * @param dest   目标对象
     * @param fields 需要写入浅拷贝的属性列表
     */
    private static <T> void shallowCopyCoverFieldState(@NotNull final T data, @NotNull final Object dest, Field[] fields) {
        for (Field f2 : fields) {
            ReflectionUtils.makeAccessible(f2);
            try {
                if (f2.get(data) == null) continue;
                for (Field f1 : dest.getClass().getDeclaredFields()) {
                    ReflectionUtils.makeAccessible(f1);
                    if (f2.getName().equals(f1.getName())) {
                        f1.set(dest, f2.get(data));
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Not allowed to access field :" + e);
            }
        }
    }

    // ========= CHOICE =========

    /**
     * 往目标对象选择写入浅拷贝数据
     *
     * @param data 数据对象
     * @param dest 目标对象
     */
    public static void shallowCopyChoiceFieldState(@NotNull final Object data, @NotNull final Object dest) {
        shallowCopyChoiceFieldState(data, dest, data.getClass().getDeclaredFields());
    }

    /**
     * 往目标对象选择写入浅拷贝数据
     *
     * @param data 数据对象
     * @param dest 目标对象
     * @param func 类对象的提供型方法
     */
    @SafeVarargs
    public static <T, R> void shallowCopyChoiceFieldState(@NotNull final T data, @NotNull final Object dest, SFunction<T, R>... func) {
        shallowCopyChoiceFieldState(data, dest, getFields(data, func));
    }

    /**
     * 往目标对象选择写入浅拷贝数据
     *
     * @param data   数据对象
     * @param dest   目标对象
     * @param fields 需要写入浅拷贝的属性列表
     */
    private static <T> void shallowCopyChoiceFieldState(@NotNull final T data, @NotNull final Object dest, Field[] fields) {
        for (Field f1 : dest.getClass().getDeclaredFields()) {
            ReflectionUtils.makeAccessible(f1);
            for (Field f2 : fields) {
                ReflectionUtils.makeAccessible(f2);
                try {
                    if (f1.get(dest) != null) break;
                    if (f2.get(data) != null && f2.getName().equals(f1.getName())) {
                        f1.set(dest, f2.get(data));
                        break;
                    }
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Not allowed to access field : " + e);
                }
            }
        }
    }

    /**
     * 时间合并工具类
     */
    public static class LocalDateTimeUtils {

        /**
         * 获取范围内的时间日期列表
         *
         * @param startDateTime 开始时间
         * @param endDateTime   结束时间
         * @param type          时间种类
         */
        public static List<LocalDateTime> getList(LocalDateTime startDateTime, LocalDateTime endDateTime, ChronoUnit type) {
            if (startDateTime.compareTo(endDateTime) >= 0) {
                // 时间倒叙
                return Stream.iterate(startDateTime, date -> date.minus(1, type))
                        .limit(type.between(endDateTime, startDateTime))
                        .collect(Collectors.toList());
            } else {
                // 时间正序
                return Stream.iterate(startDateTime, date -> date.plus(1, type))
                        .limit(type.between(startDateTime, endDateTime))
                        .collect(Collectors.toList());
            }
        }
    }
}
