package com.kttdevelopment.myanimelist.anime.property.time;

/**
 * Represents a day of the week.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
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

    /**
     * Returns the field name.
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field as an enum.
     *
     * @param string string
     * @return enum
     *
     * @since 1.0.0
     */
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
