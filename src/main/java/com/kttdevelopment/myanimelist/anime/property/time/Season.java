package com.kttdevelopment.myanimelist.anime.property.time;

import java.util.Arrays;

/**
 * Represents an airing season.
 *
 * @see com.kttdevelopment.myanimelist.anime.property.StartSeason
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum Season {

    Winter  ("winter"   , new String[]{"January", "February", "March"}),
    Spring  ("spring"   , new String[]{"April", "May", "June"}),
    Summer  ("summer"   , new String[]{"July", "August", "September"}),
    Fall    ("fall"     , new String[]{"October", "November", "December"});

    private final String field;
    private final String[] months;

    Season(final String field, final String[] months){
        this.field = field;
        this.months = months;
    }

    /**
     * Returns the months that the season represents.
     *
     * @return months
     *
     * @since 1.0.0
     */
    public final String[] getMonths(){
        return months;
    }

    /**
     * Returns the season that a month is a part of.
     *
     * @param month month
     * @return season the month is from
     *
     * @since 1.0.0
     */
    public static Season fromMonth(final String month){
        for(final Season value : values())
            for(final String m :value.getMonths())
                if(m.equalsIgnoreCase(month))
                    return value;
        return null;
    }

    /**
     * Returns the json field name.
     *
     * @return json field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field name as an enum.
     *
     * @param string json field name
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static Season asEnum(final String string){
        for(final Season value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "Season{" +
               "field='" + field + '\'' +
               ", months=" + Arrays.toString(months) +
               '}';
    }
}
