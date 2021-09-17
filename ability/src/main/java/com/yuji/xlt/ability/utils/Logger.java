/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import android.database.SQLException;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.net.BindException;
import java.net.SocketTimeoutException;
import java.security.acl.NotOwnerException;
import java.util.ConcurrentModificationException;
import java.util.MissingResourceException;
import java.util.jar.JarException;

/**
 * <日志工具类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public final class Logger {
    /**
     * 播放模块统一日志标签
     */
    public static final String PLAYER_TAG = "<PLAYER>";

    private static final String TAG = "LOG->";

    private static String mLogTag;

    private static int mLogLevel = Log.DEBUG;

    private Logger() {
    }

    public static void initLogTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            mLogTag = TAG;
        } else {
            mLogTag = tag;
        }
    }

    public static void setLogLevel(int level) {
        mLogLevel = level;
    }

    public static void d(Object message) {
        if (mLogLevel <= Log.DEBUG) {
            printLog(TAG, message, Log.DEBUG);
        }
    }

    public static void d(String tag, Object message) {
        if (mLogLevel <= Log.DEBUG) {
            printLog(tag, message, Log.DEBUG);
        }
    }

    public static void i(Object message) {
        if (mLogLevel <= Log.INFO) {
            printLog(TAG, message, Log.INFO);
        }
    }

    public static void i(String tag, Object message) {
        if (mLogLevel <= Log.INFO) {
            printLog(tag, message, Log.INFO);
        }
    }

    public static void w( Object message) {
        if (mLogLevel <= Log.WARN) {
            printLog(TAG, message, Log.WARN);
        }
    }
    public static void w(String tag, Object message) {
        if (mLogLevel <= Log.WARN) {
            printLog(tag, message, Log.WARN);
        }
    }

    public static void e(Object message) {
        if (mLogLevel <= Log.ERROR) {
            printLog(TAG, message, Log.ERROR);
        }
    }

    public static void e(String tag, Object message) {
        if (mLogLevel <= Log.ERROR) {
            printLog(tag, message, Log.ERROR);
        }
    }

    public static void d(String tag, Object message, Throwable throwable) {
        if (mLogLevel <= Log.DEBUG) {
            String newMessage = message + ":" + checkSensitiveException(throwable);
            printLog(tag, newMessage, Log.DEBUG);
        }
    }

    public static void i(String tag, Object message, Throwable throwable) {
        if (mLogLevel <= Log.INFO) {
            String newMessage = message + ":" + checkSensitiveException(throwable);
            printLog(tag, newMessage, Log.INFO);
        }
    }

    public static void w(String tag, Object message, Throwable throwable) {
        if (mLogLevel <= Log.WARN) {
            String newMessage = message + ":" + checkSensitiveException(throwable);
            printLog(tag, newMessage, Log.WARN);
        }
    }

    public static void e(String tag, Object message, Throwable throwable) {
        if (mLogLevel <= Log.ERROR) {
            String newMessage = message + ":" + checkSensitiveException(throwable);
            printLog(tag, newMessage, Log.ERROR);
        }
    }

    private static String checkSensitiveException(Object object) {
        if (object instanceof FileNotFoundException) {
            return "File is not legal ";
        } else if (object instanceof ConcurrentModificationException) {
            return "Illegal operation";
        } else if (object instanceof SQLException) {
            return "Sql exception";
        } else if (object instanceof JSONException) {
            return "Json convert exception";
        } else if (object instanceof MissingResourceException) {
            return "Resource is missing.";
        } else if (object instanceof JarException) {
            return "Error occurred while reading or writing a JAR file.";
        } else if (object instanceof OutOfMemoryError) {
            return "No more memory could be made available.";
        } else if (object instanceof StackOverflowError) {
            return "Stack overflow occurs because an application recurses too deeply.";
        } else if (object instanceof NotOwnerException) {
            return "Modification principal is not the owner of the object.";
        } else if (object instanceof BindException) {
            return "Exception occurred while binding a socket to a local address and port.";
        } else {
            return object instanceof SocketTimeoutException ? "Socket timeout exception" : null;
        }
    }

    private static void printLog(String tag, Object msg, int level) {
        // 日志打印统一出口
        String logTag = mLogTag + tag;
        Log.println(level, logTag, String.valueOf(msg));
    }

}
