package com.kttdevelopment.myanimelist.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an access token.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class AccessToken {

    String token_type;

    long expires_in;

    transient String access_token;

    transient String refresh_token;

    /**
     * Returns the access token.
     *
     * @return access token
     *
     * @since 1.0.0
     */
    public final String getToken(){
        return token_type + ' ' + access_token;
    }

    /**
     * Returns how long until the token expires in milliseconds.
     *
     * @return expiry
     *
     * @since 1.0.0
     */
    public final long getExpiry(){
        return expires_in;
    }


    /**
     * Returns the refresh token.
     *
     * @return refresh token
     *
     * @since 1.0.0
     */
    public final String getRefreshToken(){
        return refresh_token;
    }

    /**
     * Refreshes the token.
     *
     * @return new access token
     *
     * @since 1.0.0
     */
    public synchronized final AccessToken refreshToken(){
        return null;
    }



}
