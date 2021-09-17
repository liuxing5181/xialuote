
package com.yuji.xlt.ability.utils;

import java.math.BigDecimal;

/**
 * 提供精确的浮点数运算(包括加、减、乘、除、四舍五入)工具类
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public final class BigDecimalUtils {
    /**
     * 除法运算默认精度
     */
    private static final int DEFAULT_DIV_SCALE = 6;

    private BigDecimalUtils() {
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后默认精度位(6位)，以后的数字四舍五入,舍入模式采用ROUND_HALF_UP
     *
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     */
    public static float divide(float value1, float value2) {
        return divide(value1, value2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_UP
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static float divide(float value1, float value2, int scale) {
        return divide(value1, value2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     *
     * @param value1    被除数
     * @param value2    除数
     * @param scale     表示需要精确到小数点以后几位
     * @param roundMode 表示用户指定的舍入模式
     * @return 两个参数的商
     */
    public static float divide(float value1, float value2, int scale, int roundMode) {
        if (MathUtils.isEqual(value2, 0)) {
            // / by zero
            return 0;
        }
        int newScale = scale;
        if (newScale < 0) {
            // The scale must be a positive integer or zero
            newScale = DEFAULT_DIV_SCALE;
        }
        int newRoundMode = roundMode;
        if (newRoundMode < BigDecimal.ROUND_UP || newRoundMode > BigDecimal.ROUND_UNNECESSARY) {
            // Invalid round mode
            newRoundMode = BigDecimal.ROUND_UP;
        }
        BigDecimal bd1 = new BigDecimal(value1);
        BigDecimal bd2 = new BigDecimal(value2);
        return bd1.divide(bd2, newScale, newRoundMode).floatValue();
    }

    /**
     * 提供（相对）精确的除法运算，除数位常量2
     *
     * @param value1 被除数
     * @return 被除数除以2的商
     */
    public static float divideHalf(float value1) {
        return divide(value1, 2.0f);
    }

    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_UP
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static float round(float value, int scale) {
        return round(value, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param value     需要四舍五入的数字
     * @param scale     小数点后保留几位
     * @param roundMode 指定的舍入模式
     * @return 四舍五入后的结果
     */
    public static float round(float value, int scale, int roundMode) {
        int newScale = scale;
        if (newScale < 0) {
            // The scale must be a positive integer or zero
            newScale = DEFAULT_DIV_SCALE;
        }
        int newRoundMode = roundMode;
        if (newRoundMode < BigDecimal.ROUND_UP || newRoundMode > BigDecimal.ROUND_UNNECESSARY) {
            // Invalid round mode
            newRoundMode = BigDecimal.ROUND_UP;
        }
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(newScale, newRoundMode).floatValue();
    }
}
