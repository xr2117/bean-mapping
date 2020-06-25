package org.map.common;


import lombok.NonNull;

/**
 * @author Crazy.X
 * @version 2.1.1
 */
public class Assert {

    public static void notNull(@NonNull Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
