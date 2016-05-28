package com.walfud.walle.collection;

import java.util.Collection;

/**
 * Created by walfud on 2015/9/21.
 */
public class ForEach {

    public static <T extends Number> T accumulate(Collection<T> collection, T initValue) {
        if (collection == null) {
            return initValue;
        }

        T result = initValue;
        for (T t : collection) {
            if (false) {
                // Stub
            } else if (initValue instanceof Integer) {
                Integer foo = (Integer) result;
                Integer bar = (Integer) t;
                foo += bar;
                result = (T) foo;
            } else if (initValue instanceof Long) {
                Long foo = (Long) result;
                Long bar = (Long) t;
                foo += bar;
                result = (T) foo;
            } else if (initValue instanceof Float) {
                Float foo = (Float) result;
                Float bar = (Float) t;
                foo += bar;
                result = (T) foo;
            } else if (initValue instanceof Double) {
                Double foo = (Double) result;
                Double bar = (Double) t;
                foo += bar;
                result = (T) foo;
            } else {
                // Nothing
            }
        }
        return result;
    }
}
