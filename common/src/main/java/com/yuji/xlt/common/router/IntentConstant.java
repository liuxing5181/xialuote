package com.yuji.xlt.common.router;

/**
 * 页面跳转常量类<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/28]
 * @since V1.0.0
 */
public interface IntentConstant {
    /**
     * 点播id
     */
    String VOD_ID = "play_detail_vod_id";
    /**
     * 点播name
     */
    String VOD_NAME = "play_detail_vod_name";
    /**
     * 点播des
     */
    String VOD_DES = "play_detail_vod_des";
    /**
     * 点播Url
     */
    String VOD_URL = "play_detail_vod_url";
    /**
     * 页面参数extra
     */
    String PARM_EXTRA = "parm_extra";

    /**
     * 跳转，目标页面类型
     */
    String PAGE_TYPE = "page_type";

    /**
     * 栏目id
     */
    String COLUMN_ID = "area_detail_column_id";

    //
    //用户相关
    //
    /**
     * 用户id
     */
    String USER_ID = "user_id";
}
