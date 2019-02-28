package com.mime.farm.util.jdbc;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 默认的类型实现
 * 
 * @author PengChan
 * @param <T>
 * 
 */
public class DefaultMapper<T> implements ResultMapper<T> {

    @SuppressWarnings({ "deprecation", "hiding" })
	public <T> T getInstance(Class<T> c) throws InstantiationException, IllegalAccessException {
        return c.newInstance();
    }

    /**
     * 转化的核心方法
     */
    @SuppressWarnings("hiding")
    @Override
    public <T> T convert(Map<String, Object> resource, Class<T> c) {
        T t = null;
        try {
            t = getInstance(c);
            // 获取所有的成员变量
            Field[] declaredFields = t.getClass().getDeclaredFields();
            // 循环遍历设置值
            for (Field f : declaredFields) {
                setValute(f, t, resource.get(f.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 设置类型的值
     * 
     * @param fieldType
     * @param value
     */
    @SuppressWarnings({ "hiding", "rawtypes" })
    private <T> void setValute(Field field, T t, Object value) {
        try {
            Class fieldType = field.getType();
            field.setAccessible(true);
            if (fieldType.equals(int.class)) {// 如果是整形
                field.setInt(t, (int) value);
            } else if (fieldType.equals(float.class)) {
                field.setFloat(t, (float) value);
            } else if (fieldType.equals(boolean.class)) {
                field.setBoolean(t, (boolean) value);
            } else if (fieldType.equals(double.class)) {
                field.setDouble(t, (double) value);
            } else if (fieldType.equals(byte.class)) {
                field.setByte(t, (byte) value);
            } else if (fieldType.equals(short.class)) {
                field.setShort(t, (short) value);
            } else if (fieldType.equals(long.class)) {
                field.setLong(t, (long) value);
            } else if (fieldType.equals(char.class)) {
                field.setChar(t, (char) value);
            } else {
                field.set(t, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}