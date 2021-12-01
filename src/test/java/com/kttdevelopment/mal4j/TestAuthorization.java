package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class TestAuthorization {

    @Test
    public void testURLCallback(){
        final AtomicReference<String> str = new AtomicReference<>();
        try{
            new MyAnimeListAuthenticator
                .LocalServerBuilder("cid", 5050)
                .setURLCallback(str::set)
                .setTimeout(2)
                .build();
        }catch(final Throwable ignored){ }
        final String s = str.get();
        Assertions.assertTrue(s.startsWith("https://myanimelist.net/v1/oauth2/authorize?response_type=code"),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&client_id=cid"),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&code_challenge="),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&code_challenge_method=plain"),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&state="),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
    }


    @Test
    public void testNullBuilder(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator.LocalServerBuilder(null, 5050),
                                Workflow.errorSupplier("Expected MyAnimeListAuthenticator#LocalServerBuilder with null client ID to throw a NullPointerException"));
    }

    @Test
    public void testNull(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator(null));
    }


}
