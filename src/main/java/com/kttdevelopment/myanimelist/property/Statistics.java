package com.kttdevelopment.myanimelist.property;

@SuppressWarnings("rawtypes")
public abstract class Statistics<E extends Enum> {

    public abstract int getCount(final E type);

    public abstract int getUserCount();

}
