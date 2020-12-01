package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.Call;
import com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.SubLevelObject;
import com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.TopLevelObject;
import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.DayOfWeek;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.anime.property.time.Time;
import com.kttdevelopment.myanimelist.forum.*;
import com.kttdevelopment.myanimelist.forum.property.*;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.*;
import com.kttdevelopment.myanimelist.query.AnimeListUpdate;
import com.kttdevelopment.myanimelist.query.MangaListUpdate;
import com.kttdevelopment.myanimelist.user.UserAnimeStatistics;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.Common.IDN;

/**
 * Represents the MyAnimeList API response as an object.
 *
 * @see MyAnimeListAPIResponse
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("unused")
abstract class MyAnimeListAPIResponseMapping {

    static abstract class Anime {

        static AnimeStatistics asAnimeStatistics(final MyAnimeList mal, final Call.GetAnime.Statistics schema){
            return new AnimeStatistics() {

                private final int   watching    = requireNonNullElse(() -> schema.status.watching, 0),
                                    completed   = requireNonNullElse(() -> schema.status.completed, 0),
                                    onHold      = requireNonNullElse(() -> schema.status.on_hold, 0),
                                    dropped     = requireNonNullElse(() -> schema.status.dropped, 0),
                                    planToWatch = requireNonNullElse(() -> schema.status.plan_to_watch, 0),
                                    userCount   = requireNonNullElse(() -> schema.num_list_users, -1);

                // API methods

                @Override
                public final int getWatching() {
                    return watching;
                }

                @Override
                public final int getCompleted() {
                    return completed;
                }

                @Override
                public final int getOnHold() {
                    return onHold;
                }

                @Override
                public final int getDropped() {
                    return dropped;
                }

                @Override
                public final int getPlanToWatch() {
                    return planToWatch;
                }

                @Override
                public final int getUserCount() {
                    return userCount;
                }

                // additional methods

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static Broadcast asBroadcast(final MyAnimeList mal, final TopLevelObject.Anime.Broadcast schema){
            return new Broadcast() {

                private final DayOfWeek dayOfWeek   = requireNonNull(() -> DayOfWeek.asEnum(schema.day_of_the_week));
                private final Time time             = requireNonNull(() -> asTime(schema.start_time));

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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static StartSeason asStartSeason(final MyAnimeList mal, final TopLevelObject.Anime.StartSeason schema){
            return new StartSeason() {

                private final int year      = requireNonNullElse(() -> schema.year, -1);
                private final Season season = requireNonNull(() -> Season.asEnum(schema.season));

                // API methods

                @Override
                public final int getYear() {
                    return year;
                }

                @Override
                public final Season getSeason() {
                    return season;
                }

                // additional methods

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static Studio asStudio(final MyAnimeList mal, final IDN schema){
            return new Studio() {

                private final long id       = requireNonNullElse(() -> schema.id, -1L);
                private final String name   = requireNonNull(() -> schema.name);

                // API methods

                @Override
                public final long getID() {
                    return id;
                }

                @Override
                public final String getName() {
                    return name;
                }

                // additional methods

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static com.kttdevelopment.myanimelist.anime.Anime asAnime(final MyAnimeList mal, final Call.GetAnime schema){
            return new com.kttdevelopment.myanimelist.anime.Anime() {

                private final long id               = requireNonNullElse(() -> schema.id, -1L);
                private final String title          = requireNonNull(() -> schema.title);
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.main_picture));
                private final AlternativeTitles alternativeTitles = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.alternative_titles));
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long endDate          = requireNonNullElse(() -> parseDate(schema.end_date), -1L);
                private final String synopsis       = requireNonNull(() -> schema.synopsis);
                private final float meanRating      = requireNonNullElse(() -> schema.mean, 0f);
                private final int rank              = requireNonNullElse(() -> schema.rank, -1);
                private final int popularity        = requireNonNullElse(() -> schema.popularity, -1);
                private final int usersListing      = requireNonNullElse(() -> schema.num_list_users, -1);
                private final int usersScoring      = requireNonNullElse(() -> schema.num_scoring_users, -1);
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.nsfw));
                private final Genre[] genres        = requireNonNull(() -> adaptArray(schema.genres, g -> Genre.asEnum((int) g.id), Genre.class));
                private final long createdAt        = requireNonNullElse(() -> parseISO8601(schema.created_at), -1L);
                private final long updatedAt        = requireNonNullElse(() -> parseISO8601(schema.updated_at), -1L);
                private final AnimeType type        = requireNonNull(() -> AnimeType.asEnum(schema.media_type));
                private final AnimeAirStatus status = requireNonNull(() -> AnimeAirStatus.asEnum(schema.status));
                private final AnimeListStatus listStatus = requireNonNull(() -> asAnimeListStatus(mal, schema.my_list_status, this));
                private final int episodes          = requireNonNullElse(() -> schema.num_episodes, -1);
                private final StartSeason startSeason = requireNonNull(() -> asStartSeason(mal, schema.start_season));
                private final Broadcast broadcast   = requireNonNull(() -> asBroadcast(mal, schema.broadcast));
                private final AnimeSource source    = requireNonNull(() -> AnimeSource.asEnum(schema.source));
                private final int episodeLength     = requireNonNullElse(() -> schema.average_episode_duration, -1);
                private final AnimeRating rating    = requireNonNull(() -> AnimeRating.asEnum(schema.rating));
                private final Studio[] studios      = requireNonNull(() -> adaptArray(schema.studios, s -> asStudio(mal, s), Studio.class));
                private final Picture[] pictures    = requireNonNull(() -> adaptArray(schema.pictures, p -> Common.asPicture(mal, p), Picture.class));
                private final String background     = requireNonNull(() -> schema.background);
                private final RelatedAnime[] relatedAnime = requireNonNull(() -> adaptArray(schema.related_anime, a -> asRelatedAnime(mal, a), RelatedAnime.class));
                private final RelatedManga[] relatedManga = requireNonNull(() -> adaptArray(schema.related_manga, m -> Manga.asRelatedManga(mal, m), RelatedManga.class));
                private final AnimeRecommendation[] recommendations = requireNonNull(() -> adaptArray(schema.recommendations, r -> asAnimeRecommendation(mal, r), AnimeRecommendation.class));
                private final AnimeStatistics statistics = requireNonNull(() -> asAnimeStatistics(mal, schema.statistics));

                // API methods

                @Override
                public final long getID() {
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
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getEndDate() {
                    return endDate;
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final float getMeanRating() {
                    return meanRating;
                }

                @Override
                public final int getRank() {
                    return rank;
                }

                @Override
                public final int getPopularity() {
                    return popularity;
                }

                @Override
                public final int getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final int getUserScoringCount() {
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
                public final long getCreatedAt() {
                    return createdAt;
                }

                @Override
                public final long getUpdatedAt() {
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
                public final int getEpisodes() {
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
                public final int getAverageEpisodeLength() {
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

                // additional methods

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        @SuppressWarnings("SpellCheckingInspection")
        static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final TopLevelObject.Anime.MyListStatus schema, final AnimePreview anime) {
            return new AnimeListStatus() {

                private final AnimeStatus status    = requireNonNull(() -> AnimeStatus.asEnum(schema.status));
                private final int score             = requireNonNullElse(() -> schema.score, -1);
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long finishDate       = requireNonNullElse(() -> parseDate(schema.finish_date), -1L);
                private final int priority          = requireNonNullElse(() -> schema.priority, -1);
                private final String[] tags         = requireNonNull(() -> schema.tags);
                private final String comments       = requireNonNull(() -> schema.comments);
                private final long updatedAt        = requireNonNull(() -> parseISO8601(schema.updated_at));
                private final int watchedEpisodes   = requireNonNullElse(() -> schema.num_watched_episodes, -1);
                private final boolean rewatching    = requireNonNullElse(() -> schema.is_rewatching, false);
                private final int timesRewatched    = requireNonNullElse(() -> schema.num_times_rewatched, -1);
                private final int rewatchValue      = requireNonNullElse(() -> schema.rewatch_value, -1);

                // API methods

                @Override
                public final AnimeStatus getStatus() {
                    return status;
                }

                @Override
                public final int getScore() {
                    return score;
                }

                @Override
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getFinishDate() {
                    return finishDate;
                }

                @Override
                public final int getPriority() {
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
                public final long getUpdatedAt() {
                    return updatedAt;
                }

                @Override
                public final int getWatchedEpisodes() {
                    return watchedEpisodes;
                }

                @Override
                public final boolean isRewatching() {
                    return rewatching;
                }

                @Override
                public final int getTimesRewatched() {
                    return timesRewatched;
                }

                @Override
                public final int getRewatchValue() {
                    return rewatchValue;
                }

                // additional methods

                @Override
                public final AnimeListUpdate edit(){
                    return mal.updateAnimeListing(anime.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static AnimePreview asAnimePreview(final MyAnimeList mal, final TopLevelObject.Anime schema){
            return new AnimePreview() {

                private final long id               = requireNonNullElse(() -> schema.id, -1L);
                private final String title          = requireNonNull(() -> schema.title);
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.main_picture));
                private final AlternativeTitles alternativeTitles = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.alternative_titles));
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long endDate          = requireNonNullElse(() -> parseDate(schema.end_date), -1L);
                private final String synopsis       = requireNonNull(() -> schema.synopsis);
                private final float meanRating      = requireNonNullElse(() -> schema.mean, 0f);
                private final int rank              = requireNonNullElse(() -> schema.rank, -1);
                private final int popularity        = requireNonNullElse(() -> schema.popularity, -1);
                private final int usersListing      = requireNonNullElse(() -> schema.num_list_users, -1);
                private final int usersScoring      = requireNonNullElse(() -> schema.num_scoring_users, -1);
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.nsfw));
                private final Genre[] genres        = requireNonNull(() -> adaptArray(schema.genres, g -> Genre.asEnum((int) g.id), Genre.class));
                private final long createdAt        = requireNonNullElse(() -> parseISO8601(schema.created_at), -1L);
                private final long updatedAt        = requireNonNullElse(() -> parseISO8601(schema.updated_at), -1L);
                private final AnimeType type        = requireNonNull(() -> AnimeType.asEnum(schema.media_type));
                private final AnimeAirStatus status = requireNonNull(() -> AnimeAirStatus.asEnum(schema.status));
                private final AnimeListStatus listStatus = requireNonNull(() -> asAnimeListStatus(mal, schema.my_list_status, this));
                private final int episodes          = requireNonNullElse(() -> schema.num_episodes, -1);
                private final StartSeason startSeason = requireNonNull(() -> asStartSeason(mal, schema.start_season));
                private final Broadcast broadcast   = requireNonNull(() -> asBroadcast(mal, schema.broadcast));
                private final AnimeSource source    = requireNonNull(() -> AnimeSource.asEnum(schema.source));
                private final int episodeLength     = requireNonNullElse(() -> schema.average_episode_duration, -1);
                private final AnimeRating rating    = requireNonNull(() -> AnimeRating.asEnum(schema.rating));
                private final Studio[] studios      = requireNonNull(() -> adaptArray(schema.studios, s -> asStudio(mal, s), Studio.class));

                // API methods

                @Override
                public final long getID() {
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
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getEndDate() {
                    return endDate;
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final float getMeanRating() {
                    return meanRating;
                }

                @Override
                public final int getRank() {
                    return rank;
                }

                @Override
                public final int getPopularity() {
                    return popularity;
                }

                @Override
                public final int getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final int getUserScoringCount() {
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
                public final long getCreatedAt() {
                    return createdAt;
                }

                @Override
                public final long getUpdatedAt() {
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
                public final int getEpisodes() {
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
                public final int getAverageEpisodeLength() {
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
                public final com.kttdevelopment.myanimelist.anime.Anime getAnime() {
                    return mal.getAnime(id);
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static AnimeRanking asAnimeRanking(final MyAnimeList mal, final SubLevelObject.Ranking<TopLevelObject.Anime> schema){
            return new AnimeRanking() {

                private final AnimePreview anime    = requireNonNull(() -> asAnimePreview(mal, schema.node));
                private final int ranking           = requireNonNullElse(() -> schema.ranking.rank, -1);
                private final int previousRanking   = requireNonNullElse(() -> schema.ranking.previous_rank, -1);

                // API methods

                @Override
                public final AnimePreview getAnimePreview() {
                    return anime;
                }

                @Override
                public final int getRanking() {
                    return ranking;
                }

                @Override
                public final int getPreviousRank() {
                    return previousRanking;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.myanimelist.anime.Anime getAnime() {
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }
            };
        }

        static AnimeRecommendation asAnimeRecommendation(final MyAnimeList mal, final SubLevelObject.Recommendation<TopLevelObject.Anime> schema){
            return new AnimeRecommendation() {

                private final AnimePreview anime    = requireNonNull(() -> asAnimePreview(mal, schema.node));
                private final int recommendations   = requireNonNullElse(() -> schema.num_recommendations, -1);

                // API methods

                @Override
                public final AnimePreview getAnimePreview() {
                    return anime;
                }

                @Override
                public final int getRecommendations() {
                    return recommendations;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.myanimelist.anime.Anime getAnime() {
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static RelatedAnime asRelatedAnime(final MyAnimeList mal, final SubLevelObject.RelatedAnimeEdge schema){
            return new RelatedAnime() {

                private final AnimePreview anime            = requireNonNull(() -> asAnimePreview(mal, schema.node));
                private final RelationType relationType     = requireNonNull(() -> RelationType.asEnum(schema.relation_type));
                private final String relationTypeFormatted  = requireNonNull(() -> schema.relation_type_formatted);

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
                public final com.kttdevelopment.myanimelist.anime.Anime getAnime() {
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        @SuppressWarnings("SpellCheckingInspection")
        static UserAnimeListStatus asUserAnimeListStatus(final MyAnimeList mal, final Call.UpdateUserAnimeList schema, final AnimePreview anime){
            return new UserAnimeListStatus() {

                private final AnimeStatus status    = requireNonNull(() -> AnimeStatus.asEnum(schema.status));
                private final int score             = requireNonNullElse(() -> schema.score, -1);
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long finishDate       = requireNonNullElse(() -> parseDate(schema.end_date), -1L);
                private final int priority          = requireNonNullElse(() -> schema.priority, -1);
                private final String[] tags         = requireNonNull(() -> schema.tags);
                private final String comments       = requireNonNull(() -> schema.comments);
                private final long updatedAt        = requireNonNullElse(() -> parseISO8601(schema.updated_at), -1L);
                private final int watchedEpisodes   = requireNonNullElse(() -> schema.num_episodes_watched, -1);
                private final boolean rewatching    = requireNonNullElse(() -> schema.is_rewatching, false);
                private final int timesRewatched    = requireNonNullElse(() -> schema.num_times_rewatched, -1);
                private final int rewatchValue      = requireNonNullElse(() -> schema.rewatch_value, -1);

                // API methods

                @Override
                public final AnimeStatus getStatus() {
                    return status;
                }

                @Override
                public final int getScore() {
                    return score;
                }

                @Override
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getFinishDate() {
                    return finishDate;
                }

                @Override
                public final int getPriority() {
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
                public final long getUpdatedAt() {
                    return updatedAt;
                }

                @Override
                public final int getWatchedEpisodes() {
                    return watchedEpisodes;
                }

                @Override
                public final boolean isRewatching() {
                    return rewatching;
                }

                @Override
                public final int getTimesRewatched() {
                    return timesRewatched;
                }

                @Override
                public final int getRewatchValue() {
                    return rewatchValue;
                }

                // additional methods

                @Override
                public final AnimeListUpdate edit(){
                    return mal.updateAnimeListing(anime.getID());
                }

                @Override
                public final com.kttdevelopment.myanimelist.anime.Anime getAnime() {
                    return mal.getAnime(anime.getID());
                }

                @Override
                public final AnimePreview getAnimePreview() {
                    return anime;
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

    }

    static abstract class Forum {

        static ForumTopicCreator asForumTopicCreator(final MyAnimeList mal, final IDN schema){
            return new ForumTopicCreator() {

                private final long id = requireNonNullElse(() -> schema.id, -1L);
                private final String name = requireNonNull(() -> schema.name);

                // API methods

                @Override
                public final long getID() {
                    return id;
                }

                @Override
                public final String getName() {
                    return name;
                }

                // additional methods


                @Override
                public final com.kttdevelopment.myanimelist.user.User getUser(){
                    return mal.getUser(name);
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static ForumTopicDetail asForumTopicDetail(final MyAnimeList mal, final TopLevelObject.ForumTopicsData schema){
            return new ForumTopicDetail() {

                private final long id                       = requireNonNullElse(() -> schema.id, -1L);
                private final String title                  = requireNonNull(() -> schema.title);
                private final long createdAt                = requireNonNullElse(() -> parseISO8601(schema.created_at), -1L);
                private final ForumTopicCreator createdBy   = requireNonNull(() -> asForumTopicCreator(mal, schema.created_by));
                private final int posts                     = requireNonNullElse(() -> schema.number_of_posts, -1);
                private final long lastPostedAt             = requireNonNullElse(() -> parseISO8601(schema.last_post_created_at), -1L);
                private final ForumTopicCreator lastPostedBy = requireNonNull(() -> asForumTopicCreator(mal, schema.last_post_created_by));
                private final boolean locked                = requireNonNullElse(() -> schema.is_locked, false);

                // APi methods

                @Override
                public final long getID() {
                    return id;
                }

                @Override
                public final String getTitle() {
                    return title;
                }

                @Override
                public final long getCreatedAt() {
                    return createdAt;
                }

                @Override
                public final ForumTopicCreator getCreatedBy() {
                    return createdBy;
                }

                @Override
                public final int getPostsCount() {
                    return posts;
                }

                @Override
                public final long getLastPostCreatedAt() {
                    return lastPostedAt;
                }

                @Override
                public final ForumTopicCreator getLastPostCreatedBy() {
                    return lastPostedBy;
                }

                @Override
                public final boolean isLocked() {
                    return locked;
                }

                // additional methods

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static Poll asPoll(final MyAnimeList mal, final TopLevelObject.ForumTopicData.Poll schema, final ForumTopic forumTopic){
            return new Poll() {

                private final long id = requireNonNullElse(() -> schema.id, -1L);
                private final String question = requireNonNull(() -> schema.question);
                private final boolean isClosed = requireNonNullElse(() -> schema.close, false);
                private final PollOption[] options = requireNonNull(() -> adaptArray(schema.options, o -> asPollOption(mal, o, this), PollOption.class));

                // API methods

                @Override
                public final long getID() {
                    return id;
                }

                @Override
                public final String getQuestion() {
                    return question;
                }

                @Override
                public final boolean isClosed() {
                    return isClosed;
                }

                @Override
                public final PollOption[] getOptions() {
                    return options;
                }

                // additional methods

                @Override
                public final ForumTopic getForumTopic() {
                    return forumTopic;
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static PollOption asPollOption(final MyAnimeList mal, final TopLevelObject.ForumTopicData.Poll.PollOption schema, final Poll poll){
            return new PollOption() {

                private final long id       = requireNonNullElse(() -> schema.id, -1L);
                private final String text   = requireNonNull(() -> schema.text);
                private final int votes     = requireNonNullElse(() -> schema.votes, -1);

                // API methods

                @Override
                public final long getID() {
                    return 0;
                }

                @Override
                public final String getText() {
                    return text;
                }

                @Override
                public final int getVotes() {
                    return votes;
                }

                // additional methods

                @Override
                public final Poll getPoll() {
                    return poll;
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static PostAuthor asPostAuthor(final MyAnimeList mal, final TopLevelObject.ForumTopicData.Post.PostCreator schema){
            return new PostAuthor() {

                private final long id               = requireNonNullElse(() -> schema.id, -1L);
                private final String name           = requireNonNull(() -> schema.name);
                private final String forumAvatarURL = requireNonNull(() -> schema.forum_avator);

                // API methods

                @Override
                public final long getID() {
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
                public final com.kttdevelopment.myanimelist.user.User getUser() {
                    return mal.getUser(name);
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static ForumBoard asForumBoard(final MyAnimeList mal, final TopLevelObject.ForumCategory.ForumBoard schema, final ForumCategory forumCategory){
            return new ForumBoard() {

                private final long id                   = requireNonNullElse(() -> schema.id, -1L);
                private final String title              = requireNonNull(() -> schema.title);
                private final String description        = requireNonNull(() -> schema.description);
                private final ForumSubBoard[] subBoards = adaptArray(schema.subboards, b -> asForumSubBoard(mal, b, this), ForumSubBoard.class);

                // API methods

                @Override
                public final long getID() {
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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static ForumCategory asForumCategory(final MyAnimeList mal, final TopLevelObject.ForumCategory schema){
            return new ForumCategory() {

                private final String title              = requireNonNull(() -> schema.title);
                private final ForumBoard[] forumBoards  = adaptArray(schema.boards, b -> asForumBoard(mal, b, this), ForumBoard.class);

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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static ForumSubBoard asForumSubBoard(final MyAnimeList mal, final TopLevelObject.ForumCategory.ForumBoard.SubBoard schema, final ForumBoard forumBoard){
            return new ForumSubBoard() {

                private final long id       = requireNonNullElse(() -> schema.id, -1L);
                private final String title  = requireNonNull(() -> schema.title);

                // API methods

                @Override
                public final long getID() {
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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static ForumTopic asForumTopic(final MyAnimeList mal, final TopLevelObject.ForumTopicData schema){
            return new ForumTopic() {

                private final String title = requireNonNull(() -> schema.title);
                private final Post[] posts = requireNonNull(() -> adaptArray(schema.posts, p -> asPost(mal, p, this), Post.class));
                // private final Poll[] polls = requireNonNull(() -> adaptArray(schema.poll, p -> asPoll(mal, p, this), Poll.class));
                private final Poll poll = requireNonNull(() -> asPoll(mal, schema.poll, this));

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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static Post asPost(final MyAnimeList mal, final TopLevelObject.ForumTopicData.Post schema, final ForumTopic forumTopic){
            return new Post() {

                private final long id           = requireNonNullElse(() -> schema.id, -1L);
                private final int number        = requireNonNullElse(() -> schema.number, -1);
                private final long createdAt    = requireNonNullElse(() -> parseISO8601(schema.created_at), -1L);
                private final PostAuthor author = requireNonNull(() -> asPostAuthor(mal, schema.created_by));
                private final String body       = requireNonNull(() -> schema.body);
                private final String signature  = requireNonNull(() -> schema.signature);

                // API methods

                @Override
                public final long getID() {
                    return id;
                }

                @Override
                public final int getNumber() {
                    return number;
                }

                @Override
                public final long getCreatedAt() {
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
                public final ForumTopic getForumTopic() {
                    return forumTopic;
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

    }

    static abstract class Manga {

        static Author asAuthor(final MyAnimeList mal, final TopLevelObject.Manga.PersonRoleEdge schema){
            return new Author() {

                private final long id           = requireNonNullElse(() -> schema.node.id, -1L);
                private final String firstName  = requireNonNull(() -> schema.node.first_name);
                private final String lastName   = requireNonNull(() -> schema.node.last_name);
                private final String role       = requireNonNull(() -> schema.role);

                // API methods

                @Override
                public final long getID() {
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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static Publisher asPublisher(final MyAnimeList mal, final Call.GetManga.MagazineRelationEdge schema){
            return new Publisher() {

                private final long id       = requireNonNullElse(() -> schema.node.id, -1L);
                private final String name   = requireNonNull(() -> schema.node.name);
                private final String role   = requireNonNull(() -> schema.role);

                // API methods

                @Override
                public final long getID() {
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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static com.kttdevelopment.myanimelist.manga.Manga asManga(final MyAnimeList mal, final Call.GetManga schema){
            return new com.kttdevelopment.myanimelist.manga.Manga() {

                private final long id               = requireNonNullElse(() -> schema.id, -1L);
                private final String title          = requireNonNull(() -> schema.title);
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.main_picture));
                private final AlternativeTitles alternativeTitles = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.alternative_titles));
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long endDate          = requireNonNullElse(() -> parseDate(schema.end_date), -1L);
                private final String synopsis       = requireNonNull(() -> schema.synopsis);
                private final float meanRating      = requireNonNullElse(() -> schema.mean, 0F);
                private final int rank              = requireNonNullElse(() -> schema.rank, -1);
                private final int popularity        = requireNonNullElse(() -> schema.popularity, -1);
                private final int usersListing      = requireNonNullElse(() -> schema.num_list_users, -1);
                private final int usersScoring      = requireNonNullElse(() -> schema.num_scoring_users, -1);
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.nsfw));
                private final Genre[] genres        = requireNonNull(() -> adaptArray(schema.genres, g -> Genre.asEnum((int) g.id), Genre.class));
                private final long createdAt        = requireNonNullElse(() -> parseISO8601(schema.created_at), -1L);
                private final long updatedAt        = requireNonNullElse(() -> parseISO8601(schema.updated_at), -1L);
                private final MangaType type        = requireNonNull(() -> MangaType.asEnum(schema.media_type));
                private final MangaPublishStatus status = requireNonNull(() -> MangaPublishStatus.asEnum(schema.status));
                private final MangaListStatus listStatus = requireNonNull(() -> asMangaListStatus(mal, schema.my_list_status, this));
                private final int volumes           = requireNonNullElse(() -> schema.num_volumes, -1);
                private final int chapters          = requireNonNullElse(() -> schema.num_chapters, -1);
                private final Author[] authors      = requireNonNull(() -> adaptArray(schema.authors, a -> asAuthor(mal, a), Author.class));
                private final Picture[] pictures    = requireNonNull(() -> adaptArray(schema.pictures, p -> Common.asPicture(mal, p), Picture.class));
                private final String background     = requireNonNull(() -> schema.background);
                private final RelatedAnime[] relatedAnime = requireNonNull(() -> adaptArray(schema.related_anime, a -> Anime.asRelatedAnime(mal, a), RelatedAnime.class));
                private final RelatedManga[] relatedManga = requireNonNull(() -> adaptArray(schema.related_manga, m -> asRelatedManga(mal, m), RelatedManga.class));
                private final MangaRecommendation[] recommendations = requireNonNull(() -> adaptArray(schema.recommendations, r -> asMangaRecommendation(mal, r), MangaRecommendation.class));
                private final Publisher[] serialization   = requireNonNull(() -> adaptArray(schema.serialization, s -> asPublisher(mal, s), Publisher.class));
                
                // API methods

                @Override
                public final long getID() {
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
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getEndDate() {
                    return endDate;
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final float getMeanRating() {
                    return meanRating;
                }

                @Override
                public final int getRank() {
                    return rank;
                }

                @Override
                public final int getPopularity() {
                    return popularity;
                }

                @Override
                public final int getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final int getUserScoringCount() {
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
                public final long getCreatedAt() {
                    return createdAt;
                }

                @Override
                public final long getUpdatedAt() {
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
                public final int getVolumes() {
                    return volumes;
                }

                @Override
                public final int getChapters() {
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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static MangaListStatus asMangaListStatus(final MyAnimeList mal, final TopLevelObject.Manga.MyListStatus schema, final MangaPreview manga){
            return new MangaListStatus() {

                private final MangaStatus status    = requireNonNull(() -> MangaStatus.asEnum(schema.status));
                private final int score             = requireNonNullElse(() -> schema.score, -1);
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long finishDate       = requireNonNullElse(() -> parseDate(schema.finish_date), -1L);
                private final int priority          = requireNonNullElse(() -> schema.priority, -1);
                private final String[] tags         = requireNonNull(() -> schema.tags);
                private final String comments       = requireNonNull(() -> schema.comments);
                private final long updatedAt        = requireNonNull(() -> parseISO8601(schema.updated_at));
                private final int volumesRead       = requireNonNullElse(() -> schema.num_volumes_read, -1);
                private final int chaptersRead      = requireNonNullElse(() -> schema.num_chapters_read, -1);
                private final boolean rereading     = requireNonNullElse(() -> schema.is_rereading, false);
                private final int timesReread       = requireNonNullElse(() -> schema.num_times_reread, -1);
                private final int rereadValue       = requireNonNullElse(() -> schema.reread_value, -1);

                // API methods

                @Override
                public final MangaStatus getStatus() {
                    return status;
                }

                @Override
                public final int getScore() {
                    return score;
                }

                @Override
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getFinishDate() {
                    return finishDate;
                }

                @Override
                public final int getPriority() {
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
                public final long getUpdatedAt() {
                    return updatedAt;
                }

                @Override
                public final int getVolumesRead() {
                    return volumesRead;
                }

                @Override
                public final int getChaptersRead() {
                    return chaptersRead;
                }

                @Override
                public final boolean isRereading() {
                    return rereading;
                }

                @Override
                public final int getTimesReread() {
                    return timesReread;
                }

                @Override
                public final int getRereadValue() {
                    return rereadValue;
                }

                // additional methods

                @Override
                public final MangaListUpdate edit(){
                    return mal.updateMangaListing(manga.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static MangaPreview asMangaPreview(final MyAnimeList mal, final TopLevelObject.Manga schema){
            return new MangaPreview() {

                private final long id               = requireNonNullElse(() -> schema.id, -1L);
                private final String title          = requireNonNull(() -> schema.title);
                private final Picture mainPicture   = requireNonNull(() -> Common.asPicture(mal, schema.main_picture));
                private final AlternativeTitles alternativeTitles = requireNonNull(() -> Common.asAlternativeTitles(mal, schema.alternative_titles));
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long endDate          = requireNonNullElse(() -> parseDate(schema.end_date), -1L);
                private final String synopsis       = requireNonNull(() -> schema.synopsis);
                private final float meanRating      = requireNonNullElse(() -> schema.mean, 0F);
                private final int rank              = requireNonNullElse(() -> schema.rank, -1);
                private final int popularity        = requireNonNullElse(() -> schema.popularity, -1);
                private final int usersListing      = requireNonNullElse(() -> schema.num_list_users, -1);
                private final int usersScoring      = requireNonNullElse(() -> schema.num_scoring_users, -1);
                private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.nsfw));
                private final Genre[] genres        = requireNonNull(() -> adaptArray(schema.genres, g -> Genre.asEnum((int) g.id), Genre.class));
                private final long createdAt        = requireNonNullElse(() -> parseISO8601(schema.created_at), -1L);
                private final long updatedAt        = requireNonNullElse(() -> parseISO8601(schema.updated_at), -1L);
                private final MangaType type        = requireNonNull(() -> MangaType.asEnum(schema.media_type));
                private final MangaPublishStatus status = requireNonNull(() -> MangaPublishStatus.asEnum(schema.status));
                private final MangaListStatus listStatus = requireNonNull(() -> asMangaListStatus(mal, schema.my_list_status, this));
                private final int volumes           = requireNonNullElse(() -> schema.num_volumes, -1);
                private final int chapters          = requireNonNullElse(() -> schema.num_chapters, -1);
                private final Author[] authors      = requireNonNull(() -> adaptArray(schema.authors, a -> asAuthor(mal, a), Author.class));

                // API methods

                @Override
                public final long getID() {
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
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getEndDate() {
                    return endDate;
                }

                @Override
                public final String getSynopsis() {
                    return synopsis;
                }

                @Override
                public final float getMeanRating() {
                    return meanRating;
                }

                @Override
                public final int getRank() {
                    return rank;
                }

                @Override
                public final int getPopularity() {
                    return popularity;
                }

                @Override
                public final int getUserListingCount() {
                    return usersListing;
                }

                @Override
                public final int getUserScoringCount() {
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
                public final long getCreatedAt() {
                    return createdAt;
                }

                @Override
                public final long getUpdatedAt() {
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
                public final int getVolumes() {
                    return volumes;
                }

                @Override
                public final int getChapters() {
                    return chapters;
                }

                @Override
                public final Author[] getAuthors() {
                    return authors;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.myanimelist.manga.Manga getManga() {
                    return mal.getManga(id);
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static MangaRanking asMangaRanking(final MyAnimeList mal, final SubLevelObject.Ranking<TopLevelObject.Manga> schema){
            return new MangaRanking() {

                private final MangaPreview manga    = requireNonNull(() -> asMangaPreview(mal, schema.node));
                private final int ranking           = requireNonNullElse(() -> schema.ranking.rank, -1);
                private final int previousRanking   = requireNonNullElse(() -> schema.ranking.previous_rank, -1);

                // API method

                @Override
                public final MangaPreview getMangaPreview() {
                    return manga;
                }

                @Override
                public final int getRanking() {
                    return ranking;
                }

                @Override
                public final int getPreviousRank() {
                    return previousRanking;
                }

                // API methods

                @Override
                public final com.kttdevelopment.myanimelist.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static MangaRecommendation asMangaRecommendation(final MyAnimeList mal, final SubLevelObject.Recommendation<TopLevelObject.Manga> schema){
            return new MangaRecommendation() {

                private final MangaPreview manga    = requireNonNull(() -> asMangaPreview(mal, schema.node));
                private final int recommendations   = requireNonNullElse(() -> schema.num_recommendations, -1);

                // API methods

                @Override
                public final MangaPreview getMangaPreview() {
                    return manga;
                }

                @Override
                public final int getRecommendations() {
                    return recommendations;
                }

                // additional methods

                @Override
                public final com.kttdevelopment.myanimelist.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static RelatedManga asRelatedManga(final MyAnimeList mal, final SubLevelObject.RelatedMangaEdge schema){
            return new RelatedManga() {

                private final MangaPreview manga            = requireNonNull(() -> asMangaPreview(mal, schema.node));
                private final RelationType relationType     = requireNonNull(() -> RelationType.asEnum(schema.relation_type));
                private final String relationTypeFormatted  = requireNonNull(() -> schema.relation_type_formatted);

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
                public final com.kttdevelopment.myanimelist.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static UserMangaListStatus asUserMangaListStatus(final MyAnimeList mal, final Call.UpdateUserMangaList schema, final MangaPreview manga){
            return new UserMangaListStatus() {

                private final MangaStatus status    = requireNonNull(() -> MangaStatus.asEnum(schema.status));
                private final int score             = requireNonNullElse(() -> schema.score, -1);
                private final long startDate        = requireNonNullElse(() -> parseDate(schema.start_date), -1L);
                private final long finishDate       = requireNonNullElse(() -> parseDate(schema.end_date), -1L);
                private final int priority          = requireNonNullElse(() -> schema.priority, -1);
                private final String[] tags         = requireNonNull(() -> schema.tags);
                private final String comments       = requireNonNull(() -> schema.comments);
                private final long updatedAt        = requireNonNullElse(() -> parseISO8601(schema.updated_at), -1L);
                private final int volumesRead       = requireNonNullElse(() -> schema.num_volumes_read, -1);
                private final int chaptersRead      = requireNonNullElse(() -> schema.num_chapters_read, -1);
                private final boolean rereading     = requireNonNullElse(() -> schema.is_rereading, false);
                private final int timesReread       = requireNonNullElse(() -> schema.num_times_reread, -1);
                private final int rereadValue       = requireNonNullElse(() -> schema.reread_value, -1);

                // API methods

                @Override
                public final MangaStatus getStatus() {
                    return status;
                }

                @Override
                public final int getScore() {
                    return score;
                }

                @Override
                public final long getStartDate() {
                    return startDate;
                }

                @Override
                public final long getFinishDate() {
                    return finishDate;
                }

                @Override
                public final int getPriority() {
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
                public final long getUpdatedAt() {
                    return updatedAt;
                }

                @Override
                public final int getVolumesRead() {
                    return volumesRead;
                }

                @Override
                public final int getChaptersRead() {
                    return chaptersRead;
                }

                @Override
                public final boolean isRereading() {
                    return rereading;
                }

                @Override
                public final int getTimesReread() {
                    return timesReread;
                }

                @Override
                public final int getRereadValue() {
                    return rereadValue;
                }

                // additional methods
                
                @Override
                public final MangaListUpdate edit(){
                    return mal.updateMangaListing(manga.getID());
                }

                @Override
                public final com.kttdevelopment.myanimelist.manga.Manga getManga() {
                    return mal.getManga(manga.getID());
                }

                @Override
                public final MangaPreview getMangaPreview() {
                    return manga;
                }

                @Override
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

    }
    
    static abstract class User {
        
        static com.kttdevelopment.myanimelist.user.User asUser(final MyAnimeList mal, final Call.GetUserInformation schema){
            return new com.kttdevelopment.myanimelist.user.User() {

                private final long id           = requireNonNullElse(() -> schema.id, -1L);
                private final String name       = requireNonNull(() -> schema.name);
                private final String picture    = requireNonNull(() -> schema.picture);
                private final String gender     = requireNonNull(() -> schema.gender);
                private final long birthday     = requireNonNullElse(() -> parseDate(schema.birthday), -1L);
                private final String location   = requireNonNull(() -> schema.location);
                private final long joinedAt     = requireNonNullElse(() -> parseISO8601(schema.joined_at), -1L);
                private final UserAnimeStatistics animeStatistics = requireNonNull(() -> asUserAnimeStatistics(mal, schema.anime_statistics));
                private final String timezone   = requireNonNull(() -> schema.time_zone);
                private final boolean supporter = requireNonNullElse(() -> schema.is_supporter, false);
                
                // API methods
                
                @Override
                public final long getID(){
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
                public final long getBirthday(){
                    return birthday;
                }

                @Override
                public final String getLocation(){
                    return location;
                }

                @Override
                public final long getJoinedAt(){
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
                public final boolean isSupporter(){
                    return supporter;
                }
                
                // additional methods
                
                @Override
                public final String toString(){
                    return AutomatedToString(this);
                }
                
            };
        }

        static UserAnimeStatistics asUserAnimeStatistics(final MyAnimeList mal, final Call.GetUserInformation.AnimeStatistics schema){
            return new UserAnimeStatistics() {
                
                private final int watching          = requireNonNullElse(() -> schema.num_items_watching, -1);
                private final int completed         = requireNonNullElse(() -> schema.num_items_completed, -1);
                private final int onHold            = requireNonNullElse(() -> schema.num_items_on_hold, -1);
                private final int dropped           = requireNonNullElse(() -> schema.num_items_dropped, -1);
                private final int planToWatch       = requireNonNullElse(() -> schema.num_items_plan_to_watch, -1);
                private final int items             = requireNonNullElse(() -> schema.num_items, -1);
                private final float daysWatching    = requireNonNullElse(() -> schema.num_days_watching, 0f);
                private final float daysCompleted   = requireNonNullElse(() -> schema.num_days_completed, 0f);
                private final float daysOnHold      = requireNonNullElse(() -> schema.num_days_on_hold, 0f);
                private final float daysDropped     = requireNonNullElse(() -> schema.num_days_dropped, 0f);
                private final float days            = requireNonNullElse(() -> schema.num_days, 0f);
                private final int episodesWatched   = requireNonNullElse(() -> schema.num_episodes, -1);
                @SuppressWarnings("SpellCheckingInspection")
                private final int timesRewatched    = requireNonNullElse(() -> schema.num_times_rewatched, -1);
                private final float meanScore       = requireNonNullElse(() -> schema.mean_score, 0f);

                // API methods

                @Override
                public final int getItemsWatching(){
                    return watching;
                }

                @Override
                public final int getItemsCompleted(){
                    return completed;
                }

                @Override
                public final int getItemsOnHold(){
                    return onHold;
                }

                @Override
                public final int getItemsDropped(){
                    return dropped;
                }

                @Override
                public final int getItemsPlanToWatch(){
                    return planToWatch;
                }

                @Override
                public final int getItems(){
                    return items;
                }

                @Override
                public final float getDaysWatched(){
                    return days;
                }

                @Override
                public final float getDaysWatching(){
                    return daysWatching;
                }

                @Override
                public final float getDaysCompleted(){
                    return daysCompleted;
                }

                @Override
                public final float getDaysOnHold(){
                    return daysOnHold;
                }

                @Override
                public final float getDaysDropped(){
                    return daysDropped;
                }

                @Override
                public final float getDays(){
                    return days;
                }

                @Override
                public final int getEpisodes(){
                    return episodesWatched;
                }

                @SuppressWarnings("SpellCheckingInspection")
                @Override
                public final int getTimesRewatched(){
                    return timesRewatched;
                }

                @Override
                public final float getMeanScore(){
                    return meanScore;
                }
                
                // additional methods

                @Override
                public final String toString(){
                    return AutomatedToString(this);
                }

            };
        }
        
    }

    static abstract class Common {

        static AlternativeTitles asAlternativeTitles(final MyAnimeList mal, final SubLevelObject.AlternativeTitles schema){
            return new AlternativeTitles() {

                private final String[] synonyms = requireNonNull(() -> schema.synonyms);
                private final String english    = requireNonNull(() -> schema.en);
                private final String japanese   = requireNonNull(() -> schema.ja);

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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        static Picture asPicture(final MyAnimeList mal, final SubLevelObject.Picture schema){
            return new Picture() {

                private final String medium = requireNonNull(() -> schema.medium);
                private final String large  = requireNonNull(() -> schema.large);

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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

    }

    //

    @SuppressWarnings("unchecked")
    private static <T,R> R[] adaptArray(final T[] arr, final Function<T,R> adapter,final Class<R> Class){
        final List<R> list = new ArrayList<>();
        for(final T obj : arr)
            list.add(adapter.apply(obj));

        final R[] array = (R[]) Array.newInstance(Class, list.size());

        final int len = array.length;
        for(int i = 0; i < len; i++)
            array[i] = list.get(i);

        return array;
    }

    private static long parseDate(final String date){
        if(date == null) return -1;

        final DateFormat df = new SimpleDateFormat(date.length() == 10 ? "yyyy-MM-dd" : date.length() == 7 ? "yyyy-MM" : "yyyy");

        try{
            return df.parse(date).getTime();
        }catch(final ParseException ignored){
            return -1;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static long parseISO8601(final String timestamp){
        if(timestamp == null) return -1;

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        try{
            return df.parse(timestamp).getTime();
        }catch(final ParseException ignored){
            return -1;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static Time asTime(final String time){
        final String[] hhmm = time.split(":");
        final int h         = Integer.parseInt(hhmm[0]);
        final int m         = Integer.parseInt(hhmm[1]);

        return new Time() {

            private final int hour   = h;
            private final int hour12 = h > 12 ? h - 12 : h == 0 ? 12 : h;
            private final boolean am = hour <= 12;
            private final int minute = m;

            @Override
            public final int getHour() {
                return hour;
            }

            @Override
            public final int get12Hour() {
                return hour12;
            }

            @Override
            public final boolean isAM() {
                return am;
            }

            @Override
            public final boolean isPM() {
                return !am;
            }

            @Override
            public final int getMinute() {
                return minute;
            }

            //

            @Override
            public final String toString() {
                return AutomatedToString(this);
            }

        };

    }

    //

    private static <T> T requireNonNull(final Supplier<T> obj){
        return requireNonNullElse(obj, null);
    }

    private static <T> T requireNonNullElse(final Supplier<T> obj, T defaultObj){
        try{
            final T response = obj.get();
            return response != null ? response : defaultObj;
        }catch (final NullPointerException ignored){
            return defaultObj;
        }
    }

    private static String AutomatedToString(final Object obj){
        final Class<?> _class = obj.getClass();

        final List<Field[]> fieldSets = new ArrayList<>();
        fieldSets.add(_class.getDeclaredFields());

        Class<?> _super = _class; // add inherited fields
        while((_super = _super.getSuperclass()) != null)
            fieldSets.add(_super.getDeclaredFields());

        Collections.reverse(fieldSets);

        final StringBuilder OUT = new StringBuilder();
        OUT.append(_class.getSimpleName()).append('{');
        for(final Field[] set : fieldSets){ // print all fields
            for(final Field field : set){
                if(!field.getName().contains("$"))
                    try{
                        final Object value = field.get(obj);
                        OUT.append(field.getName()).append('=');
                        if(value instanceof String)
                            OUT.append('\'').append(value).append('\'');
                        else if(value instanceof Object[])
                            OUT.append(Arrays.toString((Object[]) value));
                        else
                            OUT.append(value);
                        OUT.append(", ");
                    }catch(final IllegalAccessException ignored){ }
            }
        }

        if(OUT.toString().contains(", "))
            OUT.delete(OUT.lastIndexOf(", "), OUT.length());
        OUT.append('}');
        return OUT.toString();
    }

}
