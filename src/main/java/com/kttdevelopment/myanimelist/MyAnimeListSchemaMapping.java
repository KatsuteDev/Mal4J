package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.AnimeRanking;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.*;
import com.kttdevelopment.myanimelist.forum.*;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.forum.property.*;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.MangaRanking;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.Genre;
import com.kttdevelopment.myanimelist.property.NSFW;
import com.kttdevelopment.myanimelist.property.Picture;
import com.kttdevelopment.myanimelist.property.RelationType;
import com.kttdevelopment.myanimelist.user.*;

import java.lang.reflect.Field;
import java.text.*;
import java.util.*;

import static com.kttdevelopment.myanimelist.MyAnimeListSchema.*;

/**
 * This is the master class for the MyAnimeList API, it converts the JSON mapping
 * to a more convenient interface.
 * <br><br>
 * DEVS: Parse values in instantiation and not in functions (this will improve performance).
 *
 * @see MyAnimeListSchema
 */
abstract class MyAnimeListSchemaMapping {

    // Anime

    static Anime asAnime(final MyAnimeList myAnimeList, final AnimeDetails schema){
        return new Anime() {

            private final MyAnimeList mal   = myAnimeList;
            private final AnimeDetails obj  = schema;

            private final long id           = obj == null ? -1 : obj.id;
            private final String title      = obj == null ? null : obj.title;
            private final Picture picture   = asPicture(mal, obj == null ? null : obj.main_picture);

            private final AlternativeTitles alternativeTitles = asAlternativeTitles(mal, obj == null ? null : obj.alternative_titles);
            private final long startDate                = parseDate(obj == null ? null : obj.start_date);
            private final long endDate                  = parseDate(obj == null ? null : obj.end_date);
            private final String synopsis               = obj == null ? null : obj.synopsis;
            private final double mean                   = obj == null ? -1 : obj.mean;
            private final int rank                      = obj == null ? -1 : obj.rank,
                    popularity                          = obj == null ? -1 : obj.popularity,
                    listUsers                           = obj == null ? -1 : obj.num_list_users,
                    scoringUsers                        = obj == null ? -1 : obj.num_scoring_users;
            private final NSFW nsfw                     = NSFW.asEnum(obj == null ? null : obj.nsfw);
            private final long createdAt                = parseISO8601(obj == null ? null : obj.created_at);
            private final long updatedAt                = parseISO8601(obj == null ? null : obj.updated_at);
            private final AnimeType mediaType           = AnimeType.asEnum(obj == null ? null : obj.media_type);
            private final AnimeAirStatus status         = AnimeAirStatus.asEnum(obj == null ? null : obj.status);
            private final Genre[] genres                = asGenres(obj == null ? null : obj.genres);
            private final AnimeListStatus listStatus    = asAnimeListStatus(mal, obj == null ? null : obj.my_list_status);
            private final int episodes                  = obj == null ? -1 : obj.num_episodes;
            private final StartSeason startSeason       = asStartSeason(mal, obj == null ? null : obj.start_season);
            private final Broadcast broadcast           = asBroadcast(mal, obj == null ? null : obj.broadcast);
            private final AnimeSource source            = AnimeSource.asEnum(obj == null ? null : obj.source);
            private final int duration                  = obj == null ? -1 : obj.average_episode_duration;
            private final AnimeRating rating            = AnimeRating.asEnum(obj == null ? null : obj.rating);
            private final Picture[] pictures;
            private final String background             = obj == null ? null : obj.background;
            private final RelatedAnime[] relatedAnime;
            private final RelatedManga[] relatedManga;
            private final AnimeRecommendation[] animeRecommendations;
            private final Studio[] studios;
            private final AnimeStatistics statistics    = asAnimeStatistics(mal, obj == null ? null : obj.statistics);

            {
                final List<Picture> pictures = new ArrayList<>();
                for(final MyAnimeListSchema.schema.node.picture pic : Objects.requireNonNullElse(obj.pictures, new MyAnimeListSchema.schema.node.picture[0]))
                    pictures.add(asPicture(mal, pic));
                this.pictures = pictures.toArray(new Picture[0]);

                final List<RelatedAnime> relatedAnime = new ArrayList<>();
                for(final MyAnimeListSchema.schema.related related : Objects.requireNonNullElse(obj.related_anime, new MyAnimeListSchema.schema.related[0]))
                    relatedAnime.add(asRelatedAnime(mal, related));
                this.relatedAnime = relatedAnime.toArray(new RelatedAnime[0]);

                final List<RelatedManga> relatedManga = new ArrayList<>();
                for(final MyAnimeListSchema.schema.related related : Objects.requireNonNullElse(obj.related_manga, new MyAnimeListSchema.schema.related[0]))
                    relatedManga.add(asRelatedManga(mal, related));
                this.relatedManga = relatedManga.toArray(new RelatedManga[0]);

                final List<AnimeRecommendation> animeRecommendations = new ArrayList<>();
                for(final MyAnimeListSchema.schema.recommendation recommendation : Objects.requireNonNullElse(obj.recommendations, new MyAnimeListSchema.schema.recommendation[0]))
                    animeRecommendations.add(asAnimeRecommendation(mal, recommendation));
                this.animeRecommendations = animeRecommendations.toArray(new AnimeRecommendation[0]);

                final List<Studio> studios = new ArrayList<>();
                for(final AnimeDetails.studio studio : Objects.requireNonNullElse(obj.studios, new AnimeDetails.studio[0]))
                    studios.add(asStudio(mal, studio));
                this.studios = studios.toArray(new Studio[0]);
            }

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                return picture;
            }

            @Override
            public final AlternativeTitles AlternativeTitles(){
                return alternativeTitles;
            }

            @Override
            public final long getStartDate(){
                return startDate;
            }

            @Override
            public final long getEndDate(){
                return endDate;
            }

            @Override
            public final String getSynopsis(){
                return synopsis;
            }

            @Override
            public final double getMeanRating(){
                return mean;
            }

            @Override
            public final int getRank(){
                return rank;
            }

            @Override
            public final int getPopularity(){
                return popularity;
            }

            @Override
            public final int getUserListingCount(){
                return listUsers;
            }

            @Override
            public final int getUserScoringCount(){
                return scoringUsers;
            }

            @Override
            public final NSFW getNSFW(){
                return nsfw;
            }

            @Override
            public final long getCreatedAt(){
                return createdAt;
            }

            @Override
            public final long getUpdatedAt(){
                return updatedAt;
            }

            @Override
            public final AnimeType getAnimeType(){
                return mediaType;
            }

            @Override
            public final AnimeAirStatus getStatus(){
                return status;
            }

            @Override
            public final Genre[] getGenres(){
                return genres;
            }

            @Override
            public final AnimeListStatus getListStatus(){
                return listStatus;
            }

            @Override
            public final int getEpisodes(){
                return episodes;
            }

            @Override
            public final StartSeason getStartSeason(){
                return startSeason;
            }

            @Override
            public final Broadcast getBroadcast(){
                return broadcast;
            }

            @Override
            public final AnimeSource getSource(){
                return source;
            }

            @Override
            public final int getAverageEpisodeLength(){
                return duration;
            }

            @Override
            public final AnimeRating getRating(){
                return rating;
            }

            @Override
            public final Picture[] getPictures(){
                return pictures;
            }

            @Override
            public final String getBackground(){
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime(){
                return relatedAnime;
            }

            @Override
            public final RelatedManga[] getRelatedManga(){
                return relatedManga;
            }

            @Override
            public final AnimeRecommendation[] getRecommendations(){
                return animeRecommendations;
            }

            @Override
            public final Studio[] getStudios(){
                return studios;
            }

            @Override
            public final AnimeStatistics getStatistics(){
                return statistics;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AnimeListing asAnimeListing(final MyAnimeList myAnimeList, final UpdateAnimeList schema){
        return new AnimeListing() {

            private final MyAnimeList mal       = myAnimeList;
            private final UpdateAnimeList obj   = schema;

            private final AnimeStatus status    = AnimeStatus.asEnum(obj == null ? null : obj.status);
            private final int score             = obj == null ? -1 : obj.score;
            private final int episodes_watched  = obj == null ? -1 : obj.num_watched_episodes;
            @SuppressWarnings("SpellCheckingInspection")
            private final boolean isRewatching  = obj != null && obj.is_rewatching;
            private final long updatedAt        = parseISO8601(obj == null ? null : obj.updated_at);
            private final int priority          = obj == null ? -1 : obj.priority;
            @SuppressWarnings("SpellCheckingInspection")
            private final int rewatched         = obj == null ? -1 : obj.num_times_rewatched;
            @SuppressWarnings("SpellCheckingInspection")
            private final int rewatchValue      = obj == null ? -1 : obj.rewatch_value;
            private final String[] tags         = obj == null ? null : obj.tags;
            private final String comments       = obj == null ? null : obj.comments;

            // API methods

            @Override
            public final AnimeStatus getStatus(){
                return status;
            }

            @Override
            public final int getScore(){
                return score;
            }

            @Override
            public final int getEpisodesWatched(){
                return episodes_watched;
            }

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final boolean isRewatching(){
                return isRewatching;
            }

            @Override
            public final long getUpdatedAt(){
                return updatedAt;
            }

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final int getTimesRewatched(){
                return rewatched;
            }

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final int getRewatchValue(){
                return rewatchValue;
            }

            @Override
            public final int getPriority(){
                return priority;
            }

            @Override
            public final String[] getTags(){
                return tags;
            }

            @Override
            public final String getComments(){
                return comments;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AnimeListStatus asAnimeListStatus(final MyAnimeList myAnimeList, final AnimeDetails.my_list_status schema){
        return new AnimeListStatus() {

            private final MyAnimeList mal                   = myAnimeList;
            private final AnimeDetails.my_list_status obj   = schema;

            private final AnimeStatus status    = AnimeStatus.asEnum(obj == null ? null : obj.status);
            private final int score             = obj == null ? -1 : obj.score;
            private final int episodes_watched  = obj == null ? -1 : obj.num_episodes_watched;
            @SuppressWarnings("SpellCheckingInspection")
            private final boolean isRewatching  = obj != null && obj.is_rewatching;
            private final long updatedAt        = parseISO8601(obj == null ? null : obj.updated_at);

            // API methods

            @Override
            public final AnimeStatus getStatus(){
                return status;
            }

            @Override
            public final int getScore(){
                return score;
            }

            @Override
            public final int getEpisodesWatched(){
                return episodes_watched;
            }

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final boolean isRewatching(){
                return isRewatching;
            }

            @Override
            public final long getUpdatedAt(){
                return updatedAt;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AnimePreview asAnimePreview(final MyAnimeList myAnimeList, final schema.node schema){
        return new AnimePreview() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.node obj = schema;

            private final long id               = obj == null ? -1 : obj.id;
            private final String title          = obj == null ? null : obj.title;
            private final Picture mainPicture   = asPicture(mal, obj == null ? null : obj.main_picture);

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            // additional methods

            @Override
            public final Anime getAnime(){
                return myAnimeList.getAnime(id);
            }

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AnimeRanking asAnimeRanking(final MyAnimeList myAnimeList, final schema.ranking.ranking_node schema){
        return new AnimeRanking() {

            private final MyAnimeList mal                   = myAnimeList;
            private final schema.ranking.ranking_node obj   = schema;

            private final AnimePreview anime    = asAnimePreview(mal, obj == null ? null : obj.node);
            private final int ranking           = obj == null ? -1 : obj.ranking.rank;

            // API methods

            @Override
            public final AnimePreview getAnimePreview(){
                return anime;
            }

            @Override
            public final int getRanking(){
                return ranking;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AnimeRecommendation asAnimeRecommendation(final MyAnimeList myAnimeList, final schema.recommendation schema){
        return new AnimeRecommendation() {

            private final MyAnimeList mal           = myAnimeList;
            private final schema.recommendation obj = schema;

            private final AnimePreview anime    = asAnimePreview(mal, obj == null ? null : obj.node);
            private final int recommendations   = obj == null ? -1 : obj.num_recommendations;

            // API methods

            @Override
            public final AnimePreview getAnimePreview(){
                return anime;
            }

            @Override
            public final int getRecommendations(){
                return recommendations;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static RelatedAnime asRelatedAnime(final MyAnimeList myAnimeList, final schema.related schema){
        return new RelatedAnime() {

            private final MyAnimeList mal       = myAnimeList;
            private final schema.related obj    = schema;

            private final AnimePreview anime        = asAnimePreview(mal, obj == null ? null : obj.node);
            private final RelationType relationType = RelationType.asEnum(obj == null ? null : obj.relation_type);
            private final String relationTypeFormat = obj == null ? null : obj.relation_type_formatted;

            // API methods

            @Override
            public final AnimePreview getAnimePreview(){
                return anime;
            }

            @Override
            public final RelationType getRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat(){
                return relationTypeFormat;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AnimeStatistics asAnimeStatistics(final MyAnimeList myAnimeList, final AnimeDetails.statistics schema){
        return new AnimeStatistics() {

            private final MyAnimeList mal               = myAnimeList;
            private final AnimeDetails.statistics obj   = schema;

            private final int   watching    = Integer.parseInt(obj == null ? "-1" : obj.status.watching),
                                completed   = Integer.parseInt(obj == null ? "-1" : obj.status.completed),
                                onHold      = Integer.parseInt(obj == null ? "-1" : obj.status.on_hold),
                                dropped     = Integer.parseInt(obj == null ? "-1" : obj.status.dropped),
                                planToWatch = Integer.parseInt(obj == null ? "-1" : obj.status.plan_to_watch),
                                listUsers   = obj == null ? -1 : obj.num_list_users;

            // API methods

            @Override
            public final int getWatching(){
                return watching;
            }

            @Override
            public final int getCompleted(){
                return completed;
            }

            @Override
            public final int getOnHold(){
                return onHold;
            }

            @Override
            public final int getDropped(){
                return dropped;
            }

            @Override
            public final int getPlanToWatch(){
                return planToWatch;
            }

            @Override
            public final int getUserCount(){
                return listUsers;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static StartSeason asStartSeason(final MyAnimeList myAnimeList, final AnimeDetails.start_season schema){
        return new StartSeason() {

            private final MyAnimeList mal               = myAnimeList;
            private final AnimeDetails.start_season obj = schema;

            private final int year      = obj == null ? -1 : obj.year;
            private final Season season = Season.asEnum(obj == null ? null : obj.season);

            // API methods

            @Override
            public final int getYear(){
                return year;
            }

            @Override
            public final Season getSeason(){
                return season;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static Broadcast asBroadcast(final MyAnimeList myAnimeList, final AnimeDetails.broadcast schema){
        return new Broadcast() {

            private final MyAnimeList mal               = myAnimeList;
            private final AnimeDetails.broadcast obj    = schema;

            private final DayOfWeek dayOfWeek   = DayOfWeek.asEnum(obj == null ? null : obj.day_of_week);
            private final Time time             = parseTime(obj == null ? "00:00" : obj.start_time);

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
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static Studio asStudio(final MyAnimeList myAnimeList, final AnimeDetails.studio schema){
        return new Studio() {

            private final MyAnimeList mal           = myAnimeList;
            private final AnimeDetails.studio obj   = schema;

            private final long id       = obj == null ? -1 : obj.id;
            private final String name   = obj == null ? null : obj.name;

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getName(){
                return name;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    // Manga

    static Manga asManga(final MyAnimeList myAnimeList, final MangaDetails schema){
        return new Manga() {

            private final MyAnimeList mal   = myAnimeList;
            private final MangaDetails obj  = schema;

            private final long id                   = obj == null ? -1 : obj.id;
            private final String title              = obj == null ? null : obj.title;
            private final Picture mainPicture       = asPicture(mal, obj == null ? null : obj.main_picture);
            private final AlternativeTitles alternativeTitles = asAlternativeTitles(mal, obj == null ? null : obj.alternative_titles);
            private final long startDate            = parseDate(obj == null ? null : obj.start_date);
            private final long endDate              = parseDate(obj == null ? null : obj.end_date);
            private final String synopsis           = obj == null ? null : obj.synopsis;
            private final double mean               = obj == null ? -1 : obj.mean;
            private final int rank                  = obj == null ? -1 : obj.rank,
                    popularity                      = obj == null ? -1 : obj.popularity,
                    listUsers                       = obj == null ? -1 : obj.num_list_users,
                    scoringUsers                    = obj == null ? -1 : obj.num_scoring_users;
            private final NSFW nsfw                 = NSFW.asEnum(obj == null ? null : obj.nsfw);
            private final long createdAt            = parseISO8601(obj == null ? null : obj.created_at);
            private final long updateAt             = parseDate(obj == null ? null : obj.updated_at);
            private final MangaType type            = MangaType.asEnum(obj == null ? null : obj.media_type);
            private final MangaPublishStatus status = MangaPublishStatus.asEnum(obj == null ? null : obj.status);
            private final Genre[] genres            = obj == null ? new Genre[0] : asGenres(obj.genres);
            private final MangaListStatus listStatus = asMangaListStatus(mal, obj == null ? null : obj.my_list_status);
            private final int volumes               = obj == null ? -1 : obj.num_volumes;
            private final int chapters              = obj == null ? -1 : obj.num_chapters;
            private final Author[] authors;
            private final Picture[] pictures;
            private final String background         = obj == null ? null : obj.background;
            private final RelatedAnime[] relatedAnime;
            private final RelatedManga[] relatedManga;
            private final MangaRecommendation[] mangaRecommendations;
            private final Publisher[] serialization;
            private final MangaStatistics statistics = asMangaStatistics(mal, obj == null ? null : obj.statistics);

            {
                final List<Picture> pictures = new ArrayList<>();
                for(final MyAnimeListSchema.schema.node.picture pic : Objects.requireNonNullElse(obj.pictures, new MyAnimeListSchema.schema.node.picture[0]))
                    pictures.add(asPicture(mal, pic));
                this.pictures = pictures.toArray(new Picture[0]);

                final List<Author> authors = new ArrayList<>();
                for(final MangaDetails.author author : Objects.requireNonNullElse(obj.authors, new MangaDetails.author[0]))
                    authors.add(asAuthor(mal, author));
                this.authors = authors.toArray(new Author[0]);

                final List<RelatedAnime> relatedAnime = new ArrayList<>();
                for(final MyAnimeListSchema.schema.related related : Objects.requireNonNullElse(obj.related_anime, new MyAnimeListSchema.schema.related[0]))
                    relatedAnime.add(asRelatedAnime(mal, related));
                this.relatedAnime = relatedAnime.toArray(new RelatedAnime[0]);

                final List<RelatedManga> relatedManga = new ArrayList<>();
                for(final MyAnimeListSchema.schema.related related : Objects.requireNonNullElse(obj.related_manga, new MyAnimeListSchema.schema.related[0]))
                    relatedManga.add(asRelatedManga(mal, related));
                this.relatedManga = relatedManga.toArray(new RelatedManga[0]);

                final List<MangaRecommendation> mangaRecommendations = new ArrayList<>();
                for(final MyAnimeListSchema.schema.recommendation recommendation : Objects.requireNonNullElse(obj.recommendations, new MyAnimeListSchema.schema.recommendation[0]))
                    mangaRecommendations.add(asMangaRecommendation(mal, recommendation));
                this.mangaRecommendations = mangaRecommendations.toArray(new MangaRecommendation[0]);

                final List<Publisher> serialization = new ArrayList<>();
                for(final MangaDetails.serialization serialization1 : Objects.requireNonNullElse(obj.serialization, new MangaDetails.serialization[0]))
                    serialization.add(asPublisher(mal, serialization1));
                this.serialization = serialization.toArray(new Publisher[0]);
            }

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            @Override
            public final AlternativeTitles AlternativeTitles(){
                return alternativeTitles;
            }

            @Override
            public final long getStartDate(){
                return startDate;
            }

            @Override
            public final long getEndDate(){
                return endDate;
            }

            @Override
            public final String getSynopsis(){
                return synopsis;
            }

            @Override
            public final double getMeanRating(){
                return mean;
            }

            @Override
            public final int getRank(){
                return rank;
            }

            @Override
            public final int getPopularity(){
                return popularity;
            }

            @Override
            public final int getUserListingCount(){
                return listUsers;
            }

            @Override
            public final int getUserScoringCount(){
                return scoringUsers;
            }

            @Override
            public final NSFW getNSFW(){
                return nsfw;
            }

            @Override
            public final long getCreatedAt(){
                return createdAt;
            }

            @Override
            public final long getUpdatedAt(){
                return updateAt;
            }

            @Override
            public final MangaType getMangaType(){
                return type;
            }

            @Override
            public final MangaPublishStatus getStatus(){
                return status;
            }

            @Override
            public final Genre[] getGenres(){
                return genres;
            }

            @Override
            public final MangaListStatus getListStatus(){
                return listStatus;
            }

            @Override
            public final int getVolumes(){
                return volumes;
            }

            @Override
            public final int getChapters(){
                return chapters;
            }

            @Override
            public final Author[] getAuthors(){
                return authors;
            }

            @Override
            public final Picture[] getPictures(){
                return pictures;
            }

            @Override
            public final String getBackground(){
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime(){
                return relatedAnime;
            }

            @Override
            public final RelatedManga[] getRelatedManga(){
                return relatedManga;
            }

            @Override
            public final MangaRecommendation[] getRecommendations(){
                return mangaRecommendations;
            }

            @Override
            public final Publisher[] getSerialization(){
                return serialization;
            }

            @Override
            public final MangaStatistics getStatistics(){
                return statistics;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static MangaListing asMangaListing(final MyAnimeList myAnimeList, final UpdateMangaList schema){
        return new MangaListing() {

            private final MyAnimeList mal       = myAnimeList;
            private final UpdateMangaList obj   = schema;

            private final MangaStatus status    = MangaStatus.asEnum(obj == null ? null : obj.status);
            private final int score             = obj == null ? -1 : obj.score;
            private final int volumesRead       = obj == null ? -1 : obj.num_volumes_read;
            private final int chaptersRead      = obj == null ? -1 : obj.num_chapters_read;
            private final boolean isRereading   = obj != null && obj.is_rereading;
            private final long updatedAt        = parseISO8601(obj == null ? null : obj.updated_at);
            private final int priority          = obj == null ? -1 : obj.priority;
            private final int reread            = obj == null ? -1 : obj.num_times_reread;
            private final int rereadValue       = obj == null ? -1 : obj.reread_value;
            private final String[] tags         = obj == null ? null : obj.tags;
            private final String comments       = obj == null ? null : obj.comments;

            // API methods

            @Override
            public final MangaStatus getStatus(){
                return status;
            }

            @Override
            public final int getScore(){
                return score;
            }

            @Override
            public final int getVolumesRead(){
                return volumesRead;
            }

            @Override
            public final int getChaptersRead(){
                return chaptersRead;
            }

            @Override
            public final boolean isRereading(){
                return isRereading;
            }

            @Override
            public final long getUpdatedAt(){
                return updatedAt;
            }

            @Override
            public final int getPriority(){
                return priority;
            }

            @Override
            public final int getTimesReread(){
                return reread;
            }

            @Override
            public final int getRereadValue(){
                return rereadValue;
            }

            @Override
            public final String[] getTags(){
                return tags;
            }

            @Override
            public final String getComments(){
                return comments;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static MangaListStatus asMangaListStatus(final MyAnimeList myAnimeList, final MangaDetails.my_list_status schema){
        return new MangaListStatus() {

            private final MyAnimeList mal                   = myAnimeList;
            private final MangaDetails.my_list_status obj   = schema;

            private final MangaStatus status    = MangaStatus.asEnum(obj == null ? null : obj.status);
            private final boolean isRereading   = obj != null && obj.is_rereading;
            private final int volumesRead       = obj == null ? -1 : obj.num_volumes_read;
            private final int chaptersRead      = obj == null ? -1 : obj.num_chapters_read;
            private final int score             = obj == null ? -1 : obj.score;
            private final long updatedAt        = parseISO8601(obj == null ? null : obj.updated_at);

            // API methods

            @Override
            public final MangaStatus getStatus(){
                return status;
            }

            @Override
            public final boolean isRereading(){
                return isRereading;
            }


            @Override
            public final int getVolumesRead(){
                return volumesRead;
            }

            @Override
            public final int getChaptersRead(){
                return chaptersRead;
            }

            @Override
            public final int getScore(){
                return score;
            }

            @Override
            public final long getUpdatedAt(){
                return updatedAt;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static MangaPreview asMangaPreview(final MyAnimeList myAnimeList, final schema.node schema){
        return new MangaPreview() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.node obj = schema;

            private final long id               = obj == null ? -1 : obj.id;
            private final String title          = obj == null ? null : obj.title;
            private final Picture mainPicture   = asPicture(mal, obj == null ? null : obj.main_picture);

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            // additional methods

            @Override
            public final Manga getManga(){
                return myAnimeList.getManga(id);
            }

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static MangaRanking asMangaRanking(final MyAnimeList myAnimeList, final schema.ranking.ranking_node schema){
        return new MangaRanking() {

            private final MyAnimeList mal                   = myAnimeList;
            private final schema.ranking.ranking_node obj   = schema;

            private final MangaPreview manga    = asMangaPreview(mal, obj == null ? null : obj.node);
            private final int ranking           = obj == null ? -1 : obj.ranking.rank;

            // API methods

            @Override
            public final MangaPreview getMangaPreview(){
                return manga;
            }

            @Override
            public final int getRanking(){
                return ranking;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static MangaRecommendation asMangaRecommendation(final MyAnimeList myAnimeList, final schema.recommendation schema){
        return new MangaRecommendation() {

            private final MyAnimeList mal           = myAnimeList;
            private final schema.recommendation obj = schema;

            private final MangaPreview manga    = asMangaPreview(mal, obj == null ? null : obj.node);
            private final int recommendations   = obj == null ? -1 : obj.num_recommendations;

            // API methods

            @Override
            public final MangaPreview getMangaPreview(){
                return manga;
            }

            @Override
            public final int getRecommendations(){
                return recommendations;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static RelatedManga asRelatedManga(final MyAnimeList myAnimeList, final schema.related schema){
        return new RelatedManga() {

            private final MyAnimeList mal       = myAnimeList;
            private final schema.related obj    = schema;

            private final MangaPreview manga        = asMangaPreview(mal, obj == null ? null : obj.node);
            private final RelationType relationType = RelationType.asEnum(obj == null ? null : obj.relation_type);
            private final String relationTypeFormat = obj == null ? null : obj.relation_type_formatted;

            // API methods

            @Override
            public final MangaPreview getMangaPreview(){
                return manga;
            }

            @Override
            public final RelationType getRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat(){
                return relationTypeFormat;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static MangaStatistics asMangaStatistics(final MyAnimeList myAnimeList, final MangaDetails.statistics schema){
        return new MangaStatistics() {

            private final MyAnimeList mal               = myAnimeList;
            private final MangaDetails.statistics obj   = schema;

            private final int   reading     = Integer.parseInt(obj == null ? "-1" : obj.status.reading),
                                completed   = Integer.parseInt(obj == null ? "-1" : obj.status.completed),
                                onHold      = Integer.parseInt(obj == null ? "-1" : obj.status.on_hold),
                                dropped     = Integer.parseInt(obj == null ? "-1" : obj.status.dropped),
                                planToRead  = Integer.parseInt(obj == null ? "-1" : obj.status.plan_to_read),
                                listUsers   = obj == null ? -1 : obj.num_list_users;

            // API methods

            @Override
            public final int getReading(){
                return reading;
            }

            @Override
            public final int getCompleted(){
                return completed;
            }

            @Override
            public final int getOnHold(){
                return onHold;
            }

            @Override
            public final int getDropped(){
                return dropped;
            }

            @Override
            public final int getPlanToRead(){
                return planToRead;
            }

            @Override
            public final int getUserCount(){
                return listUsers;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static Author asAuthor(final MyAnimeList myAnimeList, final MangaDetails.author schema){
        return new Author() {

            private final MyAnimeList mal           = myAnimeList;
            private final MangaDetails.author obj   = schema;

            private final long id           = obj == null ? -1 : obj.node.id;
            private final String    first   = obj == null ? null : obj.node.first_name,
                                    last    = obj == null ? null : obj.node.last_name,
                                    role    = obj == null ? null : obj.role;

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getFirstName(){
                return first;
            }

            @Override
            public final String getLastName(){
                return last;
            }

            @Override
            public final String getRole(){
                return role;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static Publisher asPublisher(final MyAnimeList myAnimeList, final MangaDetails.serialization schema){
        return new Publisher() {

            private final MyAnimeList mal                   = myAnimeList;
            private final MangaDetails.serialization obj    = schema;

            private final long id       = obj == null ? -1 : obj.node.id;
            private final String name   = obj == null ? null : obj.node.name;

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getName(){
                return name;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    // Components

    static Picture asPicture(final MyAnimeList myAnimeList, final schema.node.picture schema){
        return new Picture() {

            private final MyAnimeList mal           = myAnimeList;
            private final schema.node.picture obj   = schema;

            private final String mediumURL  = obj == null ? null : obj.medium;
            private final String largeURL   = obj == null ? null : obj.large;

            // API methods

            @Override
            public final String getMediumURL(){
                return mediumURL;
            }

            @Override
            public final String getLargeURL(){
                return largeURL;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static AlternativeTitles asAlternativeTitles(final MyAnimeList myAnimeList, final schema.alternative_titles schema){
        return new AlternativeTitles() {

            private final MyAnimeList mal               = myAnimeList;
            private final schema.alternative_titles obj = schema;

            private final String[] synonyms = obj == null ? null : obj.synonyms;
            private final String english    = obj == null ? null : obj.en;
            private final String japanese   = obj == null ? null : obj.ja;

            // API methods

            @Override
            public final String[] getSynonyms(){
                return synonyms;
            }

            @Override
            public final String getEnglish(){
                return english;
            }

            @Override
            public final String getJapanese(){
                return japanese;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static Genre[] asGenres(final schema.genre[] schema){
        if(schema == null) return new Genre[0];

        final List<Genre> list = new ArrayList<>();
        for(final MyAnimeListSchema.schema.genre genre : schema)
            list.add(Genre.asEnum(genre.name));
        return list.toArray(new Genre[schema.length]);
    }

    private static Time parseTime(final String time){
        return new Time() {

            private final int hour   = Integer.parseInt(time.substring(0, 2));
            private final int hour12 = hour > 12 ? hour - 12 : hour == 0 ? 12 : hour;
            private final boolean am = hour <= 12;
            private final int minute = Integer.parseInt(time.substring(3));

            @Override
            public final int getHour(){
                return hour;
            }

            @Override
            public final int get12Hour(){
                return hour12;
            }

            @Override
            public final boolean isAM(){
                return am;
            }

            @Override
            public final boolean isPM(){
                return !am;
            }

            @Override
            public final int getMinute(){
                return minute;
            }

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    private static long parseDate(final String date){
        if(date == null) return -1;

        final TimeZone   tz = TimeZone.getTimeZone("UTC");
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);

        try{
            return df.parse(date).getTime();
        }catch(final ParseException ignored){
            return -1;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static long parseISO8601(final String timestamp){
        if(timestamp == null) return -1;

        final TimeZone   tz = TimeZone.getTimeZone("UTC");
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);

        try{
            return df.parse(timestamp).getTime();
        }catch(final ParseException ignored){
            return -1;
        }
    }

    // Forum

    static ForumCategory asForumCategory(final MyAnimeList myAnimeList, final ForumBoards.category_node schema){
        return new ForumCategory() {

            private final MyAnimeList mal               = myAnimeList;
            private final ForumBoards.category_node obj = schema;

            private final String title = obj == null ? null : obj.title;
            private final ForumBoard[] boards;

            {
                final List<ForumBoard> boards = new ArrayList<>();
                for(final ForumBoards.category_node.board board : Objects.requireNonNullElse(obj.boards, new ForumBoards.category_node.board[0]))
                    boards.add(asForumBoard(mal, board));
                this.boards = boards.toArray(new ForumBoard[0]);
            }

            // API methods

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final ForumBoard[] getForumBoards(){
                return boards;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static ForumBoard asForumBoard(final MyAnimeList myAnimeList, final ForumBoards.category_node.board schema){
        return new ForumBoard() {

            private final MyAnimeList mal                       = myAnimeList;
            private final ForumBoards.category_node.board obj   = schema;

            private final long id               = obj == null ? -1 : obj.id;
            private final String title          = obj == null ? null : obj.title;
            private final String description    = obj == null ? null : obj.description;
            private final ForumSubBoard[] subBoards;

            {
                final List<ForumSubBoard> subBoards = new ArrayList<>();
                for(final ForumBoards.category_node.board.subboard subboard : Objects.requireNonNullElse(obj.subboards, new ForumBoards.category_node.board.subboard[0]))
                    subBoards.add(asForumSubBoard(mal, subboard));
                this.subBoards = subBoards.toArray(new ForumSubBoard[0]);
            }

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final String getDescription(){
                return description;
            }

            @Override
            public final ForumSubBoard[] getSubBoards(){
                return subBoards;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static ForumSubBoard asForumSubBoard(final MyAnimeList myAnimeList, final ForumBoards.category_node.board.subboard schema){
        return new ForumSubBoard() {

            private final MyAnimeList mal                               = myAnimeList;
            private final ForumBoards.category_node.board.subboard obj  = schema;

            // API methods

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static ForumTopic asForumTopic(final MyAnimeList myAnimeList, final ForumTopicDetail.data_node schema){
        return new ForumTopic() {

            private final MyAnimeList mal                   = myAnimeList;
            private final ForumTopicDetail.data_node obj    = schema;

            private final String title  = obj == null ? null : obj.title;
            private final ForumPost[] posts;
            private final Poll poll     = asForumPoll(mal, obj == null ? null : obj.poll);

            {
                final List<ForumPost> posts = new ArrayList<>();
                for(final ForumTopicDetail.data_node.post post : Objects.requireNonNullElse(obj.posts, new ForumTopicDetail.data_node.post[0]))
                    posts.add(asForumPost(mal, post));
                this.posts = posts.toArray(new ForumPost[0]);
            }

            // API methods

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final ForumPost[] getPosts(){
                return posts;
            }

            @Override
            public final Poll getPoll(){
                return poll;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static ForumPost asForumPost(final MyAnimeList myAnimeList, final ForumTopicDetail.data_node.post schema){
        return new ForumPost() {

            private final MyAnimeList mal                       = myAnimeList;
            private final ForumTopicDetail.data_node.post obj   = schema;

            private final long id           = Long.parseLong(obj == null ? "-1" : obj.id);
            private final int number        = obj == null ? -1 : obj.number;
            private final long createdAt    = parseISO8601(obj == null ? null : obj.created_at);
            private final PostAuthor author = asForumPostAuthor(mal, obj == null ? null : obj.created_by);
            private final String body       = obj == null ? null : obj.body;
            private final String signature  = obj == null ? null : obj.signature;

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final int getNumber(){
                return number;
            }

            @Override
            public final long getCreatedAt(){
                return createdAt;
            }

            @Override
            public final PostAuthor getAuthor(){
                return author;
            }

            @Override
            public final String getBody(){
                return body;
            }

            @Override
            public final String getSignature(){
                return signature;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static PostAuthor asForumPostAuthor(final MyAnimeList myAnimeList, final ForumTopicDetail.data_node.post.author schema){
        return new PostAuthor() {

            private final MyAnimeList mal                               = myAnimeList;
            private final ForumTopicDetail.data_node.post.author obj    = schema;

            private final long id               = Long.parseLong(obj == null ? "-1" : obj.id);
            private final String name           = obj == null ? null : obj.name;
            private final String forumAvatarURL = obj == null ? null : obj.forum_avator;

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
            public final String getForumAvatarURL(){
                return forumAvatarURL;
            }

            // additional methods

            @Override
            public final User getUser(){
                return mal.getUser(name);
            }

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static Poll asForumPoll(final MyAnimeList myAnimeList, final ForumTopicDetail.data_node.poll schema){
        return new Poll() {

            private final MyAnimeList mal                       = myAnimeList;
            private final ForumTopicDetail.data_node.poll obj   = schema;

            private final long id           = Long.parseLong(obj == null ? "-1" : obj.id);
            private final String question   = obj == null ? null : obj.question;
            private final int closed        = Integer.parseInt(obj == null ? "-1" : obj.closed);
            private final PollOption[] options;

            {
                final List<PollOption> options = new ArrayList<>();
                for(final ForumTopicDetail.data_node.poll.option option : Objects.requireNonNullElse(obj.options, new ForumTopicDetail.data_node.poll.option[0]))
                    options.add(asForumPollOption(mal, option));
                this.options = options.toArray(new PollOption[0]);
            }

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getQuestion(){
                return question;
            }

            @Override
            public final int getClosed(){
                return closed;
            }

            @Override
            public final PollOption[] getOptions(){
                return options;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static PollOption asForumPollOption(final MyAnimeList myAnimeList, final ForumTopicDetail.data_node.poll.option schema){
        return new PollOption() {

            private final MyAnimeList mal                               = myAnimeList;
            private final ForumTopicDetail.data_node.poll.option obj    = schema;

            private final long id       = Long.parseLong(obj == null ? "-1" : obj.id);
            private final String text   = obj == null ? null : obj.text;
            private final int votes     = Integer.parseInt(obj == null ? "-1" : obj.votes);

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String text(){
                return text;
            }

            @Override
            public final int votes(){
                return votes;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    // User

    static User asUser(final MyAnimeList myAnimeList, final UserInformation schema){
        return new User() {

            private final MyAnimeList mal       = myAnimeList;
            private final UserInformation obj   = schema;

            private final long id                               = obj == null ? -1 : obj.id;
            private final String username                       = obj == null ? null : obj.name;
            private final String location                       = obj == null ? null : obj.location;
            private final long joinedAt                         = parseISO8601(obj == null ? null : obj.joined_at);
            private final UserAnimeStatistics animeStatistics   = asUserAnimeStatistics(mal, obj == null ? null : obj.anime_statistics);
            private final UserMangaStatistics mangaStatistics   = asUserMangaStatistics(mal, obj == null ? null : obj.manga_statistics);

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getUsername(){
                return username;
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
            public final UserMangaStatistics getMangaStatistics(){
                return mangaStatistics;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static UserAnimeStatistics asUserAnimeStatistics(final MyAnimeList myAnimeList, final UserInformation.anime_statistics schema){
        return new UserAnimeStatistics() {

            private final MyAnimeList mal                       = myAnimeList;
            private final UserInformation.anime_statistics obj  = schema;

            private final int   itemsWatching       = obj == null ? -1 : obj.num_items_watching,
                                itemsCompleted      = obj == null ? -1 : obj.num_items_completed,
                                itemsOnHold         = obj == null ? -1 : obj.num_items_on_hold,
                                itemsPlanToWatch    = obj == null ? -1 : obj.num_items_plan_to_watch,
                                itemsDropped        = obj == null ? -1 : obj.num_items_dropped,
                                items               = obj == null ? -1 : obj.num_items;
            private final double daysWatching       = obj == null ? -1 : obj.num_days_watching,
                                daysCompleted       = obj == null ? -1 : obj.num_days_completed,
                                daysOnHold          = obj == null ? -1 : obj.num_days_on_hold,
                                daysDropped         = obj == null ? -1 : obj.num_days_dropped,
                                days                = obj == null ? -1 : obj.num_days;
            @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
            private final int   episodes            = obj == null ? -1 : obj.num_episodes,
                                timesRewatched      = obj == null ? -1 : obj.num_times_rewatched;
            private final double meanScore          = obj == null ? -1 : obj.mean_score;

            @Override
            public final int getWatching(){
                return itemsWatching;
            }

            @Override
            public final int getCompleted(){
                return itemsCompleted;
            }

            @Override
            public final int getOnHold(){
                return itemsOnHold;
            }

            @Override
            public final int getDropped(){
                return itemsDropped;
            }

            @Override
            public final int getPlanToWatch(){
                return itemsPlanToWatch;
            }

            @Override
            public final int getTotal(){
                return items;
            }

            @Override
            public final double getDaysWatching(){
                return daysWatching;
            }

            @Override
            public final double getDaysCompleted(){
                return daysCompleted;
            }

            @Override
            public final double getDaysOnHold(){
                return daysOnHold;
            }

            @Override
            public final double getDaysDropped(){
                return daysDropped;
            }

            @Override
            public final double getDaysPlanToWatch(){
                return 0;
            }

            @Override
            public final double getTotalDays(){
                return days;
            }

            @Override
            public final int getTotalEpisodes(){
                return episodes;
            }

            @SuppressWarnings("SpellCheckingInspection")
            @Override
            public final int getTotalRewatched(){
                return timesRewatched;
            }

            @Override
            public final double getMeanScore(){
                return meanScore;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    static UserMangaStatistics asUserMangaStatistics(final MyAnimeList myAnimeList, final UserInformation.manga_statistics schema){
        return new UserMangaStatistics() {

            private final MyAnimeList mal                       = myAnimeList;
            private final UserInformation.manga_statistics obj  = schema;

            private final int   itemsReading        = obj == null ? -1 : obj.num_items_reading,
                                itemsCompleted      = obj == null ? -1 : obj.num_items_completed,
                                itemsOnHold         = obj == null ? -1 : obj.num_items_on_hold,
                                itemsPlanToRead     = obj == null ? -1 : obj.num_items_plan_to_read,
                                itemsDropped        = obj == null ? -1 : obj.num_items_dropped,
                                items               = obj == null ? -1 : obj.num_items;
            private final double    daysReading     = obj == null ? -1 : obj.num_days_reading,
                                    daysCompleted   = obj == null ? -1 : obj.num_days_completed,
                                    daysOnHold      = obj == null ? -1 : obj.num_days_on_hold,
                                    daysDropped     = obj == null ? -1 : obj.num_days_dropped,
                                    days            = obj == null ? -1 : obj.num_days;
            private final int volumes               = obj == null ? -1 : obj.num_volumes,
                    chapters                        = obj == null ? -1 : obj.num_chapters,
                    timesReread                     = obj == null ? -1 : obj.num_times_reread;
            private final double meanScore          = obj == null ? -1 : obj.mean_score;

            @Override
            public final int getReading(){
                return itemsReading;
            }

            @Override
            public final int getCompleted(){
                return itemsCompleted;
            }

            @Override
            public final int getOnHold(){
                return itemsOnHold;
            }

            @Override
            public final int getDropped(){
                return itemsDropped;
            }

            @Override
            public final int getPlanToRead(){
                return itemsPlanToRead;
            }

            @Override
            public final int getTotal(){
                return items;
            }

            @Override
            public final double getDaysReading(){
                return daysReading;
            }

            @Override
            public final double getDaysCompleted(){
                return daysCompleted;
            }

            @Override
            public final double getDaysOnHold(){
                return daysOnHold;
            }

            @Override
            public final double getDaysDropped(){
                return daysDropped;
            }

            @Override
            public final double getDaysPlanToRead(){
                return 0;
            }

            @Override
            public final double getTotalDays(){
                return days;
            }

            @Override
            public final int getTotalVolumes(){
                return volumes;
            }

            @Override
            public final int getTotalChapters(){
                return chapters;
            }

            @Override
            public final int getTotalReread(){
                return timesReread;
            }

            @Override
            public final double getMeanScore(){
                return meanScore;
            }

            // additional methods

            @Override
            public final String toString(){
                return AutomatedToString(this);
            }

        };
    }

    // toString

    public static String AutomatedToString(final Object obj){
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
                if(!field.getName().equals("mal") && !field.getName().equals("obj") && !field.getName().contains("$"))
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
