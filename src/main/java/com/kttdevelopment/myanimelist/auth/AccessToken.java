package com.kttdevelopment.myanimelist.auth;

public final class AccessToken {
    // do not label variables as transient, it will cause retrofit to fail

    private String token_type;

    private long expires_in;

    private String access_token;

    private String refresh_token;

    public final String getToken(){
        return token_type + ' ' + access_token;
    }

    public final long getExpiry(){
        return expires_in;
    }

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
