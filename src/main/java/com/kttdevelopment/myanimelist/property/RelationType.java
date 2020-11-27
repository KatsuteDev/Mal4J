package com.kttdevelopment.myanimelist.property;

public enum RelationType {

    Sequel              ("sequel"),
    Prequel             ("prequel"),
    AlternativeSetting  ("alternative_setting"),
    AlternativeVersion  ("alternative_version"),
    SideStory           ("side_story"),
    ParentStory         ("parent_story"),
    Summary             ("summary"),
    FullStory           ("full_story");

    private final String field;

    RelationType(String field) {
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static RelationType asEnum(final String string){
        for(final RelationType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString() {
        return "RelationType{" +
                "field='" + field + '\'' +
                '}';
    }
}
