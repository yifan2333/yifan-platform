package com.yifan.common.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

/**
 * The type Assert utils.
 *
 * @author wuyifan
 * @date 2020年05月06日 14:20
 */
public final class AssertUtils {
    /**
     * Instantiates a new Assert utils.
     *
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    private AssertUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Is null.
     *
     * @param obj  the obj
     * @param msg  the msg
     * @param args the args
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static void isNull(Object obj, String msg, Object... args) {
        if (isNull(obj)) {
            msgDevelopment(msg, args);
        }
    }


    /**
     * Is null boolean.
     *
     * @param obj the obj
     * @return the boolean
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static boolean isNull(Object obj) {
        return !nonNull(obj);
    }

    /**
     * Non null boolean.
     *
     * @param obj the obj
     * @return the boolean
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static boolean nonNull(Object obj) {
        if (Objects.nonNull(obj)) {
            if (obj instanceof String) {
                return StringUtils.isNotBlank(obj.toString());
            } else if (obj instanceof Collection) {
                return !((Collection) obj).isEmpty();
            } else if (obj instanceof Map) {
                return !((Map) obj).isEmpty();
            } else if (obj.getClass().isArray()) {
                return Array.getLength(obj) > 0;
            }
            return true;
        }
        return false;
    }

    /**
     * Is null.
     *
     * @param obj the obj
     * @param e   the e
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static void isNull(Object obj, RuntimeException e) {
        if (isNull(obj))
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Is null return null t.
     *
     * @param <T> the type parameter
     * @param obj the obj
     * @return the t
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static <T> T isNullReturnNull(Object obj) {
        return isNull(obj) ? null : transform(obj);
    }

    /**
     * Is null return param t.
     *
     * @param <T>   the type parameter
     * @param obj   the obj
     * @param param the param
     * @return the t
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static <T> T isNullReturnParam(Object obj, T param) {
        return isNull(obj) ? param : transform(obj) ;
    }

    /**
     * Msg development.
     *
     * @param msg  the msg
     * @param args the args
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static void msgDevelopment(String msg, Object... args) {
        throw new InternalException(msg(msg, args));
    }


    /**
     * Msg string.
     *
     * @param msg  the msg
     * @param args the args
     * @return the string
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static String msg(String msg, Object... args) {
        return StringUtils.isBlank(msg) ? "网络异常，请稍后重试！" : String.format(msg, args);
    }

    /**
     * Transform t.
     *
     * @param <T> the type parameter
     * @param obj the obj
     * @return the t
     * @author wuyifan
     * @date 2020年05月06日 14:20
     */
    public static <T> T transform(Object obj) {
        return (T) obj;
    }
}
