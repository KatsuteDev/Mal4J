package com.kttdevelopment.myanimelist;

/**
 * Represents an OAuth2 authentication body.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class AccessToken {

    private final String token_type;
    private final long expires_in;
    private transient final String access_token, refresh_token;

    AccessToken(final String token_type, final long expires_in, final String access_token, final String refresh_token){
        this.token_type     = token_type;
        this.expires_in     = expires_in;
        this.access_token   = access_token;
        this.refresh_token  = refresh_token;
    }

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
