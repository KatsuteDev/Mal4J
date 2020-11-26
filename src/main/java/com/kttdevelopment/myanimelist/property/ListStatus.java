package com.kttdevelopment.myanimelist.property;

public interface ListStatus<Status extends Enum<?>> {

    Status getStatus();

    int getScore();

    long getStartDate();

    long getFinishDate();

    int getPriority();

    String[] getTags();

    String getComments();

    long getUpdatedAt();

}
