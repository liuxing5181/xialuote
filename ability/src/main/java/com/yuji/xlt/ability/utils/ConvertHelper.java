/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.yuji.xlt.ability.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <转换帮助类>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/7/23]
 * @since V1.0.0
 */
public class ConvertHelper {
    public ConvertHelper() {
    }

    public static <D, S> List<D> convert(List<S> sources, IConverter<S, D> converter) {
        if (!ArrayUtils.isEmpty(sources) && null != converter) {
            List<D> dests = new ArrayList(sources.size());
            Iterator var3 = sources.iterator();

            while (var3.hasNext()) {
                S source = (S) var3.next();
                D dest = converter.convert(source);
                if (null != dest) {
                    dests.add(dest);
                }
            }

            return dests;
        } else {
            return null;
        }
    }

    public static <D, S> List<D> convert(List<S> sources, IPositionConverter<S, D> converter) {
        if (!ArrayUtils.isEmpty(sources) && null != converter) {
            List<D> dests = new ArrayList(sources.size());
            Iterator var3 = sources.iterator();

            while (var3.hasNext()) {
                S source = (S) var3.next();
                D dest = converter.convert(source, dests.size());
                if (null != dest) {
                    dests.add(dest);
                }
            }

            return dests;
        } else {
            return null;
        }
    }

    public interface IPositionConverter<S, D> {
        D convert(S var1, int var2);
    }

    public interface IConverter<S, D> {
        D convert(S var1);
    }
}
