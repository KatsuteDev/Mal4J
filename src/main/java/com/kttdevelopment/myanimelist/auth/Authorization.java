package com.kttdevelopment.myanimelist.auth;

abstract class Authorization {

    // auth code
    public abstract String getAuthorization();

    // PKCE
    @SuppressWarnings("SpellCheckingInspection")
    public abstract String getVerifier();

}
