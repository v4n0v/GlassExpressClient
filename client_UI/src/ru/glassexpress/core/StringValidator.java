package ru.glassexpress.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    public static boolean isYearsInputCorrect(String testString) {
        Pattern p = Pattern.compile("(\\d{4})(\\W)(\\d{4})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static boolean isSingleYearInputCorrect(String testString) {
        Pattern p = Pattern.compile("(\\d{4})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static boolean isLoginCorrect(String testString) {
        Pattern p = Pattern.compile("^[a-z0-9]+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
    public static boolean isPassCorrect(String testString) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
