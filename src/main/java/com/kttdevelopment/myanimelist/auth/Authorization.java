package com.kttdevelopment.myanimelist.auth;

abstract class Authorization {

    // auth code
    public abstract String getAuthorization();

    // PKCE
    public abstract String getVerifier();

}
