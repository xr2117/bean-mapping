package org.crazy.common;


import lombok.NonNull;

/**
 * @author Crazy.X
 * @version 2.0.1
 */
public class Assert {

    public static void notNull(@NonNull Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
