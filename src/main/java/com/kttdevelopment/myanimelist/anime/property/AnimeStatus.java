package com.kttdevelopment.myanimelist.anime.property;

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

    public final String field(){
        return field;
    }

    public static AnimeStatus asEnum(final String string){
        for(final AnimeStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
