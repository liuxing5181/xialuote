/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <数据计算工具类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class MathUtils {
    private static final float FLOAT_SMALL_ENOUGH_NUM = 1.0E-7F;

    private static final double DOUBLE_SMALL_ENOUGH_NUM = 1.0E-7D;

    private MathUtils() {
    }

    public static int parseInt(String str, int defInt) {
        try {
            return Integer.parseInt(str);
        } catch (Exception var3) {
            return defInt;
        }
    }

    public static int parseInt(Integer integer, int defInt) {
        return null == integer ? defInt : integer;
    }

    public static int parseInt(String str, int radix, int defInt) {
        try {
            return Integer.parseInt(str, radix);
        } catch (Exception var4) {
            return defInt;
        }
    }

    public static long parseLong(String str, long defLong) {
        try {
            return Long.parseLong(str);
        } catch (Exception var4) {
            return defLong;
        }
    }

    public static long parseLong(String str, int radix, long defLong) {
        try {
            return Long.parseLong(str, radix);
        } catch (Exception var5) {
            return defLong;
        }
    }

    public static Boolean parseBoolean(String str, Boolean defBool) {
        try {
            return Boolean.valueOf(str);
        } catch (Exception var3) {
            return defBool;
        }
    }

    public static float parseFloat(String str, Float defFloat) {
        try {
            return Float.parseFloat(str);
        } catch (Exception var3) {
            return defFloat;
        }
    }

    public static double parseDouble(String str, double defDouble) {
        try {
            return Double.parseDouble(str);
        } catch (Exception var4) {
            return defDouble;
        }
    }

    public static byte parseByte(String str, byte defByte) {
        try {
            return Byte.parseByte(str);
        } catch (Exception var3) {
            return defByte;
        }
    }

    public static short parseShort(String str, short defShort) {
        try {
            return Short.parseShort(str);
        } catch (Exception var3) {
            return defShort;
        }
    }

    public static byte[] xor(String prevStr, String lateStr) {
        if (!StringUtils.isBlank(prevStr) && !StringUtils.isBlank(lateStr) && prevStr.length() == lateStr.length()) {
            char[] prevArray = prevStr.toCharArray();
            char[] lateArray = lateStr.toCharArray();
            byte[] xoredArray = new byte[prevArray.length];
            int length = prevStr.length();

            for (int i = 0; i < length; ++i) {
                xoredArray[i] = (byte) (prevArray[i] ^ lateArray[i]);
            }

            return xoredArray;
        } else {
            return new byte[0];
        }
    }

    public static boolean isEqual(float f1, float f2) {
        return Math.abs(f1 - f2) < FLOAT_SMALL_ENOUGH_NUM;
    }

    public static boolean isEqual(double f1, double f2) {
        return Math.abs(f1 - f2) < DOUBLE_SMALL_ENOUGH_NUM;
    }

    public static boolean biggerOrEqual(float f1, float f2) {
        return isEqual(f1, f2) || f1 > f2;
    }

    public static boolean biggerOrEqual(double f1, double f2) {
        return isEqual(f1, f2) || f1 > f2;
    }

    public static int getMaxCommonDivisor(int x, int y) {
        int xLocal = x;

        int yLocal;
        int temp;
        for (yLocal = y; xLocal % yLocal != 0; yLocal = temp) {
            temp = xLocal % yLocal;
            xLocal = yLocal;
        }

        return yLocal;
    }

    public static int getMinCommonMultiple(int[] nums) {
        int multiple = 1;
        if (nums != null && nums.length >= 2) {
            int x = nums[0];
            int y = nums[1];
            multiple = getMinCommonMultiple(x, y);

            for (int i = 2; i < nums.length; ++i) {
                multiple = getMinCommonMultiple(multiple, nums[i]);
            }
        }

        return multiple;
    }

    public static int getMinCommonMultiple(int x, int y) {
        return x * y / getMaxCommonDivisor(x, y);
    }

    public static boolean isNumber(String str) {
        if (!StringUtils.isEmpty(str)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
        } else {
            return false;
        }
    }

    public static float constrain(float minValue, float maxValue, float value) {
        return Math.max(minValue, Math.min(maxValue, value));
    }

    public static int compare(long x, long y) {
        return x < y ? -1 : (x == y ? 0 : 1);
    }

    public static int getMaxNumber(int minValue, int maxValue) {
        return Math.max(minValue, maxValue);
    }
}
