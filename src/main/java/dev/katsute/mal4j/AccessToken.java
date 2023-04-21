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

import java.util.Date;
import java.util.Objects;

/**
 * Represents an access token.
 *
 * @see MyAnimeListAuthenticator#MyAnimeListAuthenticator(Authorization)
 * @see MyAnimeListAuthenticator#MyAnimeListAuthenticator(Authorization, AccessToken)
 * @since 1.0.0
 * @version 2.7.0
 * @author Katsute
 */
public final class AccessToken {

    private final String token_type;
    private final Long expiry_in_seconds; // when the token expires in seconds since epoch
    private transient final String access_token, refresh_token;

    /**
     * Creates an access token from a token.
     *
     * @param access_token access token
     *
     * @see #AccessToken(String, String)
     * @see #AccessToken(String, String, long)
     * @since 2.7.0
     */
    public AccessToken(final String access_token){
        this("Bearer", access_token, null, null);
    }

    /**
     * Creates an access token from a token and refresh token.
     *
     * @param access_token access token
     * @param refresh_token refresh token
     *
     * @see #AccessToken(String)
     * @see #AccessToken(String, String, long)
     * @since 2.7.0
     */
    public AccessToken(final String access_token, final String refresh_token){
        this("Bearer", access_token, refresh_token, null);
    }

    /**
     * Creates an access token with expiry from a token and refresh token.
     *
     * @param access_token access token
     * @param refresh_token refresh token
     * @param expiry_in_seconds when the token expires as seconds since EPOCH
     *
     * @see #AccessToken(String)
     * @see #AccessToken(String, String)
     * @since 2.7.0
     */
    public AccessToken(final String access_token, final String refresh_token, final long expiry_in_seconds){
        this("Bearer", access_token, refresh_token, expiry_in_seconds);
    }

    AccessToken(
        final String token_type,
        final String access_token,
        final String refresh_token,
        final Long expiry_in_seconds
    ){
        this.token_type        = Objects.requireNonNull(token_type,"Token type can not be null");
        this.access_token      = Objects.requireNonNull(access_token, "Access token can not be null");
        this.refresh_token     = refresh_token;
        this.expiry_in_seconds = expiry_in_seconds;

        Logging.addMask(this.access_token);
        Logging.addMask(this.refresh_token);

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
     * Returns the refresh token or null.
     *
     * @return refresh token
     * @throws NullPointerException if the refresh token is missing
     *
     * @since 1.0.0
     */
    public final String getRefreshToken(){
        return Objects.requireNonNull(refresh_token, "Refresh token is missing");
    }

    /**
     * Returns expiry date.
     *
     * @return expiry date
     * @throws NullPointerException if the expiry date is missing
     *
     * @see #getExpiryEpochSeconds()
     * @see #getTimeUntilExpires()
     * @since 1.0.0
     */
    public final Date getExpiry(){
        return new Date(getExpiryEpochSeconds() * 1000);
    }

    /**
     * Returns expiry date as seconds since epoch.
     *
     * @return expiry date
     * @throws NullPointerException if the expiry date is missing
     *
     * @see #getExpiry()
     * @see #getTimeUntilExpires()
     * @since 2.7.0
     */
    public final long getExpiryEpochSeconds(){
        return Objects.requireNonNull(expiry_in_seconds, "Access token is missing expiry date");
    }

    /**
     * Returns how long until the token expires in seconds.
     *
     * @return time until expiry
     * @throws NullPointerException if the expiry date is missing
     *
     * @see #getExpiry()
     * @see #getExpiryEpochSeconds()
     * @see #isExpired()
     * @since 1.0.0
     */
    public final long getTimeUntilExpires(){
        return getExpiryEpochSeconds() - (System.currentTimeMillis() / 1000); // when the token expires - now in seconds
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