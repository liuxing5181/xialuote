
package com.yuji.xlt.ability.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * View 视图工具类<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ViewUtils {
    private ViewUtils() {
    }

    /**
     * 设置可见性
     *
     * @param view      view
     * @param isVisible 是否可见
     */
    public static void setVisible(View view, boolean isVisible) {
        if (view == null) {
            return;
        }
        int visible = isVisible ? View.VISIBLE : View.INVISIBLE;
        view.setVisibility(visible);
    }

    /**
     * 设置可见性
     *
     * @param view    视图
     * @param visible 是否可见
     */
    public static void setVisible(View view, int visible) {
        if (view != null) {
            view.setVisibility(visible);
        }
    }

    /**
     * 是否可见
     *
     * @param view 视图
     * @return 可见性
     */
    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    /**
     * 是否 gone
     *
     * @param view view 视图
     * @return 是否 gone
     */
    public static boolean isGone(View view) {
        return view != null && view.getVisibility() == View.GONE;
    }

    /**
     * 是否不可见
     *
     * @param view view 视图
     * @return 是否不可见
     */
    public static boolean isInvisible(View view) {
        return view != null && view.getVisibility() == View.INVISIBLE;
    }

    /**
     * 根据指定的 id 获得指定的视图
     *
     * @param view 父控件 View
     * @param id   资源 id
     * @param <T>  View 泛型
     * @return 根据指定的 id 获得指定的视图
     */
    public static <T extends View> T findViewById(View view, int id) {
        if (view != null) {
            return (T) view.findViewById(id);
        }
        return null;
    }

    /**
     * 根据指定的 id 获得指定的视图
     *
     * @param activity activity
     * @param id       资源 id
     * @param <T>      View 泛型
     * @return 根据指定的 id 获得指定的视图
     */
    public static <T extends View> T findViewById(Activity activity, int id) {
        if (activity != null) {
            return (T) activity.findViewById(id);
        }
        return null;
    }

    /**
     * 通过Tag查找Fragment
     *
     * @param fragmentManager fragment管理器
     * @param id              Tag
     * @param fragmentClass   待转换的类型
     * @param <T>             fragment泛型
     * @return 通过Tag查找到的Fragment
     */
    public static <T> T findFragmentById(FragmentManager fragmentManager, int id, Class<T> fragmentClass) {
        if (fragmentClass == null || fragmentManager == null) {
            return null;
        }
        Fragment fragment = fragmentManager.findFragmentById(id);
        if (fragmentClass.isInstance(fragment)) {
            return (T) fragment;
        }
        return null;
    }

    /**
     * 获得父控件
     *
     * @param view view 视图
     * @return 父控件
     */
    public static ViewGroup getParent(View view) {
        if (view != null) {
            ViewParent viewParent = view.getParent();
            if (viewParent instanceof ViewGroup) {
                return (ViewGroup) viewParent;
            }
        }
        return null;
    }

    /**
     * 设置背景
     *
     * @param view     view 视图
     * @param drawable 背景图片
     */
    public static void setBackground(View view, Drawable drawable) {
        if (view != null) {
            view.setBackground(drawable);
        }
    }

    /**
     * 设置背景
     *
     * @param view  view 视图
     * @param resId 资源 id
     */
    public static void setBackground(View view, int resId) {
        if (view != null) {
            view.setBackgroundResource(resId);
        }
    }

    /**
     * 设置是否可获得焦点
     *
     * @param view        view 视图
     * @param isFocusable 是否可获焦
     */
    public static void setFocusable(View view, boolean isFocusable) {
        if (view != null) {
            view.setFocusable(isFocusable);
        }
    }

    /**
     * 设置是否可点击
     *
     * @param view        view 视图
     * @param isClickable 是否可点击
     */
    public static void setClickable(View view, boolean isClickable) {
        if (view != null) {
            view.setClickable(isClickable);
        }
    }

    /**
     * 设置选中
     *
     * @param view       view 视图
     * @param isSelected 是否选中
     */
    public static void setSelected(View view, boolean isSelected) {
        if (view != null) {
            view.setSelected(isSelected);
        }
    }

    /**
     * 设置点击事件
     *
     * @param view     view 视图
     * @param listener 监听器
     */
    public static void setClickListener(View view, View.OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 设置焦点变化监听
     *
     * @param view     view 视图
     * @param listener 监听器
     */
    public static void setFocusChangeListener(View view, View.OnFocusChangeListener listener) {
        if (view != null) {
            view.setOnFocusChangeListener(listener);
        }
    }

    /**
     * 清除所有子视图
     *
     * @param viewGroup 父控件
     */
    public static void removeAllView(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    /**
     * 从child的parent中移除该child<BR>
     *
     * @param child 子控件
     */
    public static void removeViewFromParent(View child) {
        if (child != null) {
            ViewGroup parent = ViewUtils.getParent(child);
            if (parent != null) {
                parent.removeView(child);
            }
        }
    }

    /**
     * viewgroup是否包含了指定的view<BR>
     *
     * @param parent 父控件
     * @param child  子控件
     * @return viewgroup是否包含了指定的view
     */
    public static boolean isContainChild(ViewGroup parent, View child) {
        if (parent != null && child != null) {
            return parent.indexOfChild(child) != -1;
        }
        return false;
    }

    /**
     * 设置TextView
     *
     * @param view  view
     * @param value 数据
     */
    public static void setTextValue(TextView view, String value) {
        if (view == null || TextUtils.isEmpty(value)) {
            return;
        }
        view.setText(value);
    }
}
