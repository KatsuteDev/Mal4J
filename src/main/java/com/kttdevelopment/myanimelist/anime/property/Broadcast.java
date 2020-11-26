package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.property.time.DayOfWeek;
import com.kttdevelopment.myanimelist.anime.property.time.Time;

public abstract class Broadcast {

    public abstract DayOfWeek getDayOfWeek();

    public abstract Time getStartTime();

}
