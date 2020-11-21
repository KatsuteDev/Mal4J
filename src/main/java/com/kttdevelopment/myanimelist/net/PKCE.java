package com.kttdevelopment.myanimelist.net;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@SuppressWarnings("SpellCheckingInspection")
public class PKCE {

    public static String generateCodeVerifier(){
        final SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
    }

    public static String generateCodeChallange(String codeVerifier) throws NoSuchAlgorithmException{
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }

}
