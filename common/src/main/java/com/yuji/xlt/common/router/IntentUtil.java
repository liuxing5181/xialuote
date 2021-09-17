package com.yuji.xlt.common.router;

import android.os.Bundle;

import com.yuji.xlt.ability.AppContext;
import com.yuji.xlt.ability.utils.Logger;
import com.yuji.xlt.ability.utils.StringUtils;
import com.yuji.xlt.common.router.models.UriRequest;


/**
 * 暂时不涉及组件化，先自定义Intent工具类，后续注解实现<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/28]
 * @since V1.0.0
 */
public class IntentUtil implements IntentConstant, PageConstant {
    private static final String TAG = "IntentUtil";

    private IntentUtil() {
    }

    /**
     * 跳转通用页面
     *
     * @param pageType 页面类型
     * @param userId   用户id
     */
    public static boolean processCurrency(String pageType, String userId) {
        Logger.i(TAG, "processCurrency, id pageType " + pageType);
        if (StringUtils.isEmpty(pageType)) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString(PAGE_TYPE, pageType);
        bundle.putString(USER_ID, userId);
        UriRequest uriRequest = new UriRequest();
        uriRequest.setTargetClassName(PAGE_CURRENCY);
        uriRequest.setBundle(bundle);
        RouterUtil.startActivity(AppContext.INSTANCE.getContext(), uriRequest);
        return true;
    }

    /**
     * 跳转家庭医生页面
     *
     * @param userId 用户id
     */
    public static boolean processFamilyDoctor(String userId) {
        Logger.i(TAG, "processFamilyDoctor, id userId " + userId);
        Bundle bundle = new Bundle();
        bundle.putString(USER_ID, userId);
        UriRequest uriRequest = new UriRequest();
        uriRequest.setTargetClassName(PAGE_CURRENCY);
        uriRequest.setBundle(bundle);
        RouterUtil.startActivity(AppContext.INSTANCE.getContext(), uriRequest);
        return true;
    }



}
