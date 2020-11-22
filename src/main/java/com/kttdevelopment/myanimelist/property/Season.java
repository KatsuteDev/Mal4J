package com.kttdevelopment.myanimelist.property;

import java.util.Arrays;

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

    public final String getSeason(){
        return season;
    }

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
