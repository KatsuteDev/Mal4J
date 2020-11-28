package com.kttdevelopment.myanimelist.property;

public enum RankingType {

    All         ("all"),
    @SuppressWarnings("SpellCheckingInspection")
    ByPopularity("bypopularity"),
    Favorite    ("favorite");

    private final String field;

    RankingType(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static RankingType asEnum(final String string){
        for(final RankingType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
