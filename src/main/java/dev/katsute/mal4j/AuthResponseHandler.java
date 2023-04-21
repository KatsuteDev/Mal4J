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

package dev.katsute.mal4j;

/**
 * The response handler determines what the webpage will look like after the user authenticates with MyAnimeList.
 *
 * @see MyAnimeListAuthenticator.LocalServerBuilder
 * @see MyAnimeListAuthenticator.LocalServerBuilder#setResponseHandler(AuthResponseHandler)
 * @since 1.1.0
 * @version 1.1.0
 * @author Katsute
 */
@FunctionalInterface
public interface AuthResponseHandler {

    /**
     * Sends the response HTML given the query parameters. Some unicode characters may not render properly, use HTML entities if you encounter this problem.
     *
     * @param code authorization code (null if error)
     * @param error error (nullable)
     * @param message error message (nullable)
     * @param hint error hint (nullable)
     * @return HTML string
     *
     * @since 1.1.0
     */
    String getResponse(final String code, final String error , final String message, final String hint);

}