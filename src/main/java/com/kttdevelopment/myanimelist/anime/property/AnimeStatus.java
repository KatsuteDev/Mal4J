package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents an Anime status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeStatus {

    Watching    ("watching"),
    Completed   ("completed"),
    OnHold      ("on_hold"),
    Dropped     ("dropped"),
    PlanToWatch ("plan_to_watch");

    private final String field;

    AnimeStatus(final String field){
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
    public static AnimeStatus asEnum(final String string){
        for(final AnimeStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
