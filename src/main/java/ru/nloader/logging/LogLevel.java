/*
 * Copyright (C) 2014 asn007 aka Andrey Sinitsyn <andrey.sin98@gmail.com>
 *
 * This file (LogLevel.java) is part of nLogger.
 *
 *     nLogger is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     nLogger is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.nloader.logging;

/**
 * Created by asn007 on 10.05.2014.
 */
public enum LogLevel {
    // 4 is blue, 7 is white, 3 is yellow and 1 is red
    DEBUG(4), INFO(7), WARN(3), EXCEPTION(1);
    private final int colorCode;
    LogLevel(int colorCode) { this.colorCode = colorCode; }
    public int getColorCode() { return colorCode; }
}
