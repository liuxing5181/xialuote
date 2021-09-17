package com.yuji.common_x.text

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.yuji.common_x.glide.BitmapRequest
import com.yuji.common_x.glide.RequestManagerRetriever

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/27 0:27]
 * @Description: NA
 */
class XGlide1 private constructor(private val retriever: RequestManagerRetriever) {
    companion object {
        @Volatile
        private var instance: XGlide1? = null
            private get() {
                if (field == null) {
                    synchronized(XGlide1::class.java) {
                        if (field == null) {
                            val retriever = RequestManagerRetriever()
                            field = XGlide1(retriever)
                        }
                    }
                }
                return field
            }

        fun getRetriever(): RequestManagerRetriever {
            return instance!!.retriever
        }

        fun with(fragment: Fragment?): BitmapRequest {
            return getRetriever()[fragment]
        }

        fun with(activity: FragmentActivity?): BitmapRequest {
            return getRetriever()[activity]
        }

        fun with(context: Context?): BitmapRequest {
            return getRetriever()[context]
        }
    }
}