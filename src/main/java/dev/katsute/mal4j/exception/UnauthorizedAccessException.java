/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

package dev.katsute.mal4j.exception;

import dev.katsute.mal4j.MyAnimeListAuthenticator;

/**
 * Thrown if the state parameter was illegally modified.
 *
 * @see MyAnimeListAuthenticator
 * @since 1.0.0
 * @version 3.0.0
 * @author Katsute
 */
public final class UnauthorizedAccessException extends RuntimeException {

    @SuppressWarnings("SameParameterValue")
    public UnauthorizedAccessException(final String message){
        super(message);
    }

}