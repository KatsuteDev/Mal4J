package com.kttdevelopment.myanimelist;

public class TestJson {

    private static String jsonObj = "{\n" +
                                    "\t\"double\": 1.0,\n" +
                                    "\t\"doublen\": -1.0,\n" +
                                    "\t\"doubles\": 1.0,\n" +
                                    "\t\"int\": 5,\n" +
                                    "\t\"intn\": -5,\n" +
                                    "\t\"ints\": 5,\n" +
                                    "\t\"string\": \"string\",\n" +
                                    "\t\"obj\": {\n" +
                                    "\t\t\"double\": 1.0,\n" +
                                    "\t\t\"doublen\": -1.0,\n" +
                                    "\t\t\"doubles\": 1.0,\n" +
                                    "\t\t\"int\": 5,\n" +
                                    "\t\t\"intn\": -5,\n" +
                                    "\t\t\"ints\": 5,\n" +
                                    "\t\t\"string\": \"string\",\n" +
                                    "\t\t\"obj\": {\n" +
                                    "\t\t\t\"string\": \"string\"\n" +
                                    "\t\t},\n" +
                                    "\t\t\"arr\": [\n" +
                                    "\t\t\t\"string\"\n" +
                                    "\t\t]\n" +
                                    "\t},\n" +
                                    "\t\"arr\": [\n" +
                                    "\t\t1.0,\n" +
                                    "\t\t-1.0,\n" +
                                    "\t\t5,\n" +
                                    "\t\t-5,\n" +
                                    "\t\t\"str\",\n" +
                                    "\t\t{\n" +
                                    "\t\t\t\"string\": \"string\"\n" +
                                    "\t\t},\n" +
                                    "\t\t[\n" +
                                    "\t\t\t\"string\"\n" +
                                    "\t\t]\n" +
                                    "\t]\n" +
                                    "}";
    private static String jsonObjSLN = jsonObj.replace("\n", "");

}
