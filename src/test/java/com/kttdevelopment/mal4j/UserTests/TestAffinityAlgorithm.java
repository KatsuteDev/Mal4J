package com.kttdevelopment.mal4j.UserTests;

import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.user.property.AffinityAlgorithm;
import com.kttdevelopment.mal4j.user.property.MyAnimeListAffinityAlgorithm;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestAffinityAlgorithm {

    private static final AffinityAlgorithm algorithm = new MyAnimeListAffinityAlgorithm();

    @ParameterizedTest(name="[{index}] {0} & {1} = {2}")
    @MethodSource("testProvider")
    public void testAffinity(final int[] a_scores, final int[] b_scores, float expected){
        Assertions.assertEquals(expected, round(algorithm.getAffinity(a_scores, b_scores), 3), Workflow.errorSupplier("Expected " + Arrays.toString(a_scores) + " and " + Arrays.toString(b_scores) + " to have an affinity of " + expected));
    }

    @Test
    public void testEmptyAffinity(){
        Assertions.assertEquals(0, algorithm.getAffinity(new int[0], new int[0]), Workflow.errorSupplier("Expected an empty affinity to be 0"));
    }

    @Test
    public void testMismatchAffinity(){
        Assertions.assertThrows(IllegalStateException.class, () -> algorithm.getAffinity(new int[0], new int[1]), Workflow.errorSupplier("Expected mismatch affinity arrays to throw an exception"));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> testProvider(){
        return new TestProvider.ObjectStream()
            // edge cases
            .add(new int[]{10, 10, 10, 10, 10, 10}, new int[]{10, 10, 10, 10, 10, 10}, 1f)
            .add(new int[]{10, 10, 10, 10, 10, 10}, new int[]{1, 1, 1, 1, 1, 1}, 1f)
            // 100%
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 10, 10, 10, 10, 9}, 1f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 10, 10, 10, 10, 1}, 1f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 10, 10, 10, 10, 1}, 1f)
            // +
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 10, 10, 10, 1, 1}, .632f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 10, 10, 1, 1, 1}, .447f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 10, 1, 1, 1, 1}, .316f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{10, 1, 1, 1, 1, 1}, .2f)
            // -
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{1, 1, 1, 1, 1, 10}, -1f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{1, 1, 1, 1, 10, 10}, -.632f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{1, 1, 1, 10, 10, 10}, -.447f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{1, 1, 10, 10, 10, 10}, -.316f)
            .add(new int[]{10, 10, 10, 10, 10, 9}, new int[]{1, 10, 10, 10, 10, 10}, -.2f)
            .stream();
    }

    @SuppressWarnings("SameParameterValue")
    private static float round(float num, int places){
        float scale = (float) Math.pow(10, places);
        return Math.round(num * scale) / scale;
    }

}
