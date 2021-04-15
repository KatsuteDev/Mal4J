package com.kttdevelopment.mal4j.user.property;

@FunctionalInterface
public interface AffinityAlgorithm {

    float getAffinity(int[] a_scores, int[] b_scores);

}
