/*
 * Copyright (C) 2015 asn007 aka Andrey Sinitsyn <andrey.sin98@gmail.com>
 *
 *  This file (Logger.java) is part of nLogger.
 *
 *      nLogger is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      nLogger is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with nLogger.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.nloader.logging;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asn007 on 10.05.2014.
 */
public class Logger {
    private static File logFile = new File("nLogger.log");
    private static LogLevel consoleOutputLogLevel = LogLevel.INFO;
    private static FileWriter logger;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    private static Date now;

    public static void log(LogLevel level, String text) {
        if(logger == null)
            setupLogger();
        now = new Date();
        String line = String.format("[%s][%s] %s", dateFormat.format(now), level.name().toUpperCase(), text);
        if(logger != null) {
            try {
                logger.write(line + System.lineSeparator());
                logger.flush();
            } catch (IOException e) {
                System.out.println(stack2string(e));
            }
        }
        if(level.ordinal() >= consoleOutputLogLevel.ordinal())
            System.out.println(line);
    }

    public static void setup(File f) {
        logFile = f;
        setupLogger();
    }

    private static void setupLogger() {
        try {
            logger = new FileWriter(logFile, false);
        } catch (Exception e) {
            System.out.println("Failed to initialize logger!");
            System.out.println(stack2string(e));
        }
    }
    public static void debug(String text) {
        log(LogLevel.DEBUG, text);
    }

    public static void info(String text) {
        log(LogLevel.INFO, text);
    }

    public static void warn(String text) {
        log(LogLevel.WARN, text);
    }

    public static void logException(Exception e) {
        String s = stack2string(e);
        for(String str: s.split(System.lineSeparator())) {
            log(LogLevel.EXCEPTION, str);
        }
    }

    public static String stack2string(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            return "------" + System.lineSeparator() + sw.toString() + "------" + System.lineSeparator();
        } catch (Exception e2) {
            return "Bad stack2string";
        }
    }

    public static void setLogDisplayLevel(LogLevel lvl) {
        consoleOutputLogLevel = lvl;
    }

    public static LogLevel getLogDisplayLevel() {
        return consoleOutputLogLevel;
    }

}
