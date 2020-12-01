package com.kttdevelopment.myanimelist.auth;

/**
 * Represents an OAuth2 authentication body.
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
     * Returns token with token type. Ex: 'Bearer oauth2token'
     * '
     * @return token
     *
     * @since 1.0.0
     */
    public final String getToken(){
        return token_type + ' ' + access_token;
    }

    /**
     * Returns expiry in seconds.
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
               '}';
    }

}
