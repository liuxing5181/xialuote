/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * <字符串工具类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class StringUtils {
    private static final String EMPTY_ARRAYS = "[]";

    private static final String TAG = "StringUtils";

    private static final String HEXSTRING = "0x";

    private static final String IP_REGEX =
            "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

    private static final int HEX_VALUE = 255;

    private static final String EMPTY = "";

    private static final int ARRAY_INDEX = 2;

    private StringUtils() {
    }

    public static boolean isEmpty(String value) {
        return null == value || 0 == value.length();
    }

    public static boolean isNotEmpty(String value) {
        return value != null && 0 != value.length();
    }

    public static boolean isBlank(String value) {
        return null == value || 0 == value.length() || EMPTY.equals(value.trim());
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static String trimNonBlankStr(String value, String defValue) {
        return isBlank(value) ? defValue : value.trim();
    }

    public static boolean isIPAddress(String strIp) {
        return null == strIp ? false : strIp.matches(IP_REGEX);
    }

    public static String trimNonNullStr(String value, String defValue) {
        return null == value ? defValue : value.trim();
    }

    public static String emptyIfBlank(String value) {
        return null == value ? EMPTY : value.trim();
    }

    public static String trimAndLowerCase(String value) {
        return str2LowerCase(emptyIfBlank(value));
    }

    public static String trimAndUpperCase(String value) {
        return str2UpperCase(emptyIfBlank(value));
    }

    public static String trimAndToString(Object object) {
        return null == object ? EMPTY : object.toString().trim();
    }

    public static boolean isNull(String string) {
        if (null != string) {
            string = string.trim();
            if (string.length() != 0) {
                return false;
            }
        }

        return true;
    }

    public static String pickNotEmptyString(String... strings) {
        if (strings != null && strings.length != 0) {
            String[] var1 = strings;
            int var2 = strings.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                String s = var1[var3];
                if (!isEmpty(s)) {
                    return s;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static String getImageOfSize(String url, String size) {
        if (url == null) {
            return url;
        } else {
            String[] parts = url.split(",");
            String sizeSeparator = "_" + size;
            if (parts.length == 1) {
                return url;
            } else {
                String[] var4 = parts;
                int var5 = parts.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    String part = var4[var6];
                    String[] urlParts = part.split("\\.");
                    if (urlParts.length < ARRAY_INDEX) {
                        return url;
                    }

                    String filename = urlParts[urlParts.length - ARRAY_INDEX];
                    if (filename.endsWith(sizeSeparator)) {
                        return part;
                    }
                }

                if (parts.length > 1) {
                    return parts[parts.length - 1];
                } else {
                    return url;
                }
            }
        }
    }

    public static int stringToInt(String str, int def) {
        if (isEmpty(str)) {
            return def;
        } else {
            int result;
            if (str.contains(HEXSTRING)) {
                String strTemp = cutString(str, str.indexOf(HEXSTRING) + HEXSTRING.length());
                result = MathUtils.parseInt(strTemp, 16, def);
            } else {
                result = MathUtils.parseInt(str, def);
            }
            return result;
        }
    }

    public static boolean contains(String str, String def) {
        if (isEmpty(str)) {
            return isEmpty(def);
        }
        if (isEmpty(def)) {
            return true;
        }

        return str.contains(def);
    }

    public static String retrieveNumberStr(String str, int def) {
        return isEmpty(str) ? String.valueOf(def) : String.valueOf(MathUtils.parseInt(str, def));
    }

    public static String cutString(String originalStr, int beginIndex) {
        if (isEmpty(originalStr)) {
            return originalStr;
        } else {
            try {
                return originalStr.substring(beginIndex);
            } catch (IndexOutOfBoundsException var3) {
                return originalStr;
            }
        }
    }

    public static String cutString(String originalStr, int beginIndex, int endIndex) {
        if (isEmpty(originalStr)) {
            return originalStr;
        } else {
            try {
                return originalStr.substring(beginIndex, endIndex);
            } catch (IndexOutOfBoundsException var4) {
                return originalStr;
            }
        }
    }

    public static String str2UpperCase(String source) {
        return TextUtils.isEmpty(source) ? source : source.toUpperCase(Locale.US);
    }

    public static String str2LowerCase(String source) {
        return TextUtils.isEmpty(source) ? source : source.toLowerCase(Locale.US);
    }

    public static String formatByUSLocale(String format, Object... args) {
        return format(Locale.US, format, args);
    }

    public static String formatForShow(String format, Object... args) {
        return format(Locale.getDefault(), format, args);
    }

    private static String format(Locale l, String format, Object... args) {
        if (TextUtils.isEmpty(format)) {
            return null;
        } else {
            String target;
            try {
                target = String.format(l, format, args);
            } catch (IllegalFormatException var5) {
                Logger.e(TAG, "IllegalFormatException happened.");
                target = null;
            }

            return target;
        }
    }

    public static String getIndexOfString(List<String> strs) {
        if (ArrayUtils.isEmpty(strs)) {
            return null;
        } else {
            List<String> res = new ArrayList();
            Iterator var2 = strs.iterator();

            while (var2.hasNext()) {
                String str = (String) var2.next();
                if (!TextUtils.isEmpty(str)) {
                    res.add(str);
                }
            }

            if (res.size() > 0 && res.size() <= ARRAY_INDEX) {
                return (String) res.get(res.size() - 1);
            } else if (res.size() > ARRAY_INDEX) {
                return (String) res.get(res.size() - ARRAY_INDEX);
            } else {
                return null;
            }
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(HEX_VALUE & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }

            sb.append(hex);
        }

        return sb.toString();
    }

    public static boolean isEqual(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equals(str2);
        }
    }

    public static boolean isEqualIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equalsIgnoreCase(str2);
        }
    }

    public static String substringBefore(String str, String separator) {
        if (!isEmpty(str) && separator != null) {
            if (separator.length() == 0) {
                return EMPTY;
            } else {
                int pos = str.indexOf(separator);
                return pos == -1 ? str : str.substring(0, pos);
            }
        } else {
            return str;
        }
    }

    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        } else if (separator == null) {
            return EMPTY;
        } else {
            int pos = str.indexOf(separator);
            return pos == -1 ? EMPTY : str.substring(pos + separator.length());
        }
    }

    public static String substringBetween(String str, String open, String close) {
        if (str != null && open != null && close != null) {
            int start = str.indexOf(open);
            if (start != -1) {
                int end = str.indexOf(close, start + open.length());
                if (end != -1) {
                    return str.substring(start + open.length(), end);
                }
            }

            return str;
        } else {
            return str;
        }
    }

    public static String getValueFromKeyValueStr(String keyValueStr, String spliter) {
        if (keyValueStr != null && spliter != null) {
            String[] keyValuePair = keyValueStr.split(spliter);
            return keyValuePair.length > 1 ? keyValuePair[1] : null;
        } else {
            return null;
        }
    }

    public static <S> String getArrayStrings(List<S> list) {
        return getArrayStrings(list, (ConvertHelper.IConverter) null);
    }

    public static <S> String getArrayStrings(List<S> list, @Nullable ConvertHelper.IConverter<S, String> iConverter) {
        if (null == iConverter) {
            iConverter = new ConvertHelper.IConverter<S, String>() {
                public String convert(S source) {
                    return null == source ? null : source.toString();
                }
            };
        }

        List<String> prints = ConvertHelper.convert(list, iConverter);
        if (!ArrayUtils.isEmpty(prints)) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            int pos = 0;

            for (Iterator var5 = prints.iterator(); var5.hasNext(); ++pos) {
                String print = (String) var5.next();
                if (pos != 0) {
                    sb.append(',');
                }

                sb.append(print);
            }

            return sb.append(']').toString();
        } else {
            return EMPTY_ARRAYS;
        }
    }

    public static String append(String... strings) {
        if (!ArrayUtils.isEmpty(strings)) {
            StringBuilder sb = new StringBuilder();
            int pos = 0;
            String[] var3 = strings;
            int var4 = strings.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String s = var3[var5];
                if (pos != 0) {
                    sb.append('_');
                }

                sb.append(s);
                ++pos;
            }

            return sb.toString();
        } else {
            return EMPTY;
        }
    }

    public static String getStringWithLimit(String originalStr, int limit) {
        if (isEmpty(originalStr)) {
            return originalStr;
        } else if (originalStr.length() <= limit) {
            return originalStr;
        } else {
            String replaceString = "...";
            int replaceLength = replaceString.length();
            return cutString(originalStr, 0, limit - replaceLength) + replaceString;
        }
    }
}
