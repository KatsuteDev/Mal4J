package com.kttdevelopment.myanimelist.property;

public abstract class Listing implements ListStatus{

    public abstract int getPriority();

    public abstract String[] getTags();

    public abstract String getComments();

}
