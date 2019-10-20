package by.devpav.serfor.exceptions;

import static java.util.Objects.isNull;

public class ObjectThrow {

    public static void entityNotFoundThrow(Object object, String message) {
        if (isNull(object)) throw new EntityNotFoundException(message);
    }

    public static void entityNotFoundThrow(Boolean exists, String message) {
        if (!exists) throw new EntityNotFoundException(message);
    }

    public static void requireNotNullThrow(Object object, String message) {
        if (isNull(object)) throw new NullPointerException(message);
    }

    public static void requireRealmExists(Object object, String message) {
        if (isNull(object)) throw new EntityNotFoundException(message);
    }

}
