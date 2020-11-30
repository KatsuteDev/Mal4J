package com.kttdevelopment.myanimelist.forum.property;

/**
 * Represents the forum sort type.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum ForumSort {

    Recent("recent");

    private final String field;

    @SuppressWarnings("SameParameterValue")
    ForumSort(final String field){
        this.field = field;
    }

    /**
     * Returns the json field name.
     *
     * @return json field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field name as an enum.
     *
     * @param string json field name
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static ForumSort asEnum(final String string){
        for(final ForumSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
