package com.kttdevelopment.myanimelist.forum.property;

public enum ForumSort {

    Recent("recent");

    private final String field;

    ForumSort(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static ForumSort asEnum(final String string){
        for(final ForumSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "ForumSort{" +
               "field='" + field + '\'' +
               '}';
    }

}
