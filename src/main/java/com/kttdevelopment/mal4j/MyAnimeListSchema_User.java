/*
 * Copyright (C) 2021 Ktt Development
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.Json.JsonObject;
import com.kttdevelopment.mal4j.query.UserAnimeListQuery;
import com.kttdevelopment.mal4j.query.UserMangaListQuery;
import com.kttdevelopment.mal4j.user.User;
import com.kttdevelopment.mal4j.user.UserAnimeStatistics;

import java.util.Date;

abstract class MyAnimeListSchema_User extends MyAnimeListSchema {
    
    static User asUser(final MyAnimeList mal, final JsonObject schema){
        return new User() {

            private final Long id           = requireNonNull(() -> schema.getLong("id"));
            private final String name       = requireNonNull(() -> schema.getString("name"));
            private final String picture    = requireNonNull(() -> schema.getString("picture"));
            private final String gender     = requireNonNull(() -> schema.getString("gender"));
            private final Long birthday     = requireNonNull(() -> parseDate(schema.getString("birthday")));
            private final String location   = requireNonNull(() -> schema.getString("location"));
            private final Long joinedAt     = requireNonNull(() -> parseISO8601(schema.getString("joined_at")));
            private final UserAnimeStatistics animeStatistics
                                            = requireNonNull(() -> asUserAnimeStatistics(mal, schema.getJsonObject("anime_statistics")));
            private final String timezone   = requireNonNull(() -> schema.getString("time_zone"));
            private final Boolean supporter = requireNonNull(() -> schema.getBoolean("is_supporter"));

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getName(){
                return name;
            }

            @Override
            public final String getPictureURL(){
                return picture;
            }

            @Override
            public final String getGender(){
                return gender;
            }

            @Override
            public final Date getBirthday(){
                return birthday == null ? null : new Date(birthday);
            }

            @Override
            public final String getLocation(){
                return location;
            }

            @Override
            public final Date getJoinedAt(){
                return joinedAt == null ? null : new Date(joinedAt);
            }

            @Override
            public final Long getJoinedAtEpochMillis(){
                return joinedAt;
            }

            @Override
            public final UserAnimeStatistics getAnimeStatistics(){
                return animeStatistics;
            }

            @Override
            public final String getTimeZone(){
                return timezone;
            }

            @Override
            public final Boolean isSupporter(){
                return supporter;
            }

            // additional methods

            @Override
            public final UserAnimeListQuery getUserAnimeListing(){
                return mal.getUserAnimeListing(name);
            }

            @Override
            public final UserMangaListQuery getUserMangaListing(){
                return mal.getUserMangaListing(name);
            }

            @Override
            public final String toString(){
                return "User{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", picture='" + picture + '\'' +
                       ", gender='" + gender + '\'' +
                       ", birthday=" + birthday +
                       ", location='" + location + '\'' +
                       ", joinedAt=" + joinedAt +
                       ", animeStatistics=" + animeStatistics +
                       ", timezone='" + timezone + '\'' +
                       ", supporter=" + supporter +
                       '}';
            }

        };
    }

    static UserAnimeStatistics asUserAnimeStatistics(final MyAnimeList mal, final JsonObject schema){
        return new UserAnimeStatistics() {

            private final Integer watching          = requireNonNull(() -> schema.getInt("num_items_watching"));
            private final Integer completed         = requireNonNull(() -> schema.getInt("num_items_completed"));
            private final Integer onHold            = requireNonNull(() -> schema.getInt("num_items_on_hold"));
            private final Integer dropped           = requireNonNull(() -> schema.getInt("num_items_dropped"));
            private final Integer planToWatch       = requireNonNull(() -> schema.getInt("num_items_plan_to_watch"));
            private final Integer items             = requireNonNull(() -> schema.getInt("num_items"));
            private final Float daysWatching        = requireNonNull(() -> schema.getFloat("num_days_watching"));
            private final Float daysCompleted       = requireNonNull(() -> schema.getFloat("num_days_completed"));
            private final Float daysOnHold          = requireNonNull(() -> schema.getFloat("num_days_on_hold"));
            private final Float daysDropped         = requireNonNull(() -> schema.getFloat("num_days_dropped"));
            private final Float days                = requireNonNull(() -> schema.getFloat("num_days"));
            private final Integer episodesWatched   = requireNonNull(() -> schema.getInt("num_episodes"));
            @SuppressWarnings("SpellCheckingInspection")
            private final Integer timesRewatched    = requireNonNull(() -> schema.getInt("num_times_rewatched"));
            private final Float meanScore           = requireNonNull(() -> schema.getFloat("mean_score"));

            // API methods

            @Override
            public final Integer getItemsWatching(){
                return watching;
            }

            @Override
            public final Integer getItemsCompleted(){
                return completed;
            }

            @Override
            public final Integer getItemsOnHold(){
                return onHold;
            }

            @Override
            public final Integer getItemsDropped(){
                return dropped;
            }

            @Override
            public final Integer getItemsPlanToWatch(){
                return planToWatch;
            }

            @Override
            public final Integer getItems(){
                return items;
            }

            @Override
            public final Float  getDaysWatched(){
                return days;
            }

            @Override
            public final Float  getDaysWatching(){
                return daysWatching;
            }

            @Override
            public final Float  getDaysCompleted(){
                return daysCompleted;
            }

            @Override
            public final Float  getDaysOnHold(){
                return daysOnHold;
            }

            @Override
            public final Float  getDaysDropped(){
                return daysDropped;
            }

            @Override
            public final Float  getDays(){
                return days;
            }

            @Override
            public final Integer getEpisodes(){
                return episodesWatched;
            }

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final Integer getTimesRewatched(){
                return timesRewatched;
            }

            @Override
            public final Float  getMeanScore(){
                return meanScore;
            }

            // additional methods

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final String toString(){
                return "UserAnimeStatistics{" +
                       "watching=" + watching +
                       ", completed=" + completed +
                       ", onHold=" + onHold +
                       ", dropped=" + dropped +
                       ", planToWatch=" + planToWatch +
                       ", items=" + items +
                       ", daysWatching=" + daysWatching +
                       ", daysCompleted=" + daysCompleted +
                       ", daysOnHold=" + daysOnHold +
                       ", daysDropped=" + daysDropped +
                       ", days=" + days +
                       ", episodesWatched=" + episodesWatched +
                       ", timesRewatched=" + timesRewatched +
                       ", meanScore=" + meanScore +
                       '}';
            }

        };
    }
    
}
