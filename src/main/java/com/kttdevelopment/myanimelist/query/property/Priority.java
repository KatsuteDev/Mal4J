package com.kttdevelopment.myanimelist.query.property;

/**
 * Represents a listing priority.
 *
 * @see com.kttdevelopment.myanimelist.query.AnimeListUpdate#priority(Priority)
 * @see com.kttdevelopment.myanimelist.query.MangaListUpdate#priority(Priority)
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum Priority {

    Low     (0),
    Medium  (1),
    High    (2);

    private final int value;

    Priority(final int value){
        this.value = value;
    }

    /**
     * Returns the json field value.
     *
     * @return json field value
     *
     * @since 1.0.0
     */
    public final int value(){
        return value;
    }

    /**
     * Returns the field value as an enum.
     *
     * @param num json field value
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static Priority asEnum(final Integer num){
        for(final Priority value : values())
            if(value.value == num)
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }


}
