package org.crazy.common;

import com.sun.istack.internal.Nullable;

/**
 * @author Crazy.X
 * @version 1.0
 */
public class Assert {

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
