package com.kttdevelopment.myanimelist.anime.property.time;

public enum DayOfWeek {

    Sunday      ("sunday"),
    Monday      ("monday"),
    Tuesday     ("tuesday"),
    Wednesday   ("wednesday"),
    Thursday    ("thursday"),
    Friday      ("friday"),
    Saturday    ("saturday");

    private final String field;

    DayOfWeek(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static DayOfWeek asEnum(final String string){
        for(final DayOfWeek value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "DayOfWeek{" +
               "field='" + field + '\'' +
               '}';
    }
}
