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

import com.kttdevelopment.mal4j.anime.*;
import com.kttdevelopment.mal4j.anime.property.*;
import com.kttdevelopment.mal4j.anime.property.time.*;
import com.kttdevelopment.mal4j.forum.*;
import com.kttdevelopment.mal4j.forum.property.*;
import com.kttdevelopment.mal4j.manga.*;
import com.kttdevelopment.mal4j.manga.property.*;
import com.kttdevelopment.mal4j.property.NSFW;
import com.kttdevelopment.mal4j.property.*;
import com.kttdevelopment.mal4j.query.*;
import com.kttdevelopment.mal4j.user.UserAnimeStatistics;

import java.lang.reflect.Array;
import java.text.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.kttdevelopment.mal4j.Json.*;

/**
 * Represents the MyAnimeList API response as an object.
 *
 * @see MyAnimeListImpl
 */
@SuppressWarnings({"unused","unchecked","ConstantConditions"})
abstract class MyAnimeListAPIResponseMapping {

    static abstract class Anime {

        static AnimeStatistics asAnimeStatistics(final MyAnimeList mal, final JsonObject schema){
            return new AnimeStatistics() {

                private final Integer   watching    = requireNonNull(() -> schema.getJsonObject("status").getInt("watching")),
                                        completed   = requireNonNull(() -> schema.getJsonObject("status").getInt("completed")),
                                        onHold      = requireNonNull(() -> schema.getJsonObject("status").getInt("on_hold")),
                                        dropped     = requireNonNull(() -> schema.getJsonObject("status").getInt("dropped")),
                                        planToWatch = requireNonNull(() -> schema.getJsonObject("status").getInt("plan_to_watch")),
                                        userCount   = requireNonNull(() -> schema.getInt("num_list_users"));

                // API methods

                @Override
                public final Integer getWatching() {
                    return watching;
                }

                @Override
                public final Integer getCompleted() {
                    return completed;
                }

                @Override
                public final Integer getOnHold() {
                    return onHold;
                }

                @Override
                public final Integer getDropped() {
                    return dropped;
                }

                @Override
                public final Integer getPlanToWatch() {
                    return planToWatch;
                }

                @Override
                public final Integer getUserCount() {
                    return userCount;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "AnimeStatistics{" +
                           "watching=" + watching +
                           ", completed=" + completed +
                           ", onHold=" + onHold +
                           ", dropped=" + dropped +
                           ", planToWatch=" + planToWatch +
                           ", userCount=" + userCount +
                           '}';
                }

            };
        }

        static Broadcast asBroadcast(final MyAnimeList mal, final JsonObject schema){
            return new Broadcast() {

                private final DayOfWeek dayOfWeek   = requireNonNull(() -> DayOfWeek.asEnum(schema.getString("day_of_the_week")));
                private final Time time             = requireNonNull(() -> asTime(schema.getString("start_time")));

                // API methods

                @Override
                public final DayOfWeek getDayOfWeek() {
                    return dayOfWeek;
                }

                @Override
                public final Time getStartTime() {
                    return time;
                }

                // additional methods

                @Override
                public String toString(){
                    return "Broadcast{" +
                           "dayOfWeek=" + dayOfWeek +
                           ", time=" + time +
                           '}';
                }

            };
        }

        static StartSeason asStartSeason(final MyAnimeList mal, final JsonObject schema){
            return new StartSeason() {

                private final Integer year  = requireNonNull(() -> schema.getInt("year"));
                private final Season season = requireNonNull(() -> Season.asEnum(schema.getString("season")));

                // API methods

                @Override
                public final Integer getYear() {
                    return year;
                }

                @Override
                public final Season getSeason() {
                    return season;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "StartSeason{" +
                           "year=" + year +
                           ", season=" + season +
                           '}';
                }

            };
        }

        static Studio asStudio(final MyAnimeList mal, final JsonObject schema){
            return new Studio() {

                private final Long id       = requireNonNull(() -> schema.getLong("id"));
                private final String name   = requireNonNull(() -> schema.getString("name"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getName() {
                    return name;
                }

                // additional methods


                @Override
                public final String toString(){
                    return "Studio{" +
                           "id=" + id +
                           ", name='" + name + '\'' +
                           '}';
                }

            };
        }

        static OpeningTheme asOpeningTheme(final MyAnimeList mal, final JsonObject schema, final com.kttdevelopment.mal4j.anime.Anime anime){
            return new OpeningTheme() {

                private final Long id       = requireNonNull(() -> schema.getLong("id"));
                private final String text   = requireNonNull(() -> schema.getString("text"));

                // API methods

                @Override
                public final Long getID(){
                    return id;
                }

                @Override
                public final String getText(){
                    return text;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime(){
                    return anime;
                }

                @Override
                public final String toString(){
                    return "OpeningTheme{" +
                           "id=" + id +
                           ", text='" + text + '\'' +
                           '}';
                }

            };
        }

        static EndingTheme asEndingTheme(final MyAnimeList mal, final JsonObject schema, final com.kttdevelopment.mal4j.anime.Anime anime){
            return new EndingTheme() {

                private final Long id       = requireNonNull(() -> schema.getLong("id"));
                private final String text   = requireNonNull(() -> schema.getString("text"));

                // API methods

                @Override
                public final Long getID(){
                    return id;
                }

                @Override
                public final String getText(){
                    return text;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime(){
                    return anime;
                }

                @Override
                public final String toString(){
                    return "EndingTheme{" +
                           "id=" + id +
                           ", text='" + text + '\'' +
                           '}';
                }

            };
        }

        static com.kttdevelopment.mal4j.anime.Anime asAnime(final MyAnimeList mal, final JsonObject schema){
            return new com.kttdevelopment.mal4j.anime.Anime() {

                private final Long id               = requireNonNull(() -> schema.getLong("id"));
                private final String title          = requireNonNull(() -> schema.getString("title"));
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.getJsonObject("main_picture")));
                private final AlternativeTitles alternativeTitles
                                                    = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles")));
                private final Long startDate        = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long endDate          = requireNonNull(() -> parseDate(schema.getString("end_date")));
                private final String synopsis       = requireNonNull(() -> schema.getString("synopsis"));
                private final Float meanRating      = requireNonNull(() -> schema.getFloat("mean"));
                private final Integer rank          = requireNonNull(() -> schema.getInt("rank"));
                private final Integer popularity    = requireNonNull(() -> schema.getInt("popularity"));
                private final Integer usersListing  = requireNonNull(() -> schema.getInt("num_list_users"));
                private final Integer usersScoring  = requireNonNull(() -> schema.getInt("num_scoring_users"));
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.getString("nsfw")));
                private final Genre[] genres        = requireNonNull(() -> adaptList(schema.getJsonArray("genres"), g -> Genre.asEnum(g.getInt("id")), Genre.class));
                private final Long createdAt        = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final Long updatedAt        = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final AnimeType type        = requireNonNull(() -> AnimeType.asEnum(schema.getString("media_type")));
                private final AnimeAirStatus status = requireNonNull(() -> AnimeAirStatus.asEnum(schema.getString("status")));
                private final AnimeListStatus listStatus
                                                    = requireNonNull(() -> asAnimeListStatus(mal, schema.getJsonObject("my_list_status"), this));
                private final Integer episodes      = requireNonNull(() -> schema.getInt("num_episodes"));
                private final StartSeason startSeason
                                                    = requireNonNull(() -> asStartSeason(mal, schema.getJsonObject("start_season")));
                private final Broadcast broadcast   = requireNonNull(() -> asBroadcast(mal, schema.getJsonObject("broadcast")));
                private final AnimeSource source    = requireNonNull(() -> AnimeSource.asEnum(schema.getString("source")));
                private final Integer episodeLength = requireNonNull(() -> schema.getInt("average_episode_duration"));
                private final AnimeRating rating    = requireNonNull(() -> AnimeRating.asEnum(schema.getString("rating")));
                private final Studio[] studios      = requireNonNull(() -> adaptList(schema.getJsonArray("studios"), s -> asStudio(mal, s), Studio.class));
                private final Picture[] pictures    = requireNonNull(() -> adaptList(schema.getJsonArray("pictures"), p -> Common.asPicture(mal, p), Picture.class));
                private final String background     = requireNonNull(() -> schema.getString("background"));
                private final RelatedAnime[] relatedAnime
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("related_anime"), a -> asRelatedAnime(mal, a), RelatedAnime.class));
                private final RelatedManga[] relatedManga
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("related_manga"), m -> Manga.asRelatedManga(mal, m), RelatedManga.class));
                private final AnimeRecommendation[] recommendations
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("recommendations"), r -> asAnimeRecommendation(mal, r), AnimeRecommendation.class));
                private final AnimeStatistics statistics
                                                    = requireNonNull(() -> asAnimeStatistics(mal, schema.getJsonObject("statistics")));
                private final OpeningTheme[] openingThemes
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("opening_themes"), o -> asOpeningTheme(mal, o, this), OpeningTheme.class));
                private final EndingTheme[] endingThemes
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("ending_themes"), o -> asEndingTheme(mal, o, this), EndingTheme.class));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final Picture getMainPicture() {
                    return mainPicture;
                }

                @Override
                public final AlternativeTitles getAlternativeTitles() {
                    return alternativeTitles;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getEndDate() {
                    return endDate == null ? null : new Date(endDate);
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final Float  getMeanRating() {
                    return meanRating;
                }

                @Override
                public final Integer getRank() {
                    return rank;
                }

                @Override
                public final Integer getPopularity() {
                    return popularity;
                }

                @Override
                public final Integer getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final Integer getUserScoringCount() {
                    return usersScoring;
                }

                @Override
                public final NSFW getNSFW() {
                    return nsfw;
                }

                @Override
                public final Genre[] getGenres() {
                    return genres;
                }

                @Override
                public final Date getCreatedAt() {
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis(){
                    return createdAt;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final AnimeType getType() {
                    return type;
                }

                @Override
                public final AnimeAirStatus getStatus() {
                    return status;
                }

                @Override
                public final AnimeListStatus getListStatus() {
                    return listStatus;
                }

                @Override
                public final Integer getEpisodes() {
                    return episodes;
                }

                @Override
                public final StartSeason getStartSeason() {
                    return startSeason;
                }

                @Override
                public final Broadcast getBroadcast() {
                    return broadcast;
                }

                @Override
                public final AnimeSource getSource() {
                    return source;
                }

                @Override
                public final Integer getAverageEpisodeLength() {
                    return episodeLength;
                }

                @Override
                public final AnimeRating getRating() {
                    return rating;
                }

                @Override
                public final Studio[] getStudios() {
                    return studios;
                }

                @Override
                public final Picture[] getPictures() {
                    return pictures;
                }

                @Override
                public final String getBackground() {
                    return background;
                }

                @Override
                public final RelatedAnime[] getRelatedAnime() {
                    return relatedAnime;
                }

                @Override
                public final RelatedManga[] getRelatedManga() {
                    return relatedManga;
                }

                @Override
                public final AnimeRecommendation[] getRecommendations() {
                    return recommendations;
                }

                @Override
                public final AnimeStatistics getStatistics() {
                    return statistics;
                }

                @Override
                public final OpeningTheme[] getOpeningThemes(){
                    return openingThemes;
                }

                @Override
                public final EndingTheme[] getEndingThemes(){
                    return endingThemes;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "Anime{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           ", mainPicture=" + mainPicture +
                           ", alternativeTitles=" + alternativeTitles +
                           ", startDate=" + startDate +
                           ", endDate=" + endDate +
                           ", synopsis='" + synopsis + '\'' +
                           ", meanRating=" + meanRating +
                           ", rank=" + rank +
                           ", popularity=" + popularity +
                           ", usersListing=" + usersListing +
                           ", usersScoring=" + usersScoring +
                           ", nsfw=" + nsfw +
                           ", genres=" + Arrays.toString(genres) +
                           ", createdAt=" + createdAt +
                           ", updatedAt=" + updatedAt +
                           ", type=" + type +
                           ", status=" + status +
                           ", listStatus=" + listStatus +
                           ", episodes=" + episodes +
                           ", startSeason=" + startSeason +
                           ", broadcast=" + broadcast +
                           ", source=" + source +
                           ", episodeLength=" + episodeLength +
                           ", rating=" + rating +
                           ", studios=" + Arrays.toString(studios) +
                           ", pictures=" + Arrays.toString(pictures) +
                           ", background='" + background + '\'' +
                           ", relatedAnime=" + Arrays.toString(relatedAnime) +
                           ", relatedManga=" + Arrays.toString(relatedManga) +
                           ", recommendations=" + Arrays.toString(recommendations) +
                           ", statistics=" + statistics +
                           ", openingThemes=" + Arrays.toString(openingThemes) +
                           ", endingThemes=" + Arrays.toString(endingThemes) +
                           '}';
                }

            };
        }

        @SuppressWarnings("SpellCheckingInspection") // make sure matches below
        static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final long anime_id) {
            return new AnimeListStatus() {

                private final Long id                   = requireNonNull(() -> anime_id);
                private final AnimeStatus status        = requireNonNull(() -> AnimeStatus.asEnum(schema.getString("status")));
                private final Integer score             = requireNonNull(() -> schema.getInt("score"));
                private final Long startDate            = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long finishDate           = requireNonNull(() -> parseDate(schema.getString("finish_date")));
                private final Priority priority         = requireNonNull(() -> Priority.asEnum(schema.getInt("priority")));
                private final String[] tags             = requireNonNull(() -> schema.getStringArray("tags"));
                private final String comments           = requireNonNull(() -> schema.getString("comments"));
                private final Long updatedAt            = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final Integer watchedEpisodes   = requireNonNull(() -> schema.getInt("num_episodes_watched"));
                private final Boolean rewatching        = requireNonNull(() -> schema.getBoolean("is_rewatching"));
                private final Integer timesRewatched    = requireNonNull(() -> schema.getInt("num_times_rewatched"));
                private final RewatchValue rewatchValue = requireNonNull(() -> RewatchValue.asEnum(schema.getInt("rewatch_value")));

                // API methods

                @Override
                public final AnimeStatus getStatus() {
                    return status;
                }

                @Override
                public final Integer getScore() {
                    return score;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getFinishDate() {
                    return finishDate == null ? null : new Date(finishDate);
                }

                @Override
                public final Priority getPriority() {
                    return priority;
                }

                @Override
                public final String[] getTags() {
                    return tags;
                }

                @Override
                public final String getComments() {
                    return comments;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final Integer getWatchedEpisodes() {
                    return watchedEpisodes;
                }

                @Override
                public final Boolean isRewatching() {
                    return rewatching;
                }

                @Override
                public final Integer getTimesRewatched() {
                    return timesRewatched;
                }

                @Override
                public final RewatchValue getRewatchValue() {
                    return rewatchValue;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime(){
                    return mal.getAnime(id);
                }

                @Override
                public final AnimePreview getAnimePreview(){
                    return mal.getAnime(id);
                }

                @Override
                public final AnimeListUpdate edit(){
                    return mal.updateAnimeListing(id);
                }

                @Override
                public final String toString(){
                    return "AnimeListStatus{" +
                           "id=" + id +
                           ", status=" + status +
                           ", score=" + score +
                           ", startDate=" + startDate +
                           ", finishDate=" + finishDate +
                           ", priority=" + priority +
                           ", tags=" + Arrays.toString(tags) +
                           ", comments='" + comments + '\'' +
                           ", updatedAt=" + updatedAt +
                           ", watchedEpisodes=" + watchedEpisodes +
                           ", rewatching=" + rewatching +
                           ", timesRewatched=" + timesRewatched +
                           ", rewatchValue=" + rewatchValue +
                           '}';
                }

            };
        }

        @SuppressWarnings("SpellCheckingInspection")
        static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final AnimePreview anime) {
            return new AnimeListStatus() {

                private final AnimeStatus status        = requireNonNull(() -> AnimeStatus.asEnum(schema.getString("status")));
                private final Integer score             = requireNonNull(() -> schema.getInt("score"));
                private final Long startDate            = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long finishDate           = requireNonNull(() -> parseDate(schema.getString("finish_date")));
                private final Priority priority         = requireNonNull(() -> Priority.asEnum(schema.getInt("priority")));
                private final String[] tags             = requireNonNull(() -> schema.getStringArray("tags"));
                private final String comments           = requireNonNull(() -> schema.getString("comments"));
                private final Long updatedAt            = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final Integer watchedEpisodes   = requireNonNull(() -> schema.getInt("num_episodes_watched"));
                private final Boolean rewatching        = requireNonNull(() -> schema.getBoolean("is_rewatching"));
                private final Integer timesRewatched    = requireNonNull(() -> schema.getInt("num_times_rewatched"));
                private final RewatchValue rewatchValue = requireNonNull(() -> RewatchValue.asEnum(schema.getInt("rewatch_value")));

                // API methods

                @Override
                public final AnimeStatus getStatus() {
                    return status;
                }

                @Override
                public final Integer getScore() {
                    return score;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getFinishDate() {
                    return finishDate == null ? null : new Date(finishDate);
                }

                @Override
                public final Priority getPriority() {
                    return priority;
                }

                @Override
                public final String[] getTags() {
                    return tags;
                }

                @Override
                public final String getComments() {
                    return comments;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final Integer getWatchedEpisodes() {
                    return watchedEpisodes;
                }

                @Override
                public final Boolean isRewatching() {
                    return rewatching;
                }

                @Override
                public final Integer getTimesRewatched() {
                    return timesRewatched;
                }

                @Override
                public final RewatchValue getRewatchValue() {
                    return rewatchValue;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime(){
                    return anime.getAnime();
                }

                @Override
                public final AnimePreview getAnimePreview(){
                    return anime;
                }

                @Override
                public final AnimeListUpdate edit(){
                    return mal.updateAnimeListing(anime.getID());
                }

                @Override
                public final String toString(){
                    return "AnimeListStatus{" +
                           "status=" + status +
                           ", score=" + score +
                           ", startDate=" + startDate +
                           ", finishDate=" + finishDate +
                           ", priority=" + priority +
                           ", tags=" + Arrays.toString(tags) +
                           ", comments='" + comments + '\'' +
                           ", updatedAt=" + updatedAt +
                           ", watchedEpisodes=" + watchedEpisodes +
                           ", rewatching=" + rewatching +
                           ", timesRewatched=" + timesRewatched +
                           ", rewatchValue=" + rewatchValue +
                           '}';
                }

            };
        }

        static AnimePreview asAnimePreview(final MyAnimeList mal, final JsonObject schema){
            return new AnimePreview() {

                private final Long id               = requireNonNull(() -> schema.getLong("id"));
                private final String title          = requireNonNull(() -> schema.getString("title"));
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.getJsonObject("main_picture")));
                private final AlternativeTitles alternativeTitles
                                                    = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles")));
                private final Long startDate        = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long endDate          = requireNonNull(() -> parseDate(schema.getString("end_date")));
                private final String synopsis       = requireNonNull(() -> schema.getString("synopsis"));
                private final Float meanRating      = requireNonNull(() -> schema.getFloat("mean"));
                private final Integer rank          = requireNonNull(() -> schema.getInt("rank"));
                private final Integer popularity    = requireNonNull(() -> schema.getInt("popularity"));
                private final Integer usersListing  = requireNonNull(() -> schema.getInt("num_list_users"));
                private final Integer usersScoring  = requireNonNull(() -> schema.getInt("num_scoring_users"));
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.getString("nsfw")));
                private final Genre[] genres        = requireNonNull(() -> adaptList(schema.getJsonArray("genres"), g -> Genre.asEnum(g.getInt("id")), Genre.class));
                private final Long createdAt        = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final Long updatedAt        = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final AnimeType type        = requireNonNull(() -> AnimeType.asEnum(schema.getString("media_type")));
                private final AnimeAirStatus status = requireNonNull(() -> AnimeAirStatus.asEnum(schema.getString("status")));
                private final AnimeListStatus listStatus
                                                    = requireNonNull(() -> asAnimeListStatus(mal, schema.getJsonObject("my_list_status"), this));
                private final Integer episodes      = requireNonNull(() -> schema.getInt("num_episodes"));
                private final StartSeason startSeason
                                                    = requireNonNull(() -> asStartSeason(mal, schema.getJsonObject("start_season")));
                private final Broadcast broadcast   = requireNonNull(() -> asBroadcast(mal, schema.getJsonObject("broadcast")));
                private final AnimeSource source    = requireNonNull(() -> AnimeSource.asEnum(schema.getString("source")));
                private final Integer episodeLength = requireNonNull(() -> schema.getInt("average_episode_duration"));
                private final AnimeRating rating    = requireNonNull(() -> AnimeRating.asEnum(schema.getString("rating")));
                private final Studio[] studios      = requireNonNull(() -> adaptList(schema.getJsonArray("studios"), s -> asStudio(mal, s), Studio.class));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final Picture getMainPicture() {
                    return mainPicture;
                }

                @Override
                public final AlternativeTitles getAlternativeTitles() {
                    return alternativeTitles;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getEndDate() {
                    return endDate == null ? null : new Date(endDate);
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final Float  getMeanRating() {
                    return meanRating;
                }

                @Override
                public final Integer getRank() {
                    return rank;
                }

                @Override
                public final Integer getPopularity() {
                    return popularity;
                }

                @Override
                public final Integer getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final Integer getUserScoringCount() {
                    return usersScoring;
                }

                @Override
                public final NSFW getNSFW() {
                    return nsfw;
                }

                @Override
                public final Genre[] getGenres() {
                    return genres;
                }

                @Override
                public final Date getCreatedAt() {
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis(){
                    return createdAt;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final AnimeType getType() {
                    return type;
                }

                @Override
                public final AnimeAirStatus getStatus() {
                    return status;
                }

                @Override
                public final AnimeListStatus getListStatus() {
                    return listStatus;
                }

                @Override
                public final Integer getEpisodes() {
                    return episodes;
                }

                @Override
                public final StartSeason getStartSeason() {
                    return startSeason;
                }

                @Override
                public final Broadcast getBroadcast() {
                    return broadcast;
                }

                @Override
                public final AnimeSource getSource() {
                    return source;
                }

                @Override
                public final Integer getAverageEpisodeLength() {
                    return episodeLength;
                }

                @Override
                public final AnimeRating getRating() {
                    return rating;
                }

                @Override
                public final Studio[] getStudios() {
                    return studios;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime() {
                    return mal.getAnime(id);
                }

                @Override
                public String toString(){
                    return "AnimePreview{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           ", mainPicture=" + mainPicture +
                           ", alternativeTitles=" + alternativeTitles +
                           ", startDate=" + startDate +
                           ", endDate=" + endDate +
                           ", synopsis='" + synopsis + '\'' +
                           ", meanRating=" + meanRating +
                           ", rank=" + rank +
                           ", popularity=" + popularity +
                           ", usersListing=" + usersListing +
                           ", usersScoring=" + usersScoring +
                           ", nsfw=" + nsfw +
                           ", genres=" + Arrays.toString(genres) +
                           ", createdAt=" + createdAt +
                           ", updatedAt=" + updatedAt +
                           ", type=" + type +
                           ", status=" + status +
                           ", listStatus=" + listStatus +
                           ", episodes=" + episodes +
                           ", startSeason=" + startSeason +
                           ", broadcast=" + broadcast +
                           ", source=" + source +
                           ", episodeLength=" + episodeLength +
                           ", rating=" + rating +
                           ", studios=" + Arrays.toString(studios) +
                           '}';
                }

            };
        }

        static AnimeRanking asAnimeRanking(final MyAnimeList mal, final JsonObject schema){
            return new AnimeRanking() {

                private final AnimePreview anime        = requireNonNull(() -> asAnimePreview(mal, schema.getJsonObject("node")));
                private final Integer ranking           = requireNonNull(() -> schema.getJsonObject("ranking").getInt("rank"));
                private final Integer previousRanking   = requireNonNull(() -> schema.getJsonObject("ranking").getInt("previous_rank"));

                // API methods

                @Override
                public final AnimePreview getAnimePreview(){
                    return anime;
                }

                @Override
                public final Integer getRanking(){
                    return ranking;
                }

                @Override
                public final Integer getPreviousRank(){
                    return previousRanking;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime(){
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final String toString(){
                    return "AnimeRanking{" +
                           "anime=" + anime +
                           ", ranking=" + ranking +
                           ", previousRanking=" + previousRanking +
                           '}';
                }

            };
        }

        static AnimeRecommendation asAnimeRecommendation(final MyAnimeList mal, final JsonObject schema){
            return new AnimeRecommendation() {

                private final AnimePreview anime        = requireNonNull(() -> asAnimePreview(mal, schema.getJsonObject("node")));
                private final Integer recommendations   = requireNonNull(() -> schema.getInt("num_recommendations"));

                // API methods

                @Override
                public final AnimePreview getAnimePreview() {
                    return anime;
                }

                @Override
                public final Integer getRecommendations() {
                    return recommendations;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime() {
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final String toString(){
                    return "AnimeRecommendation{" +
                           "anime=" + anime +
                           ", recommendations=" + recommendations +
                           '}';
                }

            };
        }

        static RelatedAnime asRelatedAnime(final MyAnimeList mal, final JsonObject schema){
            return new RelatedAnime() {

                private final AnimePreview anime            = requireNonNull(() -> asAnimePreview(mal, schema.getJsonObject("node")));
                private final RelationType relationType     = requireNonNull(() -> RelationType.asEnum(schema.getString("relation_type")));
                private final String relationTypeFormatted  = requireNonNull(() -> schema.getString("relation_type_formatted"));

                // API methods

                @Override
                public final AnimePreview getAnimePreview() {
                    return anime;
                }

                @Override
                public final RelationType getRelationType() {
                    return relationType;
                }

                @Override
                public final String getRelationTypeFormat() {
                    return relationTypeFormatted;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.anime.Anime getAnime() {
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final String toString(){
                    return "RelatedAnime{" +
                           "anime=" + anime +
                           ", relationType=" + relationType +
                           ", relationTypeFormatted='" + relationTypeFormatted + '\'' +
                           '}';
                }

            };
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    static abstract class Forum {

        static ForumTopicCreator asForumTopicCreator(final MyAnimeList mal, final JsonObject schema){
            return new ForumTopicCreator() {

                private final Long id       = requireNonNull(() -> schema.getLong("id"));
                private final String name   = requireNonNull(() -> schema.getString("name"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getName() {
                    return name;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.user.User getUser(){
                    return mal.getUser(name);
                }

                @Override
                public final String toString(){
                    return "ForumTopicCreator{" +
                           "id=" + id +
                           ", name='" + name + '\'' +
                           '}';
                }

            };
        }

        static ForumTopic asForumTopicDetail(final MyAnimeList mal, final JsonObject schema, final Long boardid, final Long subboardid){
            return new ForumTopic() {

                private final Long boardID                  = boardid;
                private final Long subBoardID               = subboardid;

                private final Long id                       = requireNonNull(() -> schema.getLong("id"));
                private final String title                  = requireNonNull(() -> schema.getString("title"));
                private final Long createdAt                = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final ForumTopicCreator createdBy   = requireNonNull(() -> asForumTopicCreator(mal, schema.getJsonObject("created_by")));
                private final Integer posts                 = requireNonNull(() -> schema.getInt("number_of_posts"));
                private final Long lastPostedAt             = requireNonNull(() -> parseISO8601(schema.getString("last_post_created_at")));
                private final ForumTopicCreator lastPostedBy
                                                            = requireNonNull(() -> asForumTopicCreator(mal, schema.getJsonObject("last_post_created_by")));
                private final Boolean locked                = requireNonNull(() -> schema.getBoolean("is_locked"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final Date getCreatedAt(){
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis() {
                    return createdAt;
                }

                @Override
                public final ForumTopicCreator getCreatedBy() {
                    return createdBy;
                }

                @Override
                public final Integer getPostsCount() {
                    return posts;
                }

                @Override
                public final Date getLastPostCreatedAt(){
                    return lastPostedAt == null ? null : new Date(lastPostedAt);
                }

                @Override
                public final Long getLastPostCreatedAtEpochMillis() {
                    return lastPostedAt;
                }

                @Override
                public final ForumTopicCreator getLastPostCreatedBy() {
                    return lastPostedBy;
                }

                @Override
                public final Boolean isLocked() {
                    return locked;
                }

                // additional methods

                @Override
                public final Long getBoardID(){
                    return boardID;
                }

                @Override
                public final Long getSubBoardID(){
                    return subBoardID;
                }

                @Override
                public final String toString(){
                    return "ForumTopic{" +
                           "boardID=" + boardID +
                           ", subBoardID=" + subBoardID +
                           ", id=" + id +
                           ", title='" + title + '\'' +
                           ", createdAt=" + createdAt +
                           ", createdBy=" + createdBy +
                           ", posts=" + posts +
                           ", lastPostedAt=" + lastPostedAt +
                           ", lastPostedBy=" + lastPostedBy +
                           ", locked=" + locked +
                           '}';
                }

            };
        }

        static Poll asPoll(final MyAnimeList mal, final JsonObject schema, final ForumTopicDetail forumTopic){
            return new Poll() {

                private final Long id               = requireNonNull(() -> schema.getLong("id"));
                private final String question       = requireNonNull(() -> schema.getString("question"));
                private final Boolean isClosed      = requireNonNull(() -> schema.getBoolean("closed"));
                private final PollOption[] options  = requireNonNull(() -> adaptList(schema.getJsonArray("options"), o -> asPollOption(mal, o, this), PollOption.class));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getQuestion() {
                    return question;
                }

                @Override
                public final Boolean isClosed() {
                    return isClosed;
                }

                @Override
                public final PollOption[] getOptions() {
                    return options;
                }

                // additional methods

                @Override
                public final ForumTopicDetail getForumTopicDetail() {
                    return forumTopic;
                }

                @Override
                public final String toString(){
                    return "Poll{" +
                           "id=" + id +
                           ", question='" + question + '\'' +
                           ", isClosed=" + isClosed +
                           ", options=" + Arrays.toString(options) +
                           '}';
                }

            };
        }

        static PollOption asPollOption(final MyAnimeList mal, final JsonObject schema, final Poll poll){
            return new PollOption() {

                private final Long id       = requireNonNull(() -> schema.getLong("id"));
                private final String text   = requireNonNull(() -> schema.getString("text"));
                private final Integer votes = requireNonNull(() -> schema.getInt("votes"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getText() {
                    return text;
                }

                @Override
                public final Integer getVotes() {
                    return votes;
                }

                // additional methods

                @Override
                public final Poll getPoll() {
                    return poll;
                }

                @Override
                public final String toString(){
                    return "PollOption{" +
                           "id=" + id +
                           ", text='" + text + '\'' +
                           ", votes=" + votes +
                           '}';
                }

            };
        }

        static PostAuthor asPostAuthor(final MyAnimeList mal, final JsonObject schema){
            return new PostAuthor() {

                private final Long id               = requireNonNull(() -> schema.getLong("id"));
                private final String name           = requireNonNull(() -> schema.getString("name"));
                private final String forumAvatarURL = requireNonNull(() -> schema.getString("forum_avator"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getName() {
                    return name;
                }

                @Override
                public final String getForumAvatarURL() {
                    return forumAvatarURL;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.user.User getUser() {
                    return mal.getUser(name);
                }

                @Override
                public final String toString(){
                    return "PostAuthor{" +
                           "id=" + id +
                           ", name='" + name + '\'' +
                           ", forumAvatarURL='" + forumAvatarURL + '\'' +
                           '}';
                }

            };
        }

        static ForumBoard asForumBoard(final MyAnimeList mal, final JsonObject schema, final ForumCategory forumCategory){
            return new ForumBoard() {

                private final Long id                   = requireNonNull(() -> schema.getLong("id"));
                private final String title              = requireNonNull(() -> schema.getString("title"));
                private final String description        = requireNonNull(() -> schema.getString("description"));
                private final ForumSubBoard[] subBoards = adaptList(schema.getJsonArray("subboards"), b -> asForumSubBoard(mal, b, this), ForumSubBoard.class);

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final String getDescription() {
                    return description;
                }

                @Override
                public final ForumSubBoard[] getSubBoards() {
                    return subBoards;
                }

                // additional methods

                @Override
                public final ForumCategory getCategory() {
                    return forumCategory;
                }

                @Override
                public final String toString(){
                    return "ForumBoard{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           ", description='" + description + '\'' +
                           ", subBoards=" + Arrays.toString(subBoards) +
                           '}';
                }

            };
        }

        static ForumCategory asForumCategory(final MyAnimeList mal, final JsonObject schema){
            return new ForumCategory() {

                private final String title              = requireNonNull(() -> schema.getString("title"));
                private final ForumBoard[] forumBoards  = adaptList(schema.getJsonArray("boards"), b -> asForumBoard(mal, b, this), ForumBoard.class);

                // API methods

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final ForumBoard[] getForumBoards() {
                    return forumBoards;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "ForumCategory{" +
                           "title='" + title + '\'' +
                           ", forumBoards=" + Arrays.toString(forumBoards) +
                           '}';
                }

            };
        }

        static ForumSubBoard asForumSubBoard(final MyAnimeList mal, final JsonObject schema, final ForumBoard forumBoard){
            return new ForumSubBoard() {

                private final Long id       = requireNonNull(() -> schema.getLong("id"));
                private final String title  = requireNonNull(() -> schema.getString("title"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                // additional methods

                @Override
                public final ForumBoard getBoard() {
                    return forumBoard;
                }

                @Override
                public final String toString(){
                    return "ForumSubBoard{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           '}';
                }

            };
        }

        static ForumTopicDetail asForumTopic(final MyAnimeList mal, final JsonObject schema, final long topicid){
            return new ForumTopicDetail() {

                private final long id       = topicid;
                private final String title  = requireNonNull(() -> schema.getString("title"));
                private final Post[] posts  = requireNonNull(() -> adaptList(schema.getJsonArray("posts"), p -> asPost(mal, p, this), Post.class));
                private final Poll poll     = requireNonNull(() -> asPoll(mal, schema.getJsonObject("poll"), this));

                // API methods

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final Post[] getPosts() {
                    return posts;
                }

                @Override
                public final Poll getPoll() {
                    return poll;
                }

                // additional methods

                @Override
                public final Long getID(){
                    return id;
                }

                @Override
                public final String toString(){
                    return "ForumTopicDetail{" +
                           "title='" + title + '\'' +
                           ", posts=" + Arrays.toString(posts) +
                           ", poll=" + poll +
                           '}';
                }

            };
        }

        // make sure matches below
        static Post asPost(final MyAnimeList mal, final JsonObject schema, final ForumTopicDetail forumTopic){
            return new Post() {

                private final Long id           = requireNonNull(() -> schema.getLong("id"));
                private final Integer number    = requireNonNull(() -> schema.getInt("number"));
                private final Long createdAt    = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final PostAuthor author = requireNonNull(() -> asPostAuthor(mal, schema.getJsonObject("created_by")));
                private final String body       = requireNonNull(() -> schema.getString("body"));
                private final String signature  = requireNonNull(() -> schema.getString("signature"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final Integer getNumber() {
                    return number;
                }

                @Override
                public final Date getCreatedAt(){
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis() {
                    return createdAt;
                }

                @Override
                public final PostAuthor getAuthor() {
                    return author;
                }

                @Override
                public final String getBody() {
                    return body;
                }

                @Override
                public final String getSignature() {
                    return signature;
                }

                // additional methods

                @Override
                public final ForumTopicDetail getForumTopicDetail() {
                    return forumTopic;
                }

                @Override
                public final String toString(){
                    return "Post{" +
                           "id=" + id +
                           ", number=" + number +
                           ", createdAt=" + createdAt +
                           ", author=" + author +
                           ", body='" + body + '\'' +
                           ", signature='" + signature + '\'' +
                           '}';
                }

            };
        }

        static Post asPost(final MyAnimeList mal, final JsonObject schema, final long ftdid){
            return new Post() {

                private final Long id           = requireNonNull(() -> schema.getLong("id"));
                private final Integer number    = requireNonNull(() -> schema.getInt("number"));
                private final Long createdAt    = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final PostAuthor author = requireNonNull(() -> asPostAuthor(mal, schema.getJsonObject("created_by")));
                private final String body       = requireNonNull(() -> schema.getString("body"));
                private final String signature  = requireNonNull(() -> schema.getString("signature"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final Integer getNumber() {
                    return number;
                }

                @Override
                public final Date getCreatedAt(){
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis() {
                    return createdAt;
                }

                @Override
                public final PostAuthor getAuthor() {
                    return author;
                }

                @Override
                public final String getBody() {
                    return body;
                }

                @Override
                public final String getSignature() {
                    return signature;
                }

                // additional methods

                @Override
                public final ForumTopicDetail getForumTopicDetail() {
                    return mal.getForumTopicDetail(ftdid);
                }

                @Override
                public final String toString(){
                    return "Post{" +
                           "id=" + id +
                           ", number=" + number +
                           ", createdAt=" + createdAt +
                           ", author=" + author +
                           ", body='" + body + '\'' +
                           ", signature='" + signature + '\'' +
                           '}';
                }

            };
        }

    }

    static abstract class Manga {

        static Author asAuthor(final MyAnimeList mal, final JsonObject schema){
            return new Author() {

                private final Long id           = requireNonNull(() -> schema.getJsonObject("node").getLong("id"));
                private final String firstName  = requireNonNull(() -> schema.getJsonObject("node").getString("first_name"));
                private final String lastName   = requireNonNull(() -> schema.getJsonObject("node").getString("last_name"));
                private final String role       = requireNonNull(() -> schema.getString("role"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getFirstName() {
                    return firstName;
                }

                @Override
                public final String getLastName() {
                    return lastName;
                }

                @Override
                public final String getRole() {
                    return role;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "Author{" +
                           "id=" + id +
                           ", firstName='" + firstName + '\'' +
                           ", lastName='" + lastName + '\'' +
                           ", role='" + role + '\'' +
                           '}';
                }

            };
        }

        static Publisher asPublisher(final MyAnimeList mal, final JsonObject schema){
            return new Publisher() {

                private final Long id       = requireNonNull(() -> schema.getJsonObject("node").getLong("id"));
                private final String name   = requireNonNull(() -> schema.getJsonObject("node").getString("name"));
                private final String role   = requireNonNull(() -> schema.getString("role"));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

               @Override
                public final String getName() {
                    return name;
                }

                @Override
                public final String getRole() {
                    return role;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "Publisher{" +
                           "id=" + id +
                           ", name='" + name + '\'' +
                           ", role='" + role + '\'' +
                           '}';
                }

            };
        }

        static com.kttdevelopment.mal4j.manga.Manga asManga(final MyAnimeList mal, final JsonObject schema){
            return new com.kttdevelopment.mal4j.manga.Manga() {

                private final Long id               = requireNonNull(() -> schema.getLong("id"));
                private final String title          = requireNonNull(() -> schema.getString("title"));
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.getJsonObject("main_picture")));
                private final AlternativeTitles alternativeTitles
                                                    = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles")));
                private final Long startDate        = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long endDate          = requireNonNull(() -> parseDate(schema.getString("end_date")));
                private final String synopsis       = requireNonNull(() -> schema.getString("synopsis"));
                private final Float meanRating      = requireNonNull(() -> schema.getFloat("mean"));
                private final Integer rank          = requireNonNull(() -> schema.getInt("rank"));
                private final Integer popularity    = requireNonNull(() -> schema.getInt("popularity"));
                private final Integer usersListing  = requireNonNull(() -> schema.getInt("num_list_users"));
                private final Integer usersScoring  = requireNonNull(() -> schema.getInt("num_scoring_users"));
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.getString("nsfw")));
                private final Genre[] genres        = requireNonNull(() -> adaptList(schema.getJsonArray("genres"), g -> Genre.asEnum(g.getInt("id")), Genre.class));
                private final Long createdAt        = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final Long updatedAt        = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final MangaType type        = requireNonNull(() -> MangaType.asEnum(schema.getString("media_type")));
                private final MangaPublishStatus status
                                                    = requireNonNull(() -> MangaPublishStatus.asEnum(schema.getString("status")));
                private final MangaListStatus listStatus
                                                    = requireNonNull(() -> asMangaListStatus(mal, schema.getJsonObject("my_list_status"), this));
                private final Integer volumes       = requireNonNull(() -> schema.getInt("num_volumes"));
                private final Integer chapters      = requireNonNull(() -> schema.getInt("num_chapters"));
                private final Author[] authors      = requireNonNull(() -> adaptList(schema.getJsonArray("authors"), a -> asAuthor(mal, a), Author.class));
                private final Picture[] pictures    = requireNonNull(() -> adaptList(schema.getJsonArray("pictures"), p -> Common.asPicture(mal, p), Picture.class));
                private final String background     = requireNonNull(() -> schema.getString("background"));
                private final RelatedAnime[] relatedAnime
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("related_anime"), a -> Anime.asRelatedAnime(mal, a), RelatedAnime.class));
                private final RelatedManga[] relatedManga
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("related_manga"), m -> asRelatedManga(mal, m), RelatedManga.class));
                private final MangaRecommendation[] recommendations = requireNonNull(() -> adaptList(schema.getJsonArray("recommendations"), r -> asMangaRecommendation(mal, r), MangaRecommendation.class));
                private final Publisher[] serialization
                                                    = requireNonNull(() -> adaptList(schema.getJsonArray("serialization"), s -> asPublisher(mal, s), Publisher.class));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final Picture getMainPicture() {
                    return mainPicture;
                }

                @Override
                public final AlternativeTitles getAlternativeTitles() {
                    return alternativeTitles;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getEndDate() {
                    return endDate == null ? null : new Date(endDate);
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final Float  getMeanRating() {
                    return meanRating;
                }

                @Override
                public final Integer getRank() {
                    return rank;
                }

                @Override
                public final Integer getPopularity() {
                    return popularity;
                }

                @Override
                public final Integer getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final Integer getUserScoringCount() {
                    return usersScoring;
                }

                @Override
                public final NSFW getNSFW() {
                    return nsfw;
                }

                @Override
                public final Genre[] getGenres() {
                    return genres;
                }

                @Override
                public final Date getCreatedAt() {
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis(){
                    return createdAt;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final MangaType getType() {
                    return type;
                }

                @Override
                public final MangaPublishStatus getStatus() {
                    return status;
                }

                @Override
                public final MangaListStatus getListStatus() {
                    return listStatus;
                }

                @Override
                public final Integer getVolumes() {
                    return volumes;
                }

                @Override
                public final Integer getChapters() {
                    return chapters;
                }

                @Override
                public final Author[] getAuthors() {
                    return authors;
                }

                @Override
                public final Picture[] getPictures() {
                    return pictures;
                }

                @Override
                public final String getBackground() {
                    return background;
                }

                @Override
                public final RelatedAnime[] getRelatedAnime() {
                    return relatedAnime;
                }

                @Override
                public final RelatedManga[] getRelatedManga() {
                    return relatedManga;
                }

                @Override
                public final MangaRecommendation[] getRecommendations() {
                    return recommendations;
                }

                @Override
                public final Publisher[] getSerialization() {
                    return serialization;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "Manga{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           ", mainPicture=" + mainPicture +
                           ", alternativeTitles=" + alternativeTitles +
                           ", startDate=" + startDate +
                           ", endDate=" + endDate +
                           ", synopsis='" + synopsis + '\'' +
                           ", meanRating=" + meanRating +
                           ", rank=" + rank +
                           ", popularity=" + popularity +
                           ", usersListing=" + usersListing +
                           ", usersScoring=" + usersScoring +
                           ", nsfw=" + nsfw +
                           ", genres=" + Arrays.toString(genres) +
                           ", createdAt=" + createdAt +
                           ", updatedAt=" + updatedAt +
                           ", type=" + type +
                           ", status=" + status +
                           ", listStatus=" + listStatus +
                           ", volumes=" + volumes +
                           ", chapters=" + chapters +
                           ", authors=" + Arrays.toString(authors) +
                           ", pictures=" + Arrays.toString(pictures) +
                           ", background='" + background + '\'' +
                           ", relatedAnime=" + Arrays.toString(relatedAnime) +
                           ", relatedManga=" + Arrays.toString(relatedManga) +
                           ", recommendations=" + Arrays.toString(recommendations) +
                           ", serialization=" + Arrays.toString(serialization) +
                           '}';
                }

            };
        }

        // make sure matches below
        static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final long manga_id){
            return new MangaListStatus() {

                private final Long id                   = requireNonNull(() -> manga_id);
                private final MangaStatus status        = requireNonNull(() -> MangaStatus.asEnum(schema.getString("status")));
                private final Integer score             = requireNonNull(() -> schema.getInt("score"));
                private final Long startDate            = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long finishDate           = requireNonNull(() -> parseDate(schema.getString("finish_date")));
                private final Priority priority         = requireNonNull(() -> Priority.asEnum(schema.getInt("priority")));
                private final String[] tags             = requireNonNull(() -> schema.getStringArray("tags"));
                private final String comments           = requireNonNull(() -> schema.getString("comments"));
                private final Long updatedAt            = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final Integer volumesRead       = requireNonNull(() -> schema.getInt("num_volumes_read"));
                private final Integer chaptersRead      = requireNonNull(() -> schema.getInt("num_chapters_read"));
                private final Boolean rereading         = requireNonNull(() -> schema.getBoolean("is_rereading"));
                private final Integer timesReread       = requireNonNull(() -> schema.getInt("num_times_reread"));
                private final RereadValue rereadValue   = requireNonNull(() -> RereadValue.asEnum(schema.getInt("reread_value")));

                // API methods

                @Override
                public final MangaStatus getStatus() {
                    return status;
                }

                @Override
                public final Integer getScore() {
                    return score;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getFinishDate() {
                    return finishDate == null ? null : new Date(finishDate);
                }

                @Override
                public final Priority getPriority() {
                    return priority;
                }

                @Override
                public final String[] getTags() {
                    return tags;
                }

                @Override
                public final String getComments() {
                    return comments;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final Integer getVolumesRead() {
                    return volumesRead;
                }

                @Override
                public final Integer getChaptersRead() {
                    return chaptersRead;
                }

                @Override
                public final Boolean isRereading() {
                    return rereading;
                }

                @Override
                public final Integer getTimesReread() {
                    return timesReread;
                }

                @Override
                public final RereadValue getRereadValue() {
                    return rereadValue;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.manga.Manga getManga(){
                    return mal.getManga(id);
                }

                @Override
                public final MangaPreview getMangaPreview(){
                    return mal.getManga(id);
                }

                @Override
                public final MangaListUpdate edit(){
                    return mal.updateMangaListing(id);
                }

                @Override
                public final String toString(){
                    return "MangaListStatus{" +
                           "id=" + id +
                           ", status=" + status +
                           ", score=" + score +
                           ", startDate=" + startDate +
                           ", finishDate=" + finishDate +
                           ", priority=" + priority +
                           ", tags=" + Arrays.toString(tags) +
                           ", comments='" + comments + '\'' +
                           ", updatedAt=" + updatedAt +
                           ", volumesRead=" + volumesRead +
                           ", chaptersRead=" + chaptersRead +
                           ", rereading=" + rereading +
                           ", timesReread=" + timesReread +
                           ", rereadValue=" + rereadValue +
                           '}';
                }

            };
        }

        static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final MangaPreview manga){
            return new MangaListStatus() {

                private final MangaStatus status        = requireNonNull(() -> MangaStatus.asEnum(schema.getString("status")));
                private final Integer score             = requireNonNull(() -> schema.getInt("score"));
                private final Long startDate            = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long finishDate           = requireNonNull(() -> parseDate(schema.getString("finish_date")));
                private final Priority priority         = requireNonNull(() -> Priority.asEnum(schema.getInt("priority")));
                private final String[] tags             = requireNonNull(() -> schema.getStringArray("tags"));
                private final String comments           = requireNonNull(() -> schema.getString("comments"));
                private final Long updatedAt            = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final Integer volumesRead       = requireNonNull(() -> schema.getInt("num_volumes_read"));
                private final Integer chaptersRead      = requireNonNull(() -> schema.getInt("num_chapters_read"));
                private final Boolean rereading         = requireNonNull(() -> schema.getBoolean("is_rereading"));
                private final Integer timesReread       = requireNonNull(() -> schema.getInt("num_times_reread"));
                private final RereadValue rereadValue   = requireNonNull(() -> RereadValue.asEnum(schema.getInt("reread_value")));

                // API methods

                @Override
                public final MangaStatus getStatus() {
                    return status;
                }

                @Override
                public final Integer getScore() {
                    return score;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getFinishDate() {
                    return finishDate == null ? null : new Date(finishDate);
                }

                @Override
                public final Priority getPriority() {
                    return priority;
                }

                @Override
                public final String[] getTags() {
                    return tags;
                }

                @Override
                public final String getComments() {
                    return comments;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final Integer getVolumesRead() {
                    return volumesRead;
                }

                @Override
                public final Integer getChaptersRead() {
                    return chaptersRead;
                }

                @Override
                public final Boolean isRereading() {
                    return rereading;
                }

                @Override
                public final Integer getTimesReread() {
                    return timesReread;
                }

                @Override
                public final RereadValue getRereadValue() {
                    return rereadValue;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.manga.Manga getManga(){
                    return manga.getManga();
                }

                @Override
                public final MangaPreview getMangaPreview(){
                    return manga;
                }

                @Override
                public final MangaListUpdate edit(){
                    return mal.updateMangaListing(manga.getID());
                }

                @Override
                public final String toString(){
                    return "MangaListStatus{" +
                           "status=" + status +
                           ", score=" + score +
                           ", startDate=" + startDate +
                           ", finishDate=" + finishDate +
                           ", priority=" + priority +
                           ", tags=" + Arrays.toString(tags) +
                           ", comments='" + comments + '\'' +
                           ", updatedAt=" + updatedAt +
                           ", volumesRead=" + volumesRead +
                           ", chaptersRead=" + chaptersRead +
                           ", rereading=" + rereading +
                           ", timesReread=" + timesReread +
                           ", rereadValue=" + rereadValue +
                           '}';
                }

            };
        }

        static MangaPreview asMangaPreview(final MyAnimeList mal, final JsonObject schema){
            return new MangaPreview() {

                private final Long id               = requireNonNull(() -> schema.getLong("id"));
                private final String title          = requireNonNull(() -> schema.getString("title"));
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.getJsonObject("main_picture")));
                private final AlternativeTitles alternativeTitles
                                                    = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles")));
                private final Long startDate        = requireNonNull(() -> parseDate(schema.getString("start_date")));
                private final Long endDate          = requireNonNull(() -> parseDate(schema.getString("end_date")));
                private final String synopsis       = requireNonNull(() -> schema.getString("synopsis"));
                private final Float meanRating      = requireNonNull(() -> schema.getFloat("mean"));
                private final Integer rank          = requireNonNull(() -> schema.getInt("rank"));
                private final Integer popularity    = requireNonNull(() -> schema.getInt("popularity"));
                private final Integer usersListing  = requireNonNull(() -> schema.getInt("num_list_users"));
                private final Integer usersScoring  = requireNonNull(() -> schema.getInt("num_scoring_users"));
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.getString("nsfw")));
                private final Genre[] genres        = requireNonNull(() -> adaptList(schema.getJsonArray("genres"), g -> Genre.asEnum(g.getInt("id")), Genre.class));
                private final Long createdAt        = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
                private final Long updatedAt        = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
                private final MangaType type        = requireNonNull(() -> MangaType.asEnum(schema.getString("media_type")));
                private final MangaPublishStatus status
                                                    = requireNonNull(() -> MangaPublishStatus.asEnum(schema.getString("status")));
                private final MangaListStatus listStatus
                                                    = requireNonNull(() -> asMangaListStatus(mal, schema.getJsonObject("my_list_status"), this));
                private final Integer volumes       = requireNonNull(() -> schema.getInt("num_volumes"));
                private final Integer chapters      = requireNonNull(() -> schema.getInt("num_chapters"));
                private final Author[] authors      = requireNonNull(() -> adaptList(schema.getJsonArray("authors"), a -> asAuthor(mal, a), Author.class));

                // API methods

                @Override
                public final Long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final Picture getMainPicture() {
                    return mainPicture;
                }

                @Override
                public final AlternativeTitles getAlternativeTitles() {
                    return alternativeTitles;
                }

                @Override
                public final Date getStartDate() {
                    return startDate == null ? null : new Date(startDate);
                }

                @Override
                public final Date getEndDate() {
                    return endDate == null ? null : new Date(endDate);
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final Float  getMeanRating() {
                    return meanRating;
                }

                @Override
                public final Integer getRank() {
                    return rank;
                }

                @Override
                public final Integer getPopularity() {
                    return popularity;
                }

                @Override
                public final Integer getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final Integer getUserScoringCount() {
                    return usersScoring;
                }

                @Override
                public final NSFW getNSFW() {
                    return nsfw;
                }

                @Override
                public final Genre[] getGenres() {
                    return genres;
                }

                @Override
                public final Date getCreatedAt() {
                    return createdAt == null ? null : new Date(createdAt);
                }

                @Override
                public final Long getCreatedAtEpochMillis(){
                    return createdAt;
                }

                @Override
                public final Date getUpdatedAt() {
                    return updatedAt == null ? null : new Date(updatedAt);
                }

                @Override
                public final Long getUpdatedAtEpochMillis(){
                    return updatedAt;
                }

                @Override
                public final MangaType getType() {
                    return type;
                }

                @Override
                public final MangaPublishStatus getStatus() {
                    return status;
                }

                @Override
                public final MangaListStatus getListStatus() {
                    return listStatus;
                }

                @Override
                public final Integer getVolumes() {
                    return volumes;
                }

                @Override
                public final Integer getChapters() {
                    return chapters;
                }

                @Override
                public final Author[] getAuthors() {
                    return authors;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.manga.Manga getManga() {
                    return mal.getManga(id);
                }

                @Override
                public final String toString(){
                    return "MangaPreview{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           ", mainPicture=" + mainPicture +
                           ", alternativeTitles=" + alternativeTitles +
                           ", startDate=" + startDate +
                           ", endDate=" + endDate +
                           ", synopsis='" + synopsis + '\'' +
                           ", meanRating=" + meanRating +
                           ", rank=" + rank +
                           ", popularity=" + popularity +
                           ", usersListing=" + usersListing +
                           ", usersScoring=" + usersScoring +
                           ", nsfw=" + nsfw +
                           ", genres=" + Arrays.toString(genres) +
                           ", createdAt=" + createdAt +
                           ", updatedAt=" + updatedAt +
                           ", type=" + type +
                           ", status=" + status +
                           ", listStatus=" + listStatus +
                           ", volumes=" + volumes +
                           ", chapters=" + chapters +
                           ", authors=" + Arrays.toString(authors) +
                           '}';
                }

            };
        }

        static MangaRanking asMangaRanking(final MyAnimeList mal, final JsonObject schema){
            return new MangaRanking() {

                private final MangaPreview manga        = requireNonNull(() -> asMangaPreview(mal, schema.getJsonObject("node")));
                private final Integer ranking           = requireNonNull(() -> schema.getJsonObject("ranking").getInt("rank"));
                private final Integer previousRanking   = requireNonNull(() -> schema.getJsonObject("ranking").getInt("previous_rank"));

                // API method

                @Override
                public final MangaPreview getMangaPreview() {
                    return manga;
                }

                @Override
                public final Integer getRanking() {
                    return ranking;
                }

                @Override
                public final Integer getPreviousRank() {
                    return previousRanking;
                }

                // API methods

                @Override
                public final com.kttdevelopment.mal4j.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final String toString(){
                    return "MangaRanking{" +
                           "manga=" + manga +
                           ", ranking=" + ranking +
                           ", previousRanking=" + previousRanking +
                           '}';
                }

            };
        }

        static MangaRecommendation asMangaRecommendation(final MyAnimeList mal, final JsonObject schema){
            return new MangaRecommendation() {

                private final MangaPreview manga        = requireNonNull(() -> asMangaPreview(mal, schema.getJsonObject("node")));
                private final Integer recommendations   = requireNonNull(() -> schema.getInt("num_recommendations"));

                // API methods

                @Override
                public final MangaPreview getMangaPreview() {
                    return manga;
                }

                @Override
                public final Integer getRecommendations() {
                    return recommendations;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.mal4j.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final String toString(){
                    return "MangaRecommendation{" +
                           "manga=" + manga +
                           ", recommendations=" + recommendations +
                           '}';
                }

            };
        }

        static RelatedManga asRelatedManga(final MyAnimeList mal, final JsonObject schema){
            return new RelatedManga() {

                private final MangaPreview manga            = requireNonNull(() -> asMangaPreview(mal, schema.getJsonObject("node")));
                private final RelationType relationType     = requireNonNull(() -> RelationType.asEnum(schema.getString("relation_type")));
                private final String relationTypeFormatted  = requireNonNull(() -> schema.getString("relation_type_formatted"));

                // API methods

                @Override
                public final MangaPreview getMangaPreview() {
                    return manga;
                }

                @Override
                public final RelationType getRelationType() {
                    return relationType;
                }

                @Override
                public final String getRelationTypeFormat() {
                    return relationTypeFormatted;
                }

                // API methods

                @Override
                public final com.kttdevelopment.mal4j.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final String toString(){
                    return "RelatedManga{" +
                           "manga=" + manga +
                           ", relationType=" + relationType +
                           ", relationTypeFormatted='" + relationTypeFormatted + '\'' +
                           '}';
                }

            };
        }

    }

    static abstract class User {

        static com.kttdevelopment.mal4j.user.User asUser(final MyAnimeList mal, final JsonObject schema){
            return new com.kttdevelopment.mal4j.user.User() {

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

    static abstract class Common {

        static AlternativeTitles asAlternativeTitles(final MyAnimeList mal, final JsonObject schema){
            return new AlternativeTitles() {

                private final String[] synonyms = requireNonNull(() -> schema.getStringArray("synonyms"));
                private final String english    = requireNonNull(() -> schema.getString("en"));
                private final String japanese   = requireNonNull(() -> schema.getString("ja"));

                // API methods

                @Override
                public final String[] getSynonyms() {
                    return synonyms;
                }

                @Override
                public final String getEnglish() {
                    return english;
                }

                @Override
                public final String getJapanese() {
                    return japanese;
                }

                //

                @Override
                public final String toString(){
                    return "AlternativeTitles{" +
                           "synonyms=" + Arrays.toString(synonyms) +
                           ", english='" + english + '\'' +
                           ", japanese='" + japanese + '\'' +
                           '}';
                }

            };
        }

        static Picture asPicture(final MyAnimeList mal, final JsonObject schema){
            return new Picture() {

                private final String medium = requireNonNull(() -> schema.getString("medium"));
                private final String large  = requireNonNull(() -> schema.getString("large"));

                // API methods

                @Override
                public final String getMediumURL() {
                    return medium;
                }

                @Override
                public final String getLargeURL() {
                    return large;
                }

                // additional methods

                @Override
                public final String toString(){
                    return "Picture{" +
                           "medium='" + medium + '\'' +
                           ", large='" + large + '\'' +
                           '}';
                }

            };
        }

    }

    //

    private static <R> R[] adaptList(final JsonObject[] list, final Function<JsonObject,R> adapter, final Class<R> Class){
        final List<R> li = new ArrayList<>();
        for(final JsonObject o : list)
            li.add(adapter.apply(o));

        final R[] array = (R[]) Array.newInstance(Class, list.length);

        final int len = array.length;
        for(int i = 0; i < len; i++)
            array[i] = li.get(i);

        return array;
    }

    static final String YMD = "yyyy-MM-dd";
    static final String YM  = "yyyy-MM";
    static final String Y   = "yyyy";

    private static Long parseDate(final String date){
        if(date == null) return null;

        final int len = date.length();
        final DateFormat df = new SimpleDateFormat(len == 10 ? YMD : len == 7 ? YM : Y);

        try{
            return df.parse(date).getTime();
        }catch(final ParseException ignored){
            return null;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    static final String ISO8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";

    private static Long parseISO8601(final String timestamp){
        if(timestamp == null) return null;

        try{
            return new SimpleDateFormat(ISO8601).parse(timestamp).getTime();
        }catch(final ParseException ignored){
            return null;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static Time asTime(final String time){
        final String[] hhmm = time.split(":");
        final int h         = Integer.parseInt(hhmm[0]);
        final int m         = Integer.parseInt(hhmm[1]);

        return new Time() {

            private final Integer hour      = h;
            private final Integer hour12    = h > 12 ? h - 12 : h == 0 ? 12 : h;
            private final Boolean am        = hour <= 12;
            private final Integer minute    = m;

            @Override
            public final Integer getHour() {
                return hour;
            }

            @Override
            public final Integer get12Hour() {
                return hour12;
            }

            @Override
            public final Boolean isAM() {
                return am;
            }

            @Override
            public final Boolean isPM() {
                return !am;
            }

            @Override
            public final Integer getMinute() {
                return minute;
            }

            //

            @Override
            public final String toString(){
                return "Time{" +
                       "hour=" + hour +
                       ", hour12=" + hour12 +
                       ", am=" + am +
                       ", minute=" + minute +
                       '}';
            }
        };
    }

    //

    private static <T> T requireNonNull(final Supplier<T> obj){
        try{
            return obj.get();
        }catch (final NullPointerException ignored){
            return null;
        }
    }

}
