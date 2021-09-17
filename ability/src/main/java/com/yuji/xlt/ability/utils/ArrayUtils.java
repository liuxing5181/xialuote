/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import android.text.TextUtils;
import android.util.SparseBooleanArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <数组工具类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ArrayUtils {
    private static final String TAG = "ArrayUtils";

    private ArrayUtils() {
    }

    public static boolean isEmpty(String[] str) {
        return null == str || 0 == str.length;
    }

    public static boolean isEmpty(Object[] array) {
        return null == array || 0 == array.length;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return null != collection && !collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }

    public static boolean isEmpty(long[] array) {
        return null == array || 0 == array.length;
    }

    public static boolean isEmpty(int[] array) {
        return null == array || 0 == array.length;
    }

    public static boolean isEmpty(byte[] array) {
        return null == array || 0 == array.length;
    }

    public static <T> List<T> getSubList(List<T> source, int start, int end) {
        return null != source && start >= 0 && end >= start && end <= source.size() ? source.subList(start, end) : null;
    }

    public static <T> T getListElement(List<T> source, int index) {
        return !isEmpty((Collection) source) && index >= 0 && index < source.size() ? source.get(index) : null;
    }

    public static String arrayToString(String[] sources, String separator) {
        if (sources == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            String[] var3 = sources;
            int var4 = sources.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String source = var3[var5];
                builder.append(source).append(separator);
            }

            String res = builder.toString();
            return StringUtils.isEmpty(res) ? "" : StringUtils.cutString(res, 0, res.length() - 1);
        }
    }

    public static String listToString(List<String> sources, String separator) {
        if (isEmpty((Collection) sources)) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder();
            Iterator var3 = sources.iterator();

            while (var3.hasNext()) {
                String source = (String) var3.next();
                builder.append(source).append(separator);
            }

            String res = builder.toString();
            return TextUtils.isEmpty(res) ? "" : StringUtils.cutString(res, 0, res.length() - 1);
        }
    }

    public static <T> List<T> objToList(Object object, Class<T> clazz) {
        if (clazz != null && object instanceof List) {
            List objects = (List) object;
            if (!isEmpty((Collection) objects)) {
                Object obj = objects.get(0);
                if (clazz.isInstance(obj)) {
                    try {
                        return (List) object;
                    } catch (ClassCastException var5) {
                        return null;
                    }
                }
            }
        }

        return null;
    }

    public static <T> ArrayList<T> objToArrayList(Object object, Class<T> clazz) {
        if (clazz != null && object instanceof ArrayList) {
            List objects = (ArrayList) object;
            if (!isEmpty((Collection) objects)) {
                Object obj = objects.get(0);
                if (clazz.isInstance(obj)) {
                    try {
                        return (ArrayList) object;
                    } catch (ClassCastException var5) {
                        return null;
                    }
                }
            }
        }

        return null;
    }

    public static boolean getValueFromSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int index,
                                                         boolean defaultValue) {
        return sparseBooleanArray != null && sparseBooleanArray.size() > index ? sparseBooleanArray.get(index)
                : defaultValue;
    }

    public static <T> T getArrayElement(T[] source, int index) {
        return !isEmpty(source) && index >= 0 && index < source.length ? source[index] : null;
    }

    public static float getFloatArrayElement(float[] source, int index) {
        return source != null && index >= 0 && index < source.length ? source[index] : 0.0F;
    }

    public static int getIntArrayElement(int[] source, int index) {
        return source != null && index >= 0 && index < source.length ? source[index] : 0;
    }

    public static void modifyFloatArrayValue(float[] source, int index, float value) {
        if (source != null && index >= 0 && index < source.length) {
            source[index] = value;
        }

    }

    public static void modifyIntArrayValue(int[] source, int index, int value) {
        if (source != null && index >= 0 && index < source.length) {
            source[index] = value;
        }

    }

    public static int getListSize(List list) {
        return isEmpty((Collection) list) ? 0 : list.size();
    }

    public static void arrayCopy(byte[] src, int srcPos, byte[] dest, int destPos, int length) {
        if (null != src && null != dest) {
            if (srcPos >= 0 && srcPos + length <= src.length) {
                if (destPos >= 0 && destPos + length <= dest.length) {
                    System.arraycopy(src, srcPos, dest, destPos, length);
                }
            }
        }
    }

    public static <T> void replaceAll(List<T> srcList, List<T> toAddedList) {
        if (null == srcList) {
            Logger.w(TAG, "replaceAll but srcList is null");
        } else {
            srcList.clear();
            if (!isEmpty((Collection) toAddedList)) {
                srcList.addAll(toAddedList);
            }

        }
    }

    @Nullable
    public static <T> List<T> getSubList(List<T> srcList, List<T> toRemovedList) {
        if (null == srcList) {
            Logger.w(TAG, "removeAll but srcList is null");
            return null;
        } else {
            List<T> res = new ArrayList(srcList.size());
            res.addAll(srcList);
            if (!isEmpty((Collection) toRemovedList)) {
                res.removeAll(toRemovedList);
            }

            return res;
        }
    }

    public static List<Integer> asList(int[] src) {
        if (src != null && src.length > 0) {
            List<Integer> list = new ArrayList();
            int[] var2 = src;
            int var3 = src.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                int i = var2[var4];
                list.add(i);
            }

            return list;
        } else {
            return null;
        }
    }

    public static void clearList(List list) {
        if (!isEmpty((Collection) list)) {
            list.clear();
        }

    }

    public static <T> boolean arrayEquals(List<T> list1, List<T> list2) {
        if (list1 == list2) {
            return true;
        } else if (list1 != null && list2 != null && list1.size() == list2.size()) {
            for (int i = 0; i < list1.size(); ++i) {
                if (!isObjectEqual(list1.get(i), list2.get(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private static boolean isObjectEqual(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        } else {
            return null != o1 && null != o2 ? o1.equals(o2) : false;
        }
    }

    public static <T> void add(List<T> list, T ele) {
        if (list != null && ele != null && !list.contains(ele)) {
            list.add(ele);
        }

    }

    @NonNull
    public static <T> List<T> getNonNullList(List<T> list) {
        if (isEmpty((Collection) list)) {
            return Collections.emptyList();
        } else {
            List<T> res = new ArrayList(list);
            List<T> toRemoved = new ArrayList(list.size());
            Iterator var3 = list.iterator();

            while (var3.hasNext()) {
                T t = (T) var3.next();
                if (null == t) {
                    toRemoved.add((T) (Object) null);
                }
            }

            res.removeAll(toRemoved);
            return res;
        }
    }
}
