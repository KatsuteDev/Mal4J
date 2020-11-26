package com.kttdevelopment.myanimelist.auth;

public abstract class Authorization {

    public abstract String getAuthorization();

    @SuppressWarnings("SpellCheckingInspection")
    public abstract String getVerifier();

}
