package com.kttdevelopment.myanimelist.property;

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
