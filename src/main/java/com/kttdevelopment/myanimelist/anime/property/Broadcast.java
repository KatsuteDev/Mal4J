package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.DayOfWeek;
import com.kttdevelopment.myanimelist.property.Time;

public abstract class Broadcast {

    public abstract DayOfWeek getDayOfWeek();

    public abstract Time getStartTime();

}
