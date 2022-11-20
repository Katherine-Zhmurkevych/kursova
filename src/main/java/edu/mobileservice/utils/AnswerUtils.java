package edu.mobileservice.utils;

import java.util.Arrays;
import java.util.List;

public class AnswerUtils {
    public static final List<String> YES_ANSWERS = Arrays.asList("Yes", "YES", "yes");
    public static final List<String> NO_ANSWERS = Arrays.asList("No", "NO", "no");
    public static final String POSITIVE = "Positive";
    public static final String NEGATIVE = "Negative";

    private AnswerUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                System.out.print("\033\143");
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
