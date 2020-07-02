package cn.dyg.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateUtil 类是
 *
 * @author dongyinggang
 * @date 2020-06-29 16:04
 **/
public class DateUtil {

    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ";
    }
}
