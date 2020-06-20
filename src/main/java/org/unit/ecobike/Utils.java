package org.unit.ecobike;

public class Utils {
    public static void require(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }
}
