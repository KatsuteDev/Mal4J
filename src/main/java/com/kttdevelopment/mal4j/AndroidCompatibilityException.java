/*
 * Copyright (C) 2021-2022 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.kttdevelopment.mal4j;

/**
 * <b>This exception is no longer thrown.</b>
 * <br>
 * Thrown if the Android version is not compatible with a genuine Java runtime.
 *
 * @deprecated exception is no longer thrown
 *
 * @since 2.2.1
 * @version 2.4.0
 * @author Katsute
 */
@Deprecated
public final class AndroidCompatibilityException extends RuntimeException {

    @SuppressWarnings("SameParameterValue")
    AndroidCompatibilityException(final String message){
        super(message);
    }

}
