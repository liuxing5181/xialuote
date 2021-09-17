/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import android.text.TextUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <反射工具类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public final class ReflectionUtils {
    private static final String TAG = "ReflectionUtils";

    private ReflectionUtils() {
    }

    /**
     * 启用或禁用安全检查
     *
     * @param object 对象名称
     * @param flag   设置值为true表示反射对象应该在使用时抑制Java语言访问检查，设置值为false表示反射对象应强制执行Java语言访问检查。
     */
    public static void setAccessible(AccessibleObject object, boolean flag) {
        if (null != object) {
            object.setAccessible(flag);
        }
    }

    /**
     * 获取方法
     *
     * @param className      类名称
     * @param methodName     方法名称
     * @param parameterTypes 按照声明顺序标识该方法的形参类型
     * @return 方法
     */
    public static Method getMethod(String className, String methodName, Class<?>... parameterTypes) {
        if (TextUtils.isEmpty(className)) {
            Logger.w(TAG, "getMethod param className is null");
            return null;
        }
        if (TextUtils.isEmpty(methodName)) {
            Logger.w(TAG, "getMethod param methodName is null");
            return null;
        }

        Method method = null;
        try {
            Class<?> c = Class.forName(className);
            method = c.getMethod(methodName, parameterTypes);
        } catch (ClassNotFoundException e1) {
            Logger.e(TAG, "getMethod:", e1);
        } catch (NoSuchMethodException e2) {
            Logger.e(TAG, "getMethod:", e2);
        } catch (Exception e3) {
            Logger.e(TAG, "getMethod:", e3);
        }
        return method;
    }

    /**
     * 获取方法
     *
     * @param clz            类
     * @param methodName     函数名称
     * @param parameterTypes 按照声明顺序标识该方法的形参类型
     * @return 方法
     */
    public static Method getMethod(Class<?> clz, String methodName, Class<?>... parameterTypes) {
        if (clz == null) {
            Logger.w(TAG, "getMethod param clz is null");
            return null;
        }
        if (TextUtils.isEmpty(methodName)) {
            Logger.w(TAG, "getMethod param methodName is null");
            return null;
        }

        Method method = null;
        try {
            method = clz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            Logger.e(TAG, "getMethod:", e1);
        } catch (Exception e2) {
            Logger.e(TAG, "getMethod:", e2);
        }
        return method;
    }

    /**
     * 获取此Class对象所表示的类或接口的指定已声明方法
     *
     * @param clazz          类
     * @param methodName     函数名称
     * @param parameterTypes 形参类型
     * @return 返回一个Method对象，它反映此Class对象所表示的类或接口的指定已声明方法
     */
    public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        if (clazz != null && !TextUtils.isEmpty(methodName)) {
            return getDeclaredMethodImp(clazz, methodName, parameterTypes);
        } else {
            Logger.w(TAG, "getDeclaredMethod param clazz or methodname can not be null!");
            return null;
        }
    }

    private static Method getDeclaredMethodImp(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            Logger.e(TAG, methodName, e1);
        } catch (Exception e2) {
            Logger.e(TAG, "getDeclaredMethod", e2);
        }
        return method;
    }

    /**
     * 获取此Class对象所表示的类或接口的指定已声明方法
     *
     * @param className      类名称
     * @param methodName     函数名称
     * @param parameterTypes 形参类型
     * @return 返回一个Method对象，它反映此Class对象所表示的类或接口的指定已声明方法
     */
    public static Method getDeclaredMethod(String className, String methodName, Class<?>... parameterTypes) {
        if (!TextUtils.isEmpty(className) && !TextUtils.isEmpty(methodName)) {
            Method method = null;
            try {
                Class<?> c = Class.forName(className);
                method = getDeclaredMethodImp(c, methodName, parameterTypes);
            } catch (ClassNotFoundException e1) {
                Logger.e(TAG, className, e1);
            }
            return method;
        } else {
            Logger.w(TAG, "getDeclaredMethod param className or methodname can not be null!");
            return null;
        }
    }

    private static Field getDeclaredFieldImp(Class<?> clazz, String fieldName) {
        Field field = null;

        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e1) {
            Logger.e(TAG, fieldName, e1);
        } catch (Exception e2) {
            Logger.e(TAG, "getDeclaredField", e2);
        }

        return field;
    }

    /**
     * 此Class对象所表示的类或接口的指定已声明字段
     *
     * @param clazz     类
     * @param fieldName 字段名称
     * @return 字段
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName) {
        if (clazz != null && !TextUtils.isEmpty(fieldName)) {
            return getDeclaredFieldImp(clazz, fieldName);
        } else {
            Logger.w(TAG, "getDeclaredField param klass or fieldName can not be null!");
            return null;
        }
    }

    /**
     * 此Class对象所表示的类或接口的指定已声明字段
     *
     * @param className 类名称
     * @param fieldName 字段名称
     * @return 字段
     */
    public static Field getDeclaredField(String className, String fieldName) {
        if (!TextUtils.isEmpty(className) && !TextUtils.isEmpty(fieldName)) {
            try {
                Class<?> c = Class.forName(className);
                return getDeclaredFieldImp(c, fieldName);
            } catch (ClassNotFoundException e1) {
                Logger.e(TAG, className, e1);
                return null;
            }
        } else {
            Logger.w(TAG, "getDeclaredField param className or fieldName can not be null!");
            return null;
        }
    }

    /**
     * 此Class对象所表示的类或接口的指定已声明字段
     *
     * @param clz       类子
     * @param fieldName 字段名称
     * @param instance  实例
     * @param clazz     泛型类
     * @return 字段
     */
    public static <T> T getFieldValueIgnoreError(Class<?> clz, String fieldName, Object instance, Class<T> clazz) {
        if (clz != null && !TextUtils.isEmpty(fieldName) && clazz != null) {
            Object result = null;

            try {
                Field field = clz.getField(fieldName);
                Object obj = field.get(instance);
                if (clazz.isInstance(obj)) {
                    result = obj;
                }
            } catch (NoSuchFieldException e1) {
                Logger.d(TAG, fieldName);
            } catch (IllegalArgumentException e2) {
                Logger.d(TAG, "IllegalArgumentException");
            } catch (IllegalAccessException e3) {
                Logger.d(TAG, "IllegalAccessException");
            } catch (Exception var10) {
                Logger.d(TAG, "getFieldValue");
            }

            return (T) result;
        } else {
            Logger.w(TAG, "getFieldValueIgnoreError getFieldValue param className or fieldName can not be null!");
            return null;
        }
    }

    /**
     * 此Class对象所表示的类或接口的指定已声明字段
     *
     * @param clz       类子
     * @param fieldName 字段名称
     * @param instance  实例
     * @param clazz     泛型类
     * @return 字段
     */
    public static <T> T getFieldValue(Class<?> clz, String fieldName, Object instance, Class<T> clazz) {
        if (clz != null && !TextUtils.isEmpty(fieldName) && clazz != null) {
            Object result = null;

            try {
                Field field = clz.getField(fieldName);
                Object obj = field.get(instance);
                if (clazz.isInstance(obj)) {
                    result = obj;
                }
            } catch (NoSuchFieldException e1) {
                Logger.e(TAG, fieldName, e1);
            } catch (IllegalArgumentException e2) {
                Logger.e(TAG, "IllegalArgumentException", e2);
            } catch (IllegalAccessException e3) {
                Logger.e(TAG, "IllegalAccessException", e3);
            } catch (Exception e4) {
                Logger.e(TAG, "getFieldValue", e4);
            }

            return (T) result;
        } else {
            Logger.w(TAG, "getFieldValue param clz or fieldName can not be null!");
            return null;
        }
    }

    /**
     * 代理
     *
     * @param method   函数
     * @param receiver 接收的对象
     * @param args     参数
     * @return 代理类
     */
    public static Object invoke(Method method, Object receiver, Object... args) {
        if (null == method) {
            Logger.w(TAG, "invoke param method can not be null!");
            return null;
        } else {
            try {
                return method.invoke(receiver, args);
            } catch (InvocationTargetException e1) {
                Logger.e(TAG, method + " invoke ", e1.getTargetException());
            } catch (Exception e2) {
                Logger.e(TAG, method + " invoke ", e2);
            }
            return null;
        }
    }

    /**
     * 获得类实例
     *
     * @param clazz 类
     * @return 类实例
     */
    public static Object newInstance(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e1) {
                Logger.e(TAG, "newInstance InstantiationException ", e1);
            } catch (IllegalAccessException e2) {
                Logger.e(TAG, "newInstance IllegalAccessException ", e2);
            }

            return null;
        }
    }

    /**
     * 获得类的实例
     *
     * @param className 类名称
     * @param baseClz   类的基类
     * @param <T>       泛型
     * @return 类的实例
     */
    public static <T> T newInstance(String className, Class<T> baseClz) {
        if (!TextUtils.isEmpty(className) && baseClz != null) {
            try {
                Class clz = Class.forName(className);
                if (isSubClassOf(clz, baseClz)) {
                    return (T) clz.newInstance();
                }
            } catch (ClassNotFoundException e1) {
                Logger.w(TAG, "newInstance ClassNotFoundException");
            } catch (InstantiationException e2) {
                Logger.w(TAG, "newInstance InstantiationException");
            } catch (IllegalAccessException e3) {
                Logger.w(TAG, "newInstance IllegalAccessException");
            }
        }

        return null;
    }

    /**
     * 是否是一个类的子类对象
     *
     * @param childClz  子类
     * @param parentClz 父类
     * @return true: 是子类，false: 不是子类
     */
    public static boolean isSubClassOf(Class childClz, Class parentClz) {
        return childClz != null && parentClz != null ? parentClz.isAssignableFrom(childClz) : false;
    }

    /**
     * 代理
     *
     * @param className  类名称
     * @param methodName 函数名
     * @param paramTypes 形参类型
     * @param params     参数
     * @return 代理类
     */
    public static Object invoke(String className, String methodName, Class<?>[] paramTypes, Object... params) {
        if (className != null && !TextUtils.isEmpty(methodName)) {
            try {
                Class<?> cls = Class.forName(className);
                Method method = cls.getMethod(methodName, paramTypes);
                return invoke(method, cls.newInstance(), params);
            } catch (ClassNotFoundException e1) {
                Logger.e(TAG, "ClassNotFoundException", e1);
            } catch (NoSuchMethodException e2) {
                Logger.e(TAG, "NoSuchMethodException", e2);
            } catch (InstantiationException e3) {
                Logger.e(TAG, "InstantiationException", e3);
            } catch (IllegalAccessException e4) {
                Logger.e(TAG, "IllegalAccessException", e4);
            }

            return null;
        } else {
            Logger.w(TAG, "invoke param className or methodName can not be null!");
            return null;
        }
    }

    /**
     * 代理
     *
     * @param clazz      类
     * @param methodName 函数名
     * @param paramTypes 形参类型
     * @param params     参数
     * @return 代理类
     */
    public static Object invoke(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object... params) {
        if (clazz != null && !TextUtils.isEmpty(methodName)) {
            try {
                Method method = clazz.getMethod(methodName, paramTypes);
                return invoke(method, clazz.newInstance(), params);
            } catch (NoSuchMethodException e1) {
                Logger.e(TAG, "NoSuchMethodException", e1);
            } catch (InstantiationException e2) {
                Logger.e(TAG, "InstantiationException", e2);
            } catch (IllegalAccessException e3) {
                Logger.e(TAG, "IllegalAccessException", e3);
            }

            return null;
        } else {
            Logger.w(TAG, "invoke param clazz or methodName can not be null!");
            return null;
        }
    }

    /**
     * 静态
     *
     * @param clazz      类
     * @param methodName 函数名
     * @param paramTypes 形参类型
     * @param params     参数
     * @return 代理类
     */
    public static Object invokeStatic(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object... params) {
        if (clazz != null && !TextUtils.isEmpty(methodName)) {
            try {
                Method method = clazz.getMethod(methodName, paramTypes);
                return method.invoke((Object) null, params);
            } catch (NoSuchMethodException e1) {
                Logger.e(TAG, "NoSuchMethodException", e1);
            } catch (IllegalAccessException e2) {
                Logger.e(TAG, "IllegalAccessException", e2);
            } catch (InvocationTargetException e3) {
                Logger.e(TAG, "InvocationTargetException", e3.getTargetException());
            }

            return null;
        } else {
            Logger.w(TAG, "invoke param className or methodName can not be null!");
            return null;
        }
    }

    /**
     * 获取类
     *
     * @param className 类名
     * @return 类
     */
    public static Class<?> getClass(String className) {
        return getClass(className, false);
    }

    private static Class<?> getClass(String className, boolean isSilent) {
        if (null == className) {
            return null;
        } else {
            Class clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e1) {
                if (!isSilent) {
                    Logger.e(TAG, "ClassNotFoundException：", e1);
                }
            } catch (LinkageError e2) {
                if (!isSilent) {
                    Logger.e(TAG, "an error occurs during linkage");
                }
            }
            return clazz;
        }
    }

    /**
     * 给属性设置新值
     *
     * @param field  属性
     * @param object 类对象
     * @param value  值
     */
    public static void setField(Field field, Object object, Object value) {
        if (null != field) {
            if (!Modifier.isStatic(field.getModifiers()) && null == object) {
                Logger.w(TAG, "field is not null and not static, but object is null");
            } else {
                try {
                    field.set(object, value);
                } catch (IllegalAccessException e1) {
                    Logger.e(TAG, "IllegalAccessException", e1);
                } catch (IllegalArgumentException e2) {
                    Logger.e(TAG, "IllegalArgumentException", e2);
                }

            }
        }
    }

    /**
     * 返回直接继承的父类（包含泛型参数）
     *
     * @param clz 类
     * @return 直接继承的父类（包含泛型参数）
     */
    public static String getTypeArgument(Class clz) {
        String arg = null;
        if (clz != null) {
            Type superType = clz.getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                arg = ((ParameterizedType) superType).getActualTypeArguments()[0].toString();
            }
        }

        return arg;
    }

    /**
     * 获取默认值
     *
     * @param type 类型
     * @return 默认值
     */
    public static Object getDefaultValue(Class<?> type) {
        return null == type ? null : getDefaultValue(type.getName());
    }

    public static Object getDefaultValue(String fullyQualifiedTypeName) {
        if (fullyQualifiedTypeName == null) {
            return "";
        } else if (fullyQualifiedTypeName.equals(Byte.TYPE.getName())) {
            return 0;
        } else if (fullyQualifiedTypeName.equals(Integer.TYPE.getName())) {
            return 0;
        } else if (fullyQualifiedTypeName.equals(Short.TYPE.getName())) {
            return Short.valueOf((short) 0);
        } else if (fullyQualifiedTypeName.equals(Long.TYPE.getName())) {
            return 0L;
        } else if (fullyQualifiedTypeName.equals(Float.TYPE.getName())) {
            return 0.0F;
        } else if (fullyQualifiedTypeName.equals(Double.TYPE.getName())) {
            return 0.0D;
        } else if (fullyQualifiedTypeName.equals(Boolean.TYPE.getName())) {
            return false;
        } else {
            return fullyQualifiedTypeName.equals(Character.TYPE.getName()) ? ' ' : null;
        }
    }
}
