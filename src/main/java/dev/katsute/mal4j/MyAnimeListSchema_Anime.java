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
import dev.katsute.mal4j.anime.*;
import dev.katsute.mal4j.anime.property.*;
import dev.katsute.mal4j.anime.property.time.*;
import dev.katsute.mal4j.manga.RelatedManga;
import dev.katsute.mal4j.property.*;
import dev.katsute.mal4j.query.AnimeListUpdate;

import java.util.*;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Anime extends MyAnimeListSchema {

    static AnimeStatistics asAnimeStatistics(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new AnimeStatistics() {

            private final Integer   watching    = schema.getJsonObject("status").getInt("watching"),
                                    completed   = schema.getJsonObject("status").getInt("completed"),
                                    onHold      = schema.getJsonObject("status").getInt("on_hold"),
                                    dropped     = schema.getJsonObject("status").getInt("dropped"),
                                    planToWatch = schema.getJsonObject("status").getInt("plan_to_watch"),
                                    userCount   = schema.getInt("num_list_users");

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
        return schema == null ? null : new Broadcast() {

            private final DayOfWeek dayOfWeek   = DayOfWeek.asEnum(schema.getString("day_of_the_week"));
            private final Time time             = asTime(schema.getString("start_time"));

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
        return schema == null ? null : new StartSeason() {

            private final Integer year  = schema.getInt("year");
            private final Season season = Season.asEnum(schema.getString("season"));

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
        return schema == null ? null : new Studio() {

            private final Long id       = schema.getLong("id");
            private final String name   = schema.getString("name");

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

    static OpeningTheme asOpeningTheme(final MyAnimeList mal, final JsonObject schema, final Anime anime){
        return schema == null ? null : new OpeningTheme() {

            private final Long id       = schema.getLong("id");
            private final String text   = schema.getString("text");

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
            public final Anime getAnime(){
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

    static EndingTheme asEndingTheme(final MyAnimeList mal, final JsonObject schema, final Anime anime){
        return schema == null ? null : new EndingTheme() {

            private final Long id       = schema.getLong("id");
            private final String text   = schema.getString("text");

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
            public final Anime getAnime(){
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

    static Video asVideo(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Video(){

            private final Long id          = schema.getLong("id");
            private final String title     = schema.getString("title");
            private final String url       = schema.getString("url");
            private final String thumbnail = schema.getString("thumbnail");
            private final Long createdAt   = schema.getLong("created_at");
            private final Long updatedAt   = schema.getLong("updated_at");

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final String getURL(){
                return url;
            }

            @Override
            public final String getThumbnail(){
                return thumbnail;
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

            //

            @Override
            public final String toString(){
                return "Video{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", url='" + url + '\'' +
                       ", thumbnail='" + thumbnail + '\'' +
                       ", createdAt=" + createdAt +
                       ", updatedAt=" + updatedAt +
                       '}';
            }

        };
    }

    static Anime asAnime(final MyAnimeList mal, final JsonObject schema){
        return asAnime(mal, schema, false);
    }

    private static Anime asAnime(final MyAnimeList mal, final JsonObject schema, final boolean isPreview){
        return schema == null ? null : new Anime() {

            private final Long id               = schema.getLong("id");
            private final String title          = schema.getString("title");
            private final Picture mainPicture   = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));
            private final AlternativeTitles alternativeTitles
                                                = MyAnimeListSchema_Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles"));
            private final NullableDate startDate = parseNullableDate(schema.getString("start_date"));
            private final NullableDate endDate  = parseNullableDate(schema.getString("end_date"));
            private final String synopsis       = schema.getString("synopsis");
            private final Float meanRating      = schema.getFloat("mean");
            private final Integer rank          = schema.getInt("rank");
            private final Integer popularity    = schema.getInt("popularity");
            private final Integer usersListing  = schema.getInt("num_list_users");
            private final Integer usersScoring  = schema.getInt("num_scoring_users");
            private final String nsfw           = schema.getString("nsfw");
            private final NSFW e_nsfw           = NSFW.asEnum(nsfw);
            private final Genre[] genres        = adaptList(schema.getJsonArray("genres"), g -> MyAnimeListSchema_Common.asGenre(mal, g, true), Genre.class);
            private final Long createdAt        = parseISO8601(schema.getString("created_at"));
            private final Long updatedAt        = parseISO8601(schema.getString("updated_at"));
            private final String type           = schema.getString("media_type");
            private final AnimeType e_type      = AnimeType.asEnum(type);
            private final String status         = schema.getString("status");
            private final AnimeAirStatus e_status
                                                = AnimeAirStatus.asEnum(status);
            private final AnimeListStatus listStatus
                                                = asAnimeListStatus(mal, schema.getJsonObject("my_list_status"), id, this);
            private final Integer episodes      = schema.getInt("num_episodes");
            private final StartSeason startSeason
                                                = asStartSeason(mal, schema.getJsonObject("start_season"));
            private final Broadcast broadcast   = asBroadcast(mal, schema.getJsonObject("broadcast"));
            private final String source         = schema.getString("source");
            private final AnimeSource e_source  = AnimeSource.asEnum(source);
            private final Integer episodeLength = schema.getInt("average_episode_duration");
            private final String rating         = schema.getString("rating");
            private final AnimeRating e_rating  = AnimeRating.asEnum(rating);
            private final Studio[] studios      = adaptList(schema.getJsonArray("studios"), s -> asStudio(mal, s), Studio.class);

            // full only

            private boolean isFull = !isPreview;
            @SuppressWarnings("DataFlowIssue")
            private void populate(){
                if(!isFull){
                    final Anime anime = mal.getAnime(id);

                    pictures        = anime.getPictures();
                    background      = anime.getBackground();
                    relatedAnime    = anime.getRelatedAnime();
                    relatedManga    = anime.getRelatedManga();
                    recommendations = anime.getRecommendations();
                    statistics      = anime.getStatistics();
                    openingThemes   = anime.getOpeningThemes();
                    endingThemes    = anime.getEndingThemes();
                    videos          = anime.getVideos();

                    isFull = true;
                }
            }

            @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
            private boolean isPopulate(){
                final String ln = new Exception().getStackTrace()[2].toString();
                return ln.startsWith("dev.katsute.mal4j.MyAnimeListSchema_Anime") && ln.substring(43).startsWith(".populate(MyAnimeListSchema_Anime.java:");
            }

            private Picture[] pictures          = adaptList(schema.getJsonArray("pictures"), p -> MyAnimeListSchema_Common.asPicture(mal, p), Picture.class);
            private String background           = schema.getString("background");
            private RelatedAnime[] relatedAnime = adaptList(schema.getJsonArray("related_anime"), a -> asRelatedAnime(mal, a), RelatedAnime.class);
            private RelatedManga[] relatedManga = adaptList(schema.getJsonArray("related_manga"), m -> MyAnimeListSchema_Manga.asRelatedManga(mal, m), RelatedManga.class);
            private AnimeRecommendation[] recommendations
                                                = adaptList(schema.getJsonArray("recommendations"), r -> asAnimeRecommendation(mal, r), AnimeRecommendation.class);
            private AnimeStatistics statistics  = asAnimeStatistics(mal, schema.getJsonObject("statistics"));
            private OpeningTheme[] openingThemes
                                                = adaptList(schema.getJsonArray("opening_themes"), o -> asOpeningTheme(mal, o, this), OpeningTheme.class);
            private EndingTheme[] endingThemes  = adaptList(schema.getJsonArray("ending_themes"), o -> asEndingTheme(mal, o, this), EndingTheme.class);
            private Video[] videos              = adaptList(schema.getJsonArray("videos"), o -> asVideo(mal, o), Video.class);

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
            public final NullableDate getStartDate() {
                return startDate;
            }

            @Override
            public final NullableDate getEndDate() {
                return endDate;
            }

            @Override
            public final String getSynopsis() {
                return synopsis;
            }

            @Override
            public final Float getMeanRating() {
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
                return e_nsfw;
            }

            @Override
            public final String getRawNSFW(){
                return nsfw;
            }

            @Override
            public final Genre[] getGenres() {
                return genres != null ? Arrays.copyOf(genres, genres.length) : null;
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
                return e_type;
            }

            @Override
            public final String getRawType(){
                return type;
            }

            @Override
            public final AnimeAirStatus getStatus() {
                return e_status;
            }

            @Override
            public final String getRawStatus(){
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
                return e_source;
            }

            @Override
            public final String getRawSource(){
                return source;
            }

            @Override
            public final Integer getAverageEpisodeLength() {
                return episodeLength;
            }

            @Override
            public final AnimeRating getRating() {
                return e_rating;
            }

            @Override
            public final String getRawRating(){
                return rating;
            }

            @Override
            public final Studio[] getStudios() {
                return studios != null ? Arrays.copyOf(studios, studios.length) : null;
            }

            // full only

            @Override
            public final Picture[] getPictures() {
                if(!isFull) populate();
                return pictures != null ? Arrays.copyOf(pictures, pictures.length) : null;
            }

            @Override
            public final String getBackground() {
                if(!isFull) populate();
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime() {
                if(!isFull) populate();
                return relatedAnime != null ? Arrays.copyOf(relatedAnime, relatedAnime.length) : null;
            }

            @Override
            public final RelatedManga[] getRelatedManga() {
                if(!isFull) populate();
                return relatedManga != null ? Arrays.copyOf(relatedManga, relatedManga.length) : null;
            }

            @Override
            public final AnimeRecommendation[] getRecommendations() {
                if(!isFull) populate();
                return recommendations != null ? Arrays.copyOf(recommendations, recommendations.length) : null;
            }

            @Override
            public final AnimeStatistics getStatistics() {
                if(!isFull) populate();
                return statistics;
            }

            @Override
            public final OpeningTheme[] getOpeningThemes(){
                if(!isPopulate())
                    ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.OP_ED_THEMES);
                if(!isFull) populate();
                return openingThemes != null ? Arrays.copyOf(openingThemes, openingThemes.length) : null;
            }

            @Override
            public final EndingTheme[] getEndingThemes(){
                if(!isPopulate())
                    ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.OP_ED_THEMES);
                if(!isFull) populate();
                return endingThemes != null ? Arrays.copyOf(endingThemes, endingThemes.length) : null;
            }

            @Override
            public final Video[] getVideos(){
                if(!isPopulate())
                    ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.VIDEOS);
                if(!isFull) populate();
                return videos != null ? Arrays.copyOf(videos, videos.length) : null;
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
                       ", videos=" + Arrays.toString(videos) +
                       '}';
            }

        };
    }

    static Anime asAnimePreview(final MyAnimeList mal, final JsonObject schema){
        return asAnime(mal, schema, true);
    }

    static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final long anime_id){
        return asAnimeListStatus(mal, schema, anime_id, null);
    }

    static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final Anime anime){
        return asAnimeListStatus(mal, schema, null, Objects.requireNonNull(anime, "Anime must not be null"));
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final Long anime_id, final Anime anime_full) {
        if(anime_id == null && anime_full == null)
            throw new NullPointerException("Anime and ID must not both be null");
        return schema == null ? null : new AnimeListStatus() {

            private Anime anime                     = anime_full;
            private final Long id                   = anime_id != null ? anime_id : anime_full.getID();

            private final String status             = schema.getString("status");
            private final AnimeStatus e_status      = AnimeStatus.asEnum(status);
            private final Integer score             = schema.getInt("score");
            private final Long startDate            = parseDate(schema.getString("start_date"));
            private final Long finishDate           = parseDate(schema.getString("finish_date"));
            private final Integer priority          = schema.getInt("priority");
            private final Priority e_priority       = Priority.asEnum(priority);
            private final String[] tags             = schema.getStringArray("tags");
            private final String comments           = schema.getString("comments");
            private final Long updatedAt            = parseISO8601(schema.getString("updated_at"));
            private final Integer watchedEpisodes   = schema.getInt("num_episodes_watched");
            private final Boolean rewatching        = schema.getBoolean("is_rewatching");
            private final Integer timesRewatched    = schema.getInt("num_times_rewatched");
            private final Integer rewatchValue      = schema.getInt("rewatch_value");
            private final RewatchValue e_rewatchValue
                                                    = RewatchValue.asEnum(rewatchValue);

            // API methods

            @Override
            public final AnimeStatus getStatus() {
                return e_status;
            }

            @Override
            public final String getRawStatus(){
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
                return e_priority;
            }

            @Override
            public final Integer getRawPriority(){
                return priority;
            }

            @Override
            public final String[] getTags() {
                return tags != null ? Arrays.copyOf(tags, tags.length) : null;
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
                return e_rewatchValue;
            }

            @Override
            public final Integer getRawRewatchValue(){
                return rewatchValue;
            }

            // additional methods

            @Override
            public final Anime getAnime(){
                return anime != null ? anime : (anime = mal.getAnime(id));
            }

            @Override
            public final AnimeListUpdate edit(){
                return mal.updateAnimeListing(id != null ? id : anime.getID());
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

    static AnimeRanking asAnimeRanking(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new AnimeRanking() {

            private final Anime anime               = asAnimePreview(mal, schema.getJsonObject("node"));
            private final Integer ranking           = schema.getJsonObject("ranking").getInt("rank");
            private final Integer previousRanking   = schema.getJsonObject("ranking").getInt("previous_rank");

            // API methods

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
            public final Anime getAnime(){
                return anime;
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
        return schema == null ? null : new AnimeRecommendation() {

            private final Anime anime               = asAnimePreview(mal, schema.getJsonObject("node"));
            private final Integer recommendations   = schema.getInt("num_recommendations");

            // API methods

            @Override
            public final Integer getRecommendations() {
                return recommendations;
            }

            // additional methods

            @Override
            public final Anime getAnime() {
                return anime;
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
        return schema == null ? null : new RelatedAnime() {

            private final Anime anime                   = asAnimePreview(mal, schema.getJsonObject("node"));
            private final Long id						= anime.getID();
            private final String relationType           = schema.getString("relation_type");
            private final RelationType e_relationType   = RelationType.asEnum(relationType);
            private final String relationTypeFormatted  = schema.getString("relation_type_formatted");

            // API methods
            public final Long getID(){
                return id;
            }

            @Override
            public final RelationType getRelationType() {
                return e_relationType;
            }

            @Override
            public final String getRawRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat() {
                return relationTypeFormatted;
            }

            // additional methods

            @Override
            public final Anime getAnime() {
                return anime;
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
