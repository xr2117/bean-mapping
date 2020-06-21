package org.crazy.common;


import lombok.NonNull;

/**
 * @author Crazy.X
 * @version 1.3
 */
public class Assert {

    public static void notNull(@NonNull Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
