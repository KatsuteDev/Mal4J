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
import com.kttdevelopment.myanimelist.manga.property.Author;
import com.kttdevelopment.myanimelist.manga.property.MangaStatistics;
import com.kttdevelopment.myanimelist.manga.property.Publisher;
import com.kttdevelopment.myanimelist.property.AlternativeTitles;
import com.kttdevelopment.myanimelist.property.Picture;
import com.kttdevelopment.myanimelist.property.RelationType;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;

import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.Common.*;

/**
 * Represents the MyAnimeList API response as an object.
 *
 * @see MyAnimeListAPIResponse
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
abstract class MyAnimeListAPIResponseMapping {

    static abstract class Anime {

        static AnimeStatistics asAnimeStatistics(final MyAnimeList mal, final Call.GetAnime.Statistics schema){
            return new AnimeStatistics() {

                private final int   watching    = requireNonNullElse(() -> schema.status.watching, 0),
                                    completed   = requireNonNullElse(() -> schema.status.completed, 0),
                                    onHold      = requireNonNullElse(() -> schema.status.on_hold, 0),
                                    dropped     = requireNonNullElse(() -> schema.status.dropped, 0),
                                    planToWatch = requireNonNullElse(() -> schema.status.plan_to_watch, 0),
                                    userCount   = requireNonNullElse(() -> schema.num_list_users, 0);

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

        // todo
        static Anime asAnime(final MyAnimeList mal, final Call.GetAnime schema){
            return null;
        }

        // todo
        static AnimeListStatus asAnimeListStatus(final MyAnimeList mal, final Call.UpdateUserAnimeList schema) {
            return null;
        }

        // todo
        static AnimePreview asAnimePreview(final MyAnimeList mal, final TopLevelObject.Anime schema){
            return null;
        }

        static AnimeRanking asAnimeRanking(final MyAnimeList mal, final SubLevelObject.Ranking<TopLevelObject.Anime> schema){
            return new AnimeRanking() {

                private final AnimePreview anime    = requireNonNull(() -> asAnimePreview(mal, schema.node));
                private final int ranking           = requireNonNullElse(() -> schema.ranking.rank, 0);
                private final int previousRanking   = requireNonNullElse(() -> schema.ranking.previous_rank, 0);

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
                private final int recommendations   = requireNonNullElse(() -> schema.num_recommendations, 0);

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

        // todo
        static UserAnimeListStatus asUserAnimeListStatus(final MyAnimeList mal, final Call.UpdateUserAnimeList schema){
            return null;
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
                public final String toString() {
                    return AutomatedToString(this);
                }

            };
        }

        // todo
        static ForumTopicDetail asForumTopicDetail(final MyAnimeList mal, final Call.GetForumTopicDetail schema){
            return null;
        }

        static Poll asPoll(final MyAnimeList mal, final TopLevelObject.ForumTopicData.Poll schema, final ForumTopic forumTopic){
            return new Poll() {

                private final long id = requireNonNullElse(() -> schema.id, -1L);
                private final String question = requireNonNull(() -> schema.question);
                private final boolean isClosed = requireNonNull(() -> schema.close);
                private final PollOption[] options = requireNonNull(() -> {
                    final List<PollOption> options = new ArrayList<>();
                    for (TopLevelObject.ForumTopicData.Poll.PollOption option : schema.options)
                        options.add(asPollOption(mal, option, this));
                    return options.toArray(new PollOption[0]);
                });

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
                private final int votes     = requireNonNullElse(() -> schema.votes, 0);

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

        // todo
        static PostAuthor asPostAuthor(final MyAnimeList mal, final TopLevelObject.ForumTopicData.Post.PostCreator schema){
            return null;
        }

        // todo
        static ForumBoard asForumBoard(final MyAnimeList mal, final TopLevelObject.ForumCategory.ForumBoard schema, final ForumCategory forumCategory){
            return null;
        }

        // todo
        static ForumCategory asForumCategory(final MyAnimeList mal, final TopLevelObject.ForumCategory schema){
            return null;
        }

        // todo
        static ForumSubBoard asForumSubBoard(final MyAnimeList mal, final TopLevelObject.ForumCategory.ForumBoard.SubBoard schema, final TopLevelObject.ForumCategory.ForumBoard forumBoard){
            return null;
        }

        // todo
        static ForumTopic asForumTopic(final MyAnimeList mal, final TopLevelObject.ForumTopicData schema){
            return null;
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

        // todo
        static Manga asManga(final MyAnimeList mal, final Call.GetManga schema){
            return null;
        }

        // todo
        static MangaListStatus asMangaListStatus(final MyAnimeList mal, final Call.UpdateUserMangaList schema){
            return null;
        }

        // todo
        static MangaPreview asMangaPreview(final MyAnimeList mal, final TopLevelObject.Manga schema){
            return null;
        }

        static MangaRanking asMangaRanking(final MyAnimeList mal, final SubLevelObject.Ranking<TopLevelObject.Manga> schema){
            return new MangaRanking() {

                private final MangaPreview manga    = requireNonNull(() -> asMangaPreview(mal, schema.node));
                private final int ranking           = requireNonNullElse(() -> schema.ranking.rank, 0);
                private final int previousRanking   = requireNonNullElse(() -> schema.ranking.previous_rank, 0);

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
                private final int recommendations   = requireNonNullElse(() -> schema.num_recommendations, 0);

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

        // todo
        static UserMangaListStatus asUserMangaListStatus(final MyAnimeList mal, final Call.UpdateUserMangaList schema){
            return null;
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

    private static long parseDate(final String date){
        if(date == null) return -1;

        final TimeZone   tz = TimeZone.getTimeZone("UTC");
        final DateFormat df = new SimpleDateFormat(date.length() == 10 ? "yyyy-MM-dd" : date.length() == 7 ? "yyyy-MM" : "yyyy");
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

    @SuppressWarnings("SpellCheckingInspection")
    static Time asTime(final String time){
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
