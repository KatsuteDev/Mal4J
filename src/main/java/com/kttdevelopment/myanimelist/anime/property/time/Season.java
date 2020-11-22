package com.kttdevelopment.myanimelist.anime.property.time;

import java.util.Arrays;

/**
 * Represents a season.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum Season {

    Winter("winter", new String[]{"January", "February", "March"}),
    Spring("spring", new String[]{"April", "May", "June"}),
    Summer("summer", new String[]{"July", "August", "September"}),
    Fall("fall", new String[]{"October", "November", "December"});

    private final String season;
    private final String[] months;

    Season(final String season, final String[] months){
        this.season = season;
        this.months = months;
    }

    /**
     * Returns season field name.
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String getSeason(){
        return season;
    }

    /**
     * Returns season months.
     *
     * @return season months
     *
     * @since 1.0.0
     */
    public final String[] getMonths(){
        return months;
    }

    @Override
    public String toString(){
        return "Season{" +
               "season='" + season + '\'' +
               ", months=" + Arrays.toString(months) +
               '}';
    }
}
