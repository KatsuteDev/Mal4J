/*
 * Copyright (C) 2025 Katsute <https://github.com/Katsute>
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
import dev.katsute.mal4j.anime.property.time.DayOfWeek;
import dev.katsute.mal4j.anime.property.time.Season;
import dev.katsute.mal4j.anime.property.time.Time;
import dev.katsute.mal4j.manga.RelatedManga;
import dev.katsute.mal4j.property.*;
import dev.katsute.mal4j.query.AnimeCharacterQuery;
import dev.katsute.mal4j.query.AnimeListUpdate;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Anime extends MyAnimeListSchema {

    static AnimeStatistics asAnimeStatistics(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new AnimeStatistics(){

            private final Integer   watching    = schema.getJsonObject("status").getInt("watching"),
                                    completed   = schema.getJsonObject("status").getInt("completed"),
                                    onHold      = schema.getJsonObject("status").getInt("on_hold"),
                                    dropped     = schema.getJsonObject("status").getInt("dropped"),
                                    planToWatch = schema.getJsonObject("status").getInt("plan_to_watch"),
                                    userCount   = schema.getInt("num_list_users");

            // API methods

            @Override
            public final Integer getWatching(){
                return watching;
            }

            @Override
            public final Integer getCompleted(){
                return completed;
            }

            @Override
            public final Integer getOnHold(){
                return onHold;
            }

            @Override
            public final Integer getDropped(){
                return dropped;
            }

            @Override
            public final Integer getPlanToWatch(){
                return planToWatch;
            }

            @Override
            public final Integer getUserCount(){
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
        return schema == null ? null : new Broadcast(){

            private final DayOfWeek dayOfWeek   = DayOfWeek.asEnum(schema.getString("day_of_the_week"));
            private final Time time             = asTime(schema.getString("start_time"));

            // API methods

            @Override
            public final DayOfWeek getDayOfWeek(){
                return dayOfWeek;
            }

            @Override
            public final Time getStartTime(){
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
        return schema == null ? null : new StartSeason(){

            private final Integer year  = schema.getInt("year");
            private final Season season = Season.asEnum(schema.getString("season"));

            // API methods

            @Override
            public final Integer getYear(){
                return year;
            }

            @Override
            public final Season getSeason(){
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
        return schema == null ? null : new Studio(){

            private final Long id       = schema.getLong("id");
            private final String name   = schema.getString("name");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getName(){
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
        return schema == null ? null : new OpeningTheme(){

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
        return schema == null ? null : new EndingTheme(){

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
            public final Date getCreatedAt(){
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                return createdAt;
            }

            @Override
            public final Date getUpdatedAt(){
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
        return schema == null ? null : new Anime(){

            boolean draft = true; // if any field is null, try and fetch full values (only try once)

            private final Long id = schema.getLong("id");
            private String title;
            private Picture mainPicture;
            private AlternativeTitles alternativeTitles;
            private NullableDate startDate;
            private NullableDate endDate;
            private String synopsis;
            private Float meanRating;
            private Integer rank;
            private Integer popularity;
            private Integer usersListing;
            private Integer usersScoring;
            private String nsfw;
            private NSFW e_nsfw;
            private Genre[] genres;
            private Long createdAt;
            private Long updatedAt;
            private String type;
            private AnimeType e_type;
            private String status;
            private AnimeAirStatus e_status;
            private AnimeListStatus listStatus;
            private Integer episodes;
            private StartSeason startSeason;
            private Broadcast broadcast;
            private String source;
            private AnimeSource e_source;
            private Integer episodeLength;
            private String rating;
            private AnimeRating e_rating;
            private Studio[] studios;

            private Picture[] pictures;
            private String background;
            private RelatedAnime[] relatedAnime;
            private RelatedManga[] relatedManga;
            private AnimeRecommendation[] recommendations;
            private AnimeStatistics statistics;
            private OpeningTheme[] openingThemes;
            private EndingTheme[] endingThemes;
            private Video[] videos;

            {
                populate(schema);
            }

            @SuppressWarnings("DataFlowIssue")
            private void populate(){
                if(draft){
                    draft = false;
                    populate(((MyAnimeListImpl) mal).getAnimeSchema(id, ((String[]) null)));
                }
            }

            private void populate(final JsonObject schema){
                title             = schema.getString("title");
                mainPicture       = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));
                alternativeTitles = MyAnimeListSchema_Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles"));
                startDate         = parseNullableDate(schema.getString("start_date"));
                endDate           = parseNullableDate(schema.getString("end_date"));
                synopsis          = schema.getString("synopsis");
                meanRating        = schema.getFloat("mean");
                rank              = schema.getInt("rank");
                popularity        = schema.getInt("popularity");
                usersListing      = schema.getInt("num_list_users");
                usersScoring      = schema.getInt("num_scoring_users");
                nsfw              = schema.getString("nsfw");
                e_nsfw            = NSFW.asEnum(nsfw);
                genres            = adaptList(schema.getJsonArray("genres"), g -> MyAnimeListSchema_Common.asGenre(mal, g, true), Genre.class);
                createdAt         = parseISO8601(schema.getString("created_at"));
                updatedAt         = parseISO8601(schema.getString("updated_at"));
                type              = schema.getString("media_type");
                e_type            = AnimeType.asEnum(type);
                status            = schema.getString("status");
                e_status          = AnimeAirStatus.asEnum(status);
                listStatus        = asAnimeListStatus(mal, schema.getJsonObject("my_list_status"), id, this);
                episodes          = schema.getInt("num_episodes");
                startSeason       = asStartSeason(mal, schema.getJsonObject("start_season"));
                broadcast         = asBroadcast(mal, schema.getJsonObject("broadcast"));
                source            = schema.getString("source");
                e_source          = AnimeSource.asEnum(source);
                episodeLength     = schema.getInt("average_episode_duration");
                rating            = schema.getString("rating");
                e_rating          = AnimeRating.asEnum(rating);
                studios           = adaptList(schema.getJsonArray("studios"), s -> asStudio(mal, s), Studio.class);

                pictures          = adaptList(schema.getJsonArray("pictures"), p -> MyAnimeListSchema_Common.asPicture(mal, p), Picture.class);
                background        = schema.getString("background");
                relatedAnime      = adaptList(schema.getJsonArray("related_anime"), a -> asRelatedAnime(mal, a), RelatedAnime.class);
                relatedManga      = adaptList(schema.getJsonArray("related_manga"), m -> MyAnimeListSchema_Manga.asRelatedManga(mal, m), RelatedManga.class);
                recommendations   = adaptList(schema.getJsonArray("recommendations"), r -> asAnimeRecommendation(mal, r), AnimeRecommendation.class);
                statistics        = asAnimeStatistics(mal, schema.getJsonObject("statistics"));
                openingThemes     = adaptList(schema.getJsonArray("opening_themes"), o -> asOpeningTheme(mal, o, this), OpeningTheme.class);
                endingThemes      = adaptList(schema.getJsonArray("ending_themes"), o -> asEndingTheme(mal, o, this), EndingTheme.class);
                videos            = adaptList(schema.getJsonArray("videos"), o -> asVideo(mal, o), Video.class);
            }

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                if(title == null && draft)
                    populate();
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                if(mainPicture == null && draft)
                    populate();
                return mainPicture;
            }

            @Override
            public final AlternativeTitles getAlternativeTitles(){
                if(alternativeTitles == null && draft)
                    populate();
                return alternativeTitles;
            }

            @Override
            public final NullableDate getStartDate(){
                if(startDate == null && draft)
                    populate();
                return startDate;
            }

            @Override
            public final NullableDate getEndDate(){
                if(endDate == null && draft)
                    populate();
                return endDate;
            }

            @Override
            public final String getSynopsis(){
                if(synopsis == null && draft)
                    populate();
                return synopsis;
            }

            @Override
            public final Float getMeanRating(){
                if(meanRating == null && draft)
                    populate();
                return meanRating;
            }

            @Override
            public final Integer getRank(){
                if(rank == null && draft)
                    populate();
                return rank;
            }

            @Override
            public final Integer getPopularity(){
                if(popularity == null && draft)
                    populate();
                return popularity;
            }

            @Override
            public final Integer getUserListingCount(){
                if(usersListing == null && draft)
                    populate();
                return usersListing;
            }

            @Override
            public final Integer getUserScoringCount(){
                if(usersScoring == null && draft)
                    populate();
                return usersScoring;
            }

            @Override
            public final NSFW getNSFW(){
                if(e_nsfw == null && draft)
                    populate();
                return e_nsfw;
            }

            @Override
            public final String getRawNSFW(){
                if(nsfw == null && draft)
                    populate();
                return nsfw;
            }

            @Override
            public final Genre[] getGenres(){
                if(genres == null && draft)
                    populate();
                return genres != null ? Arrays.copyOf(genres, genres.length) : null;
            }

            @Override
            public final Date getCreatedAt(){
                if(createdAt == null && draft)
                    populate();
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                if(createdAt == null && draft)
                    populate();
                return createdAt;
            }

            @Override
            public final Date getUpdatedAt(){
                if(updatedAt == null && draft)
                    populate();
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                if(updatedAt == null && draft)
                    populate();
                return updatedAt;
            }

            @Override
            public final AnimeType getType(){
                if(e_type == AnimeType.Unknown && draft)
                    populate();
                return e_type;
            }

            @Override
            public final String getRawType(){
                if(type == null && draft)
                    populate();
                return type;
            }

            @Override
            public final AnimeAirStatus getStatus(){
                if(e_status == AnimeAirStatus.Unknown && draft)
                    populate();
                return e_status;
            }

            @Override
            public final String getRawStatus(){
                if(status == null && draft)
                    populate();
                return status;
            }

            @Override
            public final AnimeListStatus getListStatus(){
                if(listStatus == null && draft)
                    populate();
                return listStatus;
            }

            @Override
            public final Integer getEpisodes(){
                if(episodes == null && draft)
                    populate();
                return episodes;
            }

            @Override
            public final StartSeason getStartSeason(){
                if(startSeason == null && draft)
                    populate();
                return startSeason;
            }

            @Override
            public final Broadcast getBroadcast(){
                if(broadcast == null && draft)
                    populate();
                return broadcast;
            }

            @Override
            public final AnimeSource getSource(){
                if(e_source == AnimeSource.Unknown && draft)
                    populate();
                return e_source;
            }

            @Override
            public final String getRawSource(){
                if(source == null && draft)
                    populate();
                return source;
            }

            @Override
            public final Integer getAverageEpisodeLength(){
                if(episodeLength == null && draft)
                    populate();
                return episodeLength;
            }

            @Override
            public final AnimeRating getRating(){
                if(e_rating == null && draft)
                    populate();
                return e_rating;
            }

            @Override
            public final String getRawRating(){
                if(rating == null && draft)
                    populate();
                return rating;
            }

            @Override
            public final Studio[] getStudios(){
                if(studios == null && draft)
                    populate();
                return studios != null ? Arrays.copyOf(studios, studios.length) : null;
            }

            // full only

            @Override
            public final Picture[] getPictures(){
                if(pictures == null && draft)
                    populate();
                return pictures != null ? Arrays.copyOf(pictures, pictures.length) : null;
            }

            @Override
            public final String getBackground(){
                if(background == null && draft)
                    populate();
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime(){
                if(relatedAnime == null && draft)
                    populate();
                return relatedAnime != null ? Arrays.copyOf(relatedAnime, relatedAnime.length) : null;
            }

            @Override
            public final RelatedManga[] getRelatedManga(){
                if(relatedManga == null && draft)
                    populate();
                return relatedManga != null ? Arrays.copyOf(relatedManga, relatedManga.length) : null;
            }

            @Override
            public final AnimeRecommendation[] getRecommendations(){
                if(recommendations == null && draft)
                    populate();
                return recommendations != null ? Arrays.copyOf(recommendations, recommendations.length) : null;
            }

            @Override
            public final AnimeStatistics getStatistics(){
                if(statistics == null && draft)
                    populate();
                return statistics;
            }

            @Override
            public final OpeningTheme[] getOpeningThemes(){
                ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.OP_ED_THEMES);
                if(openingThemes == null && draft)
                    populate();
                return openingThemes != null ? Arrays.copyOf(openingThemes, openingThemes.length) : null;
            }

            @Override
            public final EndingTheme[] getEndingThemes(){
                ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.OP_ED_THEMES);
                if(endingThemes == null && draft)
                    populate();
                return endingThemes != null ? Arrays.copyOf(endingThemes, endingThemes.length) : null;
            }

            @Override
            public final Video[] getVideos(){
                ((MyAnimeListImpl) mal).checkExperimentalFeatureEnabled(ExperimentalFeature.VIDEOS);
                if(videos == null && draft)
                    populate();
                return videos != null ? Arrays.copyOf(videos, videos.length) : null;
            }

            // additional methods

            @SuppressWarnings("DataFlowIssue")
            @Override
            public final AnimeCharacterQuery getCharacters(){
                return mal.getAnimeCharacters(id);
            }

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

    static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final long anime_id){
        return asAnimeListStatus(mal, schema, anime_id, null);
    }

    static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final Anime anime){
        return asAnimeListStatus(mal, schema, null, Objects.requireNonNull(anime, "Anime must not be null"));
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final JsonObject schema, final Long anime_id, final Anime anime_full){
        if(anime_id == null && anime_full == null)
            throw new NullPointerException("Anime and ID must not both be null");
        return schema == null ? null : new AnimeListStatus(){

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
            public final AnimeStatus getStatus(){
                return e_status;
            }

            @Override
            public final String getRawStatus(){
                return status;
            }

            @Override
            public final Integer getScore(){
                return score;
            }

            @Override
            public final Date getStartDate(){
                return startDate == null ? null : new Date(startDate);
            }

            @Override
            public final Date getFinishDate(){
                return finishDate == null ? null : new Date(finishDate);
            }

            @Override
            public final Priority getPriority(){
                return e_priority;
            }

            @Override
            public final Integer getRawPriority(){
                return priority;
            }

            @Override
            public final String[] getTags(){
                return tags != null ? Arrays.copyOf(tags, tags.length) : null;
            }

            @Override
            public final String getComments(){
                return comments;
            }

            @Override
            public final Date getUpdatedAt(){
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                return updatedAt;
            }

            @Override
            public final Integer getWatchedEpisodes(){
                return watchedEpisodes;
            }

            @Override
            public final Boolean isRewatching(){
                return rewatching;
            }

            @Override
            public final Integer getTimesRewatched(){
                return timesRewatched;
            }

            @Override
            public final RewatchValue getRewatchValue(){
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
        return schema == null ? null : new AnimeRanking(){

            private final Anime anime               = asAnime(mal, schema.getJsonObject("node"));
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
        return schema == null ? null : new AnimeRecommendation(){

            private final Anime anime               = asAnime(mal, schema.getJsonObject("node"));
            private final Integer recommendations   = schema.getInt("num_recommendations");

            // API methods

            @Override
            public final Integer getRecommendations(){
                return recommendations;
            }

            // additional methods

            @Override
            public final Anime getAnime(){
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
        return schema == null ? null : new RelatedAnime(){

            private final Anime anime                   = asAnime(mal, schema.getJsonObject("node"));
            private final String relationType           = schema.getString("relation_type");
            private final RelationType e_relationType   = RelationType.asEnum(relationType);
            private final String relationTypeFormatted  = schema.getString("relation_type_formatted");

            // API methods

            @Override
            public final RelationType getRelationType(){
                return e_relationType;
            }

            @Override
            public final String getRawRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat(){
                return relationTypeFormatted;
            }

            // additional methods

            @Override
            public final Anime getAnime(){
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