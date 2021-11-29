/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
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

import java.util.Date;
import java.util.Objects;

/**
 * Represents an OAuth2 authentication body.
 *
 * @since 1.0.0
 * @version 2.7.0
 * @author Katsute
 */
public final class AccessToken {

    private final String token_type;
    private final Long expiry_in_seconds; // when the token expires in seconds since epoch
    private transient final String access_token, refresh_token;

    AccessToken(
        final String token_type,
        final String access_token,
        final String refresh_token,
        final Long expires_in
    ){
        this.token_type        = Objects.requireNonNull(token_type,"Token type can not be null");
        this.access_token      = Objects.requireNonNull(access_token, "Access token can not be null");
        this.refresh_token     = refresh_token;
        this.expiry_in_seconds = (System.currentTimeMillis() / 1000) + expires_in; // now in seconds + time until expiry in seconds
    }

    /**
     * Returns token with token type. Ex: 'Bearer oauth2token'
     *
     * @return token
     *
     * @since 1.0.0
     */
    public final String getToken(){
        return token_type + ' ' + access_token;
    }

    /**
     * Returns the refresh token.
     *
     * @return refresh token
     * @throws NullPointerException if the refresh token is missing
     *
     * @since 1.0.0
     */
    public final String getRefreshToken(){
        return Objects.requireNonNull(refresh_token, "Access token is missing refresh token");
    }

    /**
     * Returns expiry date.
     *
     * @return expiry date
     * @throws NullPointerException if the expiry date is missing
     *
     * @see #getTimeUntilExpires()
     * @since 1.0.0
     */
    public final Date getExpiry(){
        return new Date(Objects.requireNonNull(expiry_in_seconds, "Access token is missing expiry date") * 1000);
    }

    /**
     * Returns how long until the token expires in seconds.
     *
     * @return time until expiry
     * @throws NullPointerException if the expiry date is missing
     *
     * @see #getExpiry()
     * @see #isExpired()
     * @since 1.0.0
     */
    public final long getTimeUntilExpires(){
        return Objects.requireNonNull(expiry_in_seconds, "Access token is missing expiry date") - (System.currentTimeMillis() / 1000); // when the token expires - now in seconds
    }

    /**
     * Returns if the token used is expired.
     *
     * @return if the token used is expired
     * @throws NullPointerException if the expiry date is missing
     *
     * @see #getTimeUntilExpires()
     * @since 1.0.0
     */
    public final boolean isExpired(){
        return getTimeUntilExpires() <= 0;
    }

    @Override
    public final String toString(){
        return "AccessToken{" +
               "token_type='" + token_type + '\'' +
               ", expires=" + expiry_in_seconds +
               '}';
    }

}
