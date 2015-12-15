package com.himeetu.util;

import java.util.Collection;

/**
 * Created by zhangxinjian on 15/9/24.
 */
public class CollectionUtil {
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
