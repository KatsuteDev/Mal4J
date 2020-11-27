package com.kttdevelopment.myanimelist.anime.property.time;

import java.util.Arrays;

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

    public final String[] getMonths(){
        return months;
    }

    public static Season fromMonth(final String month){
        for(final Season value : values())
            for(final String m :value.getMonths())
                if(m.equalsIgnoreCase(month))
                    return value;
        return null;
    }

    public final String field(){
        return field;
    }

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
