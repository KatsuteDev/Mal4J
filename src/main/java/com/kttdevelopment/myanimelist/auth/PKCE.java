package com.kttdevelopment.myanimelist.auth;

import java.security.*;
import java.util.Base64;

@SuppressWarnings("SpellCheckingInspection")
final class PKCE {

    static String generateCodeVerifier(){
        final SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[64];
        secureRandom.nextBytes(codeVerifier);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
    }

}
