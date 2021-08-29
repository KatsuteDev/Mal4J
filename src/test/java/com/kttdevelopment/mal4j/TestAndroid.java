package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.apache.bcel.classfile.*;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestAndroid {

    private static final int MIN_SDK = 3;
    private static final int MAX_SDK = 30;

    private static Stream<Integer> versionProvider(){
        return IntStream.rangeClosed(MIN_SDK, MAX_SDK).boxed();
    }

    @ParameterizedTest(name="[{index}] Android {0}")
    @MethodSource("versionProvider")
    public void testAndroid(final Integer i) throws IOException{
        Assumptions.assumeTrue(i != 20, "Android API 20 is only for wear devices");

        final File jar = new File("android-sdk/android-" + i + "/android.jar");

        final Map<String,JavaClass> classes = new HashMap<>();
        final JarFile jarFile = new JarFile(jar);
        final Enumeration<JarEntry> entries = jarFile.entries();

        while(entries.hasMoreElements()){
            final JarEntry entry = entries.nextElement();
            final String name = entry.getName();
            if(!name.endsWith(".class")) continue;

            final JavaClass jclass = new ClassParser(jarFile.getInputStream(jarFile.getEntry(name)), name).parse();

            classes.put(name.replace('/', '.').substring(0, name.length() - 6), jclass);
        }

        //

        Assumptions.assumeTrue(doesNotThrow(() -> getField(classes, "java.net.HttpURLConnection", "method")), Workflow.warningSupplier("Failed to use HttpURLConnection on Android " + i));
    }

    private static <T> boolean doesNotThrow(final ThrowingSupplier<T> supplier){
        try{
            supplier.get();
            return true;
        }catch(Throwable throwable){
            return false;
        }
    }

    @SuppressWarnings({"SpellCheckingInspection", "SameParameterValue"})
    private static Field getField(final Map<String,JavaClass> classes, final String classname, final String field) throws NoSuchFieldException, ClassNotFoundException{
        final JavaClass jclass = classes.get(classname);

        if(jclass == null)
            throw new ClassNotFoundException(classname);
        for(final Field jclassField : jclass.getFields())
            if(jclassField.getName().equals(field))
                return jclassField;
        throw new NoSuchFieldException("No such field '" + field + "' in class " + jclass.getClassName());
    }

}