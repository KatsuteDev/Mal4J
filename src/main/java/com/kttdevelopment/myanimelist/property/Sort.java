package com.kttdevelopment.myanimelist.property;

public enum Sort {

    Score       ("list_score"),
    UpdatedAt   ("updated_at");

    private final String field;

    Sort(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static Sort asEnum(final String string){
        for(final Sort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "Sort{" +
               "field='" + field + '\'' +
               '}';
    }

}
