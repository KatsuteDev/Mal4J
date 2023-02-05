/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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
package dev.katsute.mal4j;

import dev.katsute.mal4j.Json.JsonObject;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.AnimeListStatus;
import dev.katsute.mal4j.manga.Manga;
import dev.katsute.mal4j.manga.MangaListStatus;
import dev.katsute.mal4j.property.ExperimentalFeature;
import dev.katsute.mal4j.query.UserAnimeListQuery;
import dev.katsute.mal4j.query.UserMangaListQuery;
import dev.katsute.mal4j.user.User;
import dev.katsute.mal4j.user.UserAnimeStatistics;
import dev.katsute.mal4j.user.property.*;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_User extends MyAnimeListSchema {

    static User asUser(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new User() {

            private final Long id           = schema.getLong("id");
            private final String name       = schema.getString("name");
            private final String picture    = schema.getString("picture");
            private final String gender     = schema.getString("gender");
            private final Long birthday     = parseDate(schema.getString("birthday"));
            private final String location   = schema.getString("location");
            private final Long joinedAt     = parseISO8601(schema.getString("joined_at"));
            private final UserAnimeStatistics animeStatistics
                                            = asUserAnimeStatistics(mal, schema.getJsonObject("anime_statistics"));
            private final String timezone   = schema.getString("time_zone");
            private final Boolean supporter = schema.getBoolean("is_supporter");

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
            public final AnimeAffinity getAnimeAffinity(){
                return getAnimeAffinity(mal.getAuthenticatedUser(Fields.NO_FIELDS).getName());
            }

            @Override
            public final void getAnimeAffinity(final Consumer<AnimeAffinity> callback){
                new Thread(() -> callback.accept(getAnimeAffinity())).start();
            }

            @Override
            public final AnimeAffinity getAnimeAffinity(final User user){
                return getAnimeAffinity(Objects.requireNonNull(user, "User can not be null").getName());
            }

            @Override
            public final void getAnimeAffinity(final User user, final Consumer<AnimeAffinity> callback){
                new Thread(() -> callback.accept(getAnimeAffinity(user))).start();
            }

            @Override
            public final AnimeAffinity getAnimeAffinity(final String username){
                ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.AFFINITY);
                Objects.requireNonNull(username, "Username can not be null");

                final Map<Long,AnimeListStatus> selfListings = new HashMap<>();
                getUserAnimeListing()
                    .includeNSFW()
                    .withFields(Fields.Anime.list_status)
                    .withLimit(1000)
                    .searchAll()
                    .forEachRemaining(e -> {
                        if(e.getScore() != null && e.getScore() > 0) // if rated
                            selfListings.put(e.getAnime().getID(), e);
                    });

                final Map<Long,AnimeListStatus> otherListings = new HashMap<>();

                if(mal.getAuthenticatedUser().getName().equals(username))
                    otherListings.putAll(selfListings);
                else
                    mal.getUserAnimeListing(username)
                        .includeNSFW()
                        .withFields(Fields.Anime.list_status)
                        .withLimit(1000)
                        .searchAll()
                        .forEachRemaining(e -> {
                        final Long id = e.getAnime().getID();
                        if(e.getScore() != null && e.getScore() > 0 && selfListings.containsKey(id)) // if rated & shared
                            otherListings.put(id, e);
                    });

                final int len = otherListings.size();
                final Anime[] shared        = new Anime[len];
                final int[] selfScores      = new int[len];
                final int[] otherScores     = new int[len];

                int index = 0;
                for(final Map.Entry<Long,AnimeListStatus> otherListing : otherListings.entrySet()){
                    final AnimeListStatus selfListing = selfListings.get(otherListing.getKey());
                    shared[index]       = selfListing.getAnime();
                    selfScores[index]   = selfListing.getScore();
                    otherScores[index]  = otherListing.getValue().getScore();
                    index++;
                }

                return new AnimeAffinity() {

                    private final int sharedCount = len;
                    private final Anime[] sharedPreviews = shared;
                    private final int[] a_scores = selfScores;
                    private final int[] b_scores = otherScores;

                    @Override
                    public final Anime[] getShared(){
                        return Arrays.copyOf(sharedPreviews, sharedCount);
                    }

                    @Override
                    public final int getSharedCount(){
                        return sharedCount;
                    }

                    @Override
                    public final float getAffinity(){
                        return getAffinity(new MyAnimeListAffinityAlgorithm());
                    }

                    @Override
                    public final float getAffinity(final AffinityAlgorithm algorithm){
                        return Objects.requireNonNull(algorithm, "Affinity algorithm can not be null").getAffinity(Arrays.copyOf(a_scores, sharedCount), Arrays.copyOf(b_scores, sharedCount));
                    }

                    @Override
                    public final String toString(){
                        return "AnimeAffinity{" +
                               "sharedCount=" + sharedCount +
                               ", selfScores=" + Arrays.toString(a_scores) +
                               ", otherScores=" + Arrays.toString(b_scores) +
                               '}';
                    }

                };
            }

            @Override
            public final void getAnimeAffinity(final String username, final Consumer<AnimeAffinity> callback){
                new Thread(() -> callback.accept(getAnimeAffinity(username))).start();
            }

            @Override
            public final MangaAffinity getMangaAffinity(){
                return getMangaAffinity(mal.getAuthenticatedUser(Fields.NO_FIELDS).getName());
            }

            @Override
            public final void getMangaAffinity(final Consumer<MangaAffinity> callback){
                new Thread(() -> callback.accept(getMangaAffinity())).start();
            }

            @Override
            public final MangaAffinity getMangaAffinity(final User user){
                return getMangaAffinity(Objects.requireNonNull(user, "User can not be null").getName());
            }

            @Override
            public final void getMangaAffinity(final User user, final Consumer<MangaAffinity> callback){
                new Thread(() -> callback.accept(getMangaAffinity(user))).start();
            }

            @Override
            public final MangaAffinity getMangaAffinity(final String username){
                ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.AFFINITY);
                Objects.requireNonNull(username, "Username can not be null");

                final Map<Long,MangaListStatus> selfListings = new HashMap<>();
                getUserMangaListing()
                    .includeNSFW()
                    .withFields(Fields.Manga.list_status)
                    .withLimit(1000)
                    .searchAll()
                    .forEachRemaining(e -> {
                        if(e.getScore() != null && e.getScore() > 0) // if rated
                            selfListings.put(e.getManga().getID(), e);
                    });

                final Map<Long,MangaListStatus> otherListings = new HashMap<>();

                if(mal.getAuthenticatedUser().getName().equals(username))
                    otherListings.putAll(selfListings);
                else
                    mal.getUserMangaListing(username)
                        .includeNSFW()
                        .withFields(Fields.Manga.list_status)
                        .withLimit(1000)
                        .searchAll()
                        .forEachRemaining(e -> {
                        final Long id = e.getManga().getID();
                        if(e.getScore() != null && e.getScore() > 0 && selfListings.containsKey(id)) // if rated & shared
                            otherListings.put(id, e);
                    });

                final int len = otherListings.size();
                final Manga[] shared        = new Manga[len];
                final int[] selfScores      = new int[len];
                final int[] otherScores     = new int[len];

                int index = 0;
                for(final Map.Entry<Long,MangaListStatus> otherListing : otherListings.entrySet()){
                    final MangaListStatus selfListing = selfListings.get(otherListing.getKey());
                    shared[index]       = selfListing.getManga();
                    selfScores[index]   = selfListing.getScore();
                    otherScores[index]  = otherListing.getValue().getScore();
                    index++;
                }

                return new MangaAffinity() {

                    private final int sharedCount = len;
                    private final Manga[] sharedPreviews = shared;
                    private final int[] a_scores = selfScores;
                    private final int[] b_scores = otherScores;

                    @Override
                    public final Manga[] getShared(){
                        return Arrays.copyOf(sharedPreviews, sharedCount);
                    }

                    @Override
                    public final int getSharedCount(){
                        return sharedCount;
                    }

                    @Override
                    public final float getAffinity(){
                        return getAffinity(new MyAnimeListAffinityAlgorithm());
                    }

                    @Override
                    public final float getAffinity(final AffinityAlgorithm algorithm){
                        return Objects.requireNonNull(algorithm, "Affinity algorithm can not be null").getAffinity(Arrays.copyOf(a_scores, sharedCount), Arrays.copyOf(b_scores, sharedCount));
                    }

                    @Override
                    public final String toString(){
                        return "MangaAffinity{" +
                               "sharedCount=" + sharedCount +
                               ", selfScores=" + Arrays.toString(a_scores) +
                               ", otherScores=" + Arrays.toString(b_scores) +
                               '}';
                    }

                };
            }

            @Override
            public final void getMangaAffinity(final String username, final Consumer<MangaAffinity> callback){
                new Thread(() -> callback.accept(getMangaAffinity(username))).start();
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
        return schema == null ? null : new UserAnimeStatistics() {

            private final Integer watching          = schema.getInt("num_items_watching");
            private final Integer completed         = schema.getInt("num_items_completed");
            private final Integer onHold            = schema.getInt("num_items_on_hold");
            private final Integer dropped           = schema.getInt("num_items_dropped");
            private final Integer planToWatch       = schema.getInt("num_items_plan_to_watch");
            private final Integer items             = schema.getInt("num_items");
            private final Float daysWatching        = schema.getFloat("num_days_watching");
            private final Float daysCompleted       = schema.getFloat("num_days_completed");
            private final Float daysOnHold          = schema.getFloat("num_days_on_hold");
            private final Float daysDropped         = schema.getFloat("num_days_dropped");
            private final Float days                = schema.getFloat("num_days");
            private final Integer episodesWatched   = schema.getInt("num_episodes");
            @SuppressWarnings("SpellCheckingInspection")
            private final Integer timesRewatched    = schema.getInt("num_times_rewatched");
            private final Float meanScore           = schema.getFloat("mean_score");

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
