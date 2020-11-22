package com.kttdevelopment.myanimelist.anime.property.time;

/**
 * Represents a day of the week.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum DayOfWeek {

    Sunday("sunday"),
    Monday("monday"),
    Tuesday("tuesday"),
    Wednesday("wednesday"),
    Thursday("thursday"),
    Friday("friday"),
    Saturday("saturday");

    private final String dayOfWeek;

    DayOfWeek(final String dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Returns the type field name
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String getDayOfWeek(){
        return dayOfWeek;
    }

    @Override
    public String toString(){
        return "DayOfWeek{" +
               "dayOfWeek='" + dayOfWeek + '\'' +
               '}';
    }
}
