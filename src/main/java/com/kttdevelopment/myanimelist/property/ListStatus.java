package com.kttdevelopment.myanimelist.property;

public interface ListStatus<Status extends Enum<?>> {

    Status getStatus();

    int getScore();

    int getPriority();

    String[] getTags();

    String getComments();

}
