package com.kttdevelopment.myanimelist.property;

public interface Listing {

    int getScore();

    long getUpdatedAt();

    int getPriority();

    String[] getTags();

    String getComments();

}
