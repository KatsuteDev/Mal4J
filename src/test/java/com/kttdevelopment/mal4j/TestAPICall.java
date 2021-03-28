package com.kttdevelopment.mal4j;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kttdevelopment.mal4j.APIStruct.*;

public class TestAPICall {

    private static HttpServer server;

    @BeforeAll
    public static void beforeAll() throws IOException{
        server = HttpServer.create(new InetSocketAddress(8080), 0);
    }

    @AfterAll
    public static void afterAll(){
        if(server != null)
            server.stop(0);
    }

    @Test
    public void testCall(){
        final AtomicBoolean PASS = new AtomicBoolean();
        final AtomicBoolean formENC = new AtomicBoolean();
        server.createContext("/", exchange -> {
            switch(exchange.getRequestMethod().toUpperCase()){
                case "POST":
                    final StringBuilder SB = new StringBuilder();
                    try (Reader reader = new BufferedReader(new InputStreamReader
                      (exchange.getRequestBody(), StandardCharsets.UTF_8))) {
                        int c;
                        while ((c = reader.read()) != -1)
                            SB.append((char) c);
                    }
                    formENC.set(SB.toString().equalsIgnoreCase("test=test"));
                case "GET":
                case "DELETE":
                case "PATCH":
                    PASS.set(true);
            }

            exchange.sendResponseHeaders(200, "{}".length());
            try(final OutputStream OUT = exchange.getResponseBody()){
                OUT.write("{}".getBytes(StandardCharsets.UTF_8));
            }
            exchange.close();
        });
        server.start();

        final Call call = Call.create();

        Assertions.assertDoesNotThrow(call::GET);
        Assertions.assertTrue(PASS.get(), "Failed to execute GET");

        PASS.set(false);
        Assertions.assertDoesNotThrow(() -> call.POST("test"));
        Assertions.assertTrue(PASS.get(), "Failed to execute POST");
        Assertions.assertTrue(formENC.get());

        PASS.set(false);
        Assertions.assertDoesNotThrow(call::DELETE);
        Assertions.assertTrue(PASS.get(), "Failed to execute DELETE");

        PASS.set(false);
        Assertions.assertDoesNotThrow(call::PATCH);
        Assertions.assertTrue(PASS.get(), "Failed to execute PATCH");
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
