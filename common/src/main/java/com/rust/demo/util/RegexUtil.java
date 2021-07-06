package com.rust.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static String retain(String str, String regex) {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        if (!matcher.find()) {
            return "";
        }
        return matcher.group();
    }
}
