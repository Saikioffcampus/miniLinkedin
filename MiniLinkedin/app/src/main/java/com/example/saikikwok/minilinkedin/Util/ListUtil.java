package com.example.saikikwok.minilinkedin.Util;

import java.util.List;

/**
 * Created by saikikwok on 8/24/16.
 */

public class ListUtil {

    public static <E> String List2String(List<E> list) {
        StringBuilder sb = new StringBuilder();
        for (E each : list) {
            sb.append("-");
            sb.append(each.toString());
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
