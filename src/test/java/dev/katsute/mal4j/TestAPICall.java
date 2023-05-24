package dev.katsute.mal4j;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static dev.katsute.mal4j.APIStruct.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAPICall {

    private static HttpServer server;

    @BeforeAll
    static void beforeAll() throws IOException{
        server = HttpServer.create(new InetSocketAddress(8080), 0);
    }

    @AfterAll
    static void afterAll(){
        if(server != null)
            server.stop(0);
    }

    @Test
    final void testCall(){
        final AtomicReference<String> method = new AtomicReference<>();
        final AtomicBoolean formENC = new AtomicBoolean();
        server.createContext("/", exchange -> {
            if(exchange.getRequestMethod().equalsIgnoreCase("POST")){
                final StringBuilder SB = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader
                  (exchange.getRequestBody(), StandardCharsets.UTF_8))){
                    int c;
                    while ((c = reader.read()) != -1)
                        SB.append((char) c);
                }
                formENC.set(SB.toString().equalsIgnoreCase("test=test"));
            }
            method.set(exchange.getRequestMethod().toUpperCase());

            exchange.sendResponseHeaders(200, "{}".length());
            try(final OutputStream OUT = exchange.getResponseBody()){
                OUT.write("{}".getBytes(StandardCharsets.UTF_8));
            }
            exchange.close();
        });
        server.start();

        final Call call = Call.create();

        assertDoesNotThrow(call::GET);
        assertEquals("GET", method.get());

        method.set(null);
        assertDoesNotThrow(() -> call.POST("test"));
        assertEquals("POST", method.get());
        assertTrue(formENC.get());

        method.set(null);
        assertDoesNotThrow(call::DELETE);
        assertEquals("DELETE", method.get());

        method.set(null);
        assertDoesNotThrow(call::PATCH);
        assertEquals("PATCH", method.get());
    }

    @SuppressWarnings("UnusedReturnValue")
    interface Call {

        static Call create(){
            return APICall.create("http://localhost:8080", Call.class);
        }

        @SuppressWarnings("DefaultAnnotationParam")
        @Endpoint(method = "GET", value = "/")
        Response<Json.JsonObject> GET();

        @FormUrlEncoded
        @Endpoint(method = "POST", value = "/")
        Response<Json.JsonObject> POST(
            @Field("test") final String test
        );

        @Endpoint(method = "DELETE", value = "/")
        Response<Json.JsonObject> DELETE();

        @FormUrlEncoded
        @Endpoint(method = "PATCH", value = "/")
        Response<Json.JsonObject> PATCH();

    }

}