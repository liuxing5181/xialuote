package com.yuji.xlt.mvvm.dialog

import android.content.Context
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yuji.xlt.framework.R

/**
 * 可滑动的dialog<BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/30]
 * @since V1.0.0
 */
open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    private var bottomSheet: FrameLayout? = null
    private var behavior: BottomSheetBehavior<FrameLayout>? = null

    protected var fragmentActivity: FragmentActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActivity) {
            fragmentActivity = context
        }
    }
    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog?
        bottomSheet = dialog?.delegate?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val layoutParams = it.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.height = getHeight()
            it.layoutParams = layoutParams
            behavior = BottomSheetBehavior.from(it)
            behavior?.peekHeight = getHeight()
            // 初始为展开状态
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }

    }

    /**
     * 主题
     */
    override fun getTheme(): Int {
        return R.style.sheet_dialog_style
    }

    /**
     * 高度 子类可复写
     */
    private fun getHeight():Int{
        return (resources.displayMetrics.heightPixels.toFloat() * 0.7).toInt()
    }


}