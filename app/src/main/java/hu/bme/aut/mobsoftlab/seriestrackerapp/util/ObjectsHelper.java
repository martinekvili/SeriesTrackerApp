package hu.bme.aut.mobsoftlab.seriestrackerapp.util;

import java.util.Arrays;

public class ObjectsHelper {

    public static boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hash(Object... objects) {
        return Arrays.hashCode(objects);
    }
}
