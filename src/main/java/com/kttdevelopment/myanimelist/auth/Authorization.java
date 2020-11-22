package com.kttdevelopment.myanimelist.auth;

/**
 * Represents an authorization.
 *
 * @since 1.0.0
 */
public abstract class Authorization {

    /**
     * Returns the authorization.
     *
     * @return authorization
     *
     * @since 1.0.0
     */
    public abstract String getAuthorization();

    /**
     * Returns the PKCE verifier.
     *
     * @return verifier
     *
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public abstract String getVerifier();

}
