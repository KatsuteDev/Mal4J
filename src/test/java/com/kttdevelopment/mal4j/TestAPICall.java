package com.kttdevelopment.mal4j;

import com.sun.net.httpserver.HttpServer;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

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
        final AtomicReference<String> method = new AtomicReference<>();
        final AtomicBoolean formENC = new AtomicBoolean();
        server.createContext("/", exchange -> {
            if(exchange.getRequestMethod().equalsIgnoreCase("POST")){
                final StringBuilder SB = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader
                  (exchange.getRequestBody(), StandardCharsets.UTF_8))) {
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

        Assertions.assertDoesNotThrow(call::GET,
                                      Workflow.errorSupplier("Expected GET to not throw"));
        Assertions.assertEquals("GET", method.get(),
                                Workflow.errorSupplier("Expected GET method"));

        method.set(null);
        Assertions.assertDoesNotThrow(() -> call.POST("test"),
                                      Workflow.errorSupplier("Expected POST to not throw"));
        Assertions.assertEquals("POST", method.get(),
                                Workflow.errorSupplier("Expected POST method"));
        Assertions.assertTrue(formENC.get(), Workflow.errorSupplier("Expected form to be encoded"));

        method.set(null);
        Assertions.assertDoesNotThrow(call::DELETE,
                                      Workflow.errorSupplier("Expected DELETE to not throw"));
        Assertions.assertEquals("DELETE", method.get(),
                                Workflow.errorSupplier("Expected DELETE method"));

        method.set(null);
        Assertions.assertDoesNotThrow(call::PATCH,
                                      Workflow.errorSupplier("Expected PATCH to not throw"));
        Assertions.assertEquals("PATCH", method.get(),
                                Workflow.errorSupplier("Expected PATCH method"));
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
