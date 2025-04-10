package com.appvenir.infrastructure.system.logger;

public class Logger {

    // ANSI escape code for red text
    private static final String RED = "\u001B[31m";
    final static String REDRESET = "\u001B[0m";

    static final String GREEN = "\u001B[32m";
    static final String GREENRESET = "\u001B[0m";

    static final String YELLOW = "\u001B[33m";
    static final String YELLOWRESET = "\u001B[0m"; 

    static final String BOLD = "\u001B[1m";   // ANSI code for bold
    static final String BOLDRESET = "\u001B[0m"; 

    public static void info(String message)
    {
        System.out.println(green(bold("[INFO]")) + " " + message);
    }

    public static void warn(String message)
    {
        System.out.println(yellow(bold("[WARN]")) + " " + message);
    }

    public static void error(String message)
    {
        System.out.println(red(bold("[ERROR]")) + " " + message);
    }

    public static void nextLine(String message)
    {
        System.out.println("       " + message);
    }

    private static String green(String value)
    {
        return GREEN + value + GREENRESET;
    }

    private static String yellow(String value)
    {
        return YELLOW + value + YELLOWRESET;
    }

    private static String red(String value)
    {
        return RED + value + REDRESET;
    }

    private static String bold(String value)
    {
        return BOLD + value + BOLDRESET;
    }
    
}
