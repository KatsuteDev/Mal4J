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

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents client ID authorization.
 *
 * @see MyAnimeListAuthenticator#MyAnimeListAuthenticator(Authorization)
 * @see MyAnimeListAuthenticator#MyAnimeListAuthenticator(Authorization, AccessToken)
 * @since 2.7.0
 * @version 2.7.0
 * @author Katsute
 */
public final class Authorization {

    // [\w\-.~]*
    private static final Pattern allowedPKCE = Pattern.compile("[\\w\\-.~]*");

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private final String client_id, client_secret, authorization_code, pkce, redirect_uri;

    /**
     * Creates authorization from client ID. <code>authorization_code</code> is different from the authorization URL, do not use the URL by mistake.
     *
     * @param client_id client id <b>(required)</b>
     * @param client_secret client secret <b>(optional)</b>
     * @param authorization_code authorization code <b>(required)</b>
     * @param PKCE PKCE code challenge used to generate authorization code <b>(required)</b>
     * @throws NullPointerException if missing required value
     * @throws IllegalArgumentException if invalid PKCE
     *
     * @see #Authorization(String, String, String, String, String)
     * @since 2.7.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Authorization(final String client_id, final String client_secret, final String authorization_code, final String PKCE){
        this(client_id, client_secret, authorization_code, PKCE, null);
    }

    /**
     * Creates authorization from client ID. <code>authorization_code</code> is different from the authorization URL, do not use the URL by mistake.
     *
     * @param client_id client id <b>(required)</b>
     * @param client_secret client secret <b>(optional)</b>
     * @param authorization_code authorization code <b>(required)</b>
     * @param PKCE PKCE code challenge used to generate authorization code <b>(required)</b>
     * @param redirect_uri redirect uri <b>(optional, required if used to generate authorization code)</b>
     * @throws NullPointerException if missing required value
     * @throws IllegalArgumentException if invalid PKCE
     *
     * @see #Authorization(String, String, String, String)
     * @since 2.7.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Authorization(final String client_id, final String client_secret, final String authorization_code, final String PKCE, final String redirect_uri){
        /* check null */ {
            Objects.requireNonNull(client_id, "Client ID must not be null");
            Objects.requireNonNull(authorization_code, "Authorization code must not be null");
            Objects.requireNonNull(PKCE, "PKCE code challenge must not be null");
        }

        /* mask logs */ {
            Logging.addMask(client_id);
            Logging.addMask(client_secret);
            Logging.addMask(authorization_code);
            Logging.addMask(PKCE);
        }

        /* validate authorization */ {
            if(authorization_code.startsWith("https://") || authorization_code.startsWith("http://") || authorization_code.startsWith("www."))
                Logging.getLogger().warning("Authorization code looks like a URL, make sure you are passing the authorization code and not the authorization URL");
        }

        /* validate PKCE */ {
            final int PKCE_len = PKCE.length();
            if(PKCE_len < 43 || PKCE_len > 128)
                throw new IllegalArgumentException("PKCE code challenge must be between 43 and 128 characters, was " + PKCE_len + " characters");
            else if(!allowedPKCE.matcher(PKCE).matches())
                throw new IllegalArgumentException("PKCE code challenge contains illegal characters, only a-z , A-Z , 0-9 , _ , . , - , and ~ are allowed");
        }

        this.client_id          = client_id;
        this.client_secret      = client_secret;
        this.authorization_code = authorization_code;
        this.pkce               = PKCE;
        this.redirect_uri       = redirect_uri;
    }

    // retrieval methods

    /**
     * Returns the client ID.
     *
     * @return client ID
     *
     * @since 2.7.0
     */
    public final String getClientID(){
        return this.client_id;
    }

    /**
     * Returns the client secret if it has one.
     *
     * @return client secret or null
     *
     * @since 2.7.0
     */
    public final String getClientSecret(){
        return this.client_secret;
    }

    /**
     * Returns the authorization code.
     *
     * @return authorization code
     *
     * @since 2.7.0
     */
    public final String getAuthorizationCode(){
        return this.authorization_code;
    }

    /**
     * Returns the PKCE code challenge used to generate the authorization code.
     *
     * @return PKCE code challenge
     *
     * @since 2.7.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public final String getPKCE(){
        return this.pkce;
    }

    /**
     * Returns the redirect URI if it has one.
     *
     * @return redirect uri
     *
     * @since 2.7.0
     */
    public final String getRedirectURI(){
        return redirect_uri;
    }

    @Override
    public final String toString(){
        return "Authorization{" +
               "redirect_uri='" + redirect_uri + '\'' +
               '}';
    }

}