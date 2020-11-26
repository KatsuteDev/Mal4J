package com.kttdevelopment.myanimelist.auth;

/**
 * Represents an access token.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class AccessToken {
    // do not label variables as transient, it will cause retrofit to fail

    private String token_type;

    private long expires_in;

    private String access_token;

    private String refresh_token;

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

    @Override
    public String toString(){
        return "AccessToken{" +
               "token_type='" + token_type + '\'' +
               ", expires_in=" + expires_in +
               ", access_token='" + access_token + '\'' +
               ", refresh_token='" + refresh_token + '\'' +
               '}';
    }

}
