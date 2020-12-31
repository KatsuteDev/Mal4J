package com.kttdevelopment.myanimelist.query.property;

/**
 * Represents a Anime listings rewatch value.
 *
 * @see com.kttdevelopment.myanimelist.query.AnimeListUpdate#rewatchValue(RewatchValue)
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public enum RewatchValue {

    None        (0),
    VeryLow     (1),
    Low         (2),
    Medium      (3),
    High        (4),
    VeryHigh    (5);

    private final int value;

    RewatchValue(final int value){
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
    public static RewatchValue asEnum(final Integer num){
        for(final RewatchValue value : values())
            if(value.value == num)
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }


}
