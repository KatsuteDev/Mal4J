package com.kttdevelopment.myanimelist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.Common.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.SubLevelObject.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.TopLevelObject.*;

/**
 * Represents the MyAnimeList API response schema.
 *
 * @see MyAnimeListService
 * @see Call
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings({"SpellCheckingInspection","unused"})
abstract class MyAnimeListAPIResponse {

    // commonly used formats
    static abstract class Common {

        static class Pagination<T> extends AutomatedToString {

            T[] data;
            Paging paging;

            static class Paging extends AutomatedToString {

                String previous;
                String next;

            }

        }

        static class Node<T extends AutomatedToString> extends AutomatedToString {

            T node;

        }

        static class IDN extends AutomatedToString {

            long id;
            String name;

        }

    }

    // api calls
    static abstract class Call {

        static class GetAnimeList extends Pagination<Node<Anime>> {

        }

        static class GetAnime extends Anime{

            Picture[] pictures;
            String background;
            RelatedAnimeEdge[] related_anime;
            RelatedMangaEdge[]      related_manga;
            Recommendation<Anime>[] recommendations;
            Statistics              statistics;

            static class Statistics extends AutomatedToString {

                int num_list_users;
                Status status;

                static class Status extends AutomatedToString {

                    int watching, completed, on_hold, dropped, plan_to_watch;

                }

            }

        }

        static class GetAnimeRanking extends Pagination<Ranking<Anime>>{

        }

        static class GetSeasonalAnime extends GetAnimeList {

        }

        static class GetSuggestedAnime extends GetAnimeList {

        }

        static class UpdateUserAnimeList extends AutomatedToString {

            String status;
            int score, num_watched_episodes;
            boolean is_rewatching;
            String start_date, end_date;
            int priority, num_times_rewatched, rewatch_value;
            String[] tags;
            String comments, updated_at;

        }

        static class GetUserAnimeList extends Pagination<ListEdge<Anime,UpdateUserAnimeList>> {

        }

        static class GetForumBoards extends AutomatedToString {

            ForumCategory[] categories;

        }

        static class GetForumTopicDetail /* extends Pagination<ForumTopicData> */ { // API has conflicting schemas

            ForumTopicData data;
            Pagination.Paging paging;

        }

        static class GetForumTopics extends Pagination<ForumTopicsData> {

        }

        static class GetMangaList extends Pagination<Node<Manga>> {

        }

        static class GetManga extends Manga {

            Picture[] pictures;
            String background;
            RelatedAnimeEdge[] related_anime;
            RelatedMangaEdge[] related_manga;
            Recommendation<Manga>[] recommendations;
            MagazineRelationEdge[] serialization;

            static class MagazineRelationEdge extends Node<IDN> {

                String role;

            }

        }

        static class GetMangaRanking extends Pagination<Ranking<Manga>> {

        }

        static class UpdateUserMangaList extends AutomatedToString {

            String status;
            boolean is_rereading;
            String start_date, end_date;
            int score, num_volumes_read, num_chapters_read, priority, num_times_reread, reread_value;
            String[] tags;
            String comments, updated_at;

        }

        static class GetUserMangaList extends Pagination<ListEdge<Manga,UpdateUserMangaList>> {

        }

        static class GetUserInformation extends AutomatedToString {

            long id;
            String name, picture, gender, birthday, location, joined_at;
            AnimeStatistics anime_statistics;
            String time_zone;
            boolean is_supporter;

            static class AnimeStatistics extends AutomatedToString {

                int num_items_watching, num_items_completed, num_items_on_hold, num_items_dropped, num_items_plan_to_watch, num_items;
                float num_days_watched, num_days_watching, num_days_completed, num_days_on_hold, num_days_dropped, num_days;
                int num_episodes, num_times_rewatched;
                float mean_score;

            }

        }

    }

    // shared formats
    static abstract class SubLevelObject {

        static class Recommendation<T extends AutomatedToString> extends Node<T> {

            int num_recommendations;

        }

        static class ListEdge<T extends AutomatedToString,L> extends Node<T> {

            L list_status;

        }

        static class Ranking<T extends AutomatedToString> extends Node<T> {

            RankingInfo ranking;

            static class RankingInfo extends AutomatedToString {

                int rank, previous_rank;

            }

        }

        static class Picture extends AutomatedToString {

            String large, medium;

        }

        static class AlternativeTitles extends AutomatedToString {

            String[] synonyms;
            String en;
            String ja;

        }

        // classified

        static class RelatedAnimeEdge extends Node<Anime> {

            String relation_type;
            String relation_type_formatted;

        }

        RelatedMangaEdge related_manga;

        static class RelatedMangaEdge extends Node<Manga> {

            String relation_type;
            String relation_type_formatted;

        }

    }

    static abstract class TopLevelObject {

        static class Anime extends AutomatedToString {

            long id;
            String title;
            Picture main_picture;
            AlternativeTitles alternative_titles;
            String start_date, end_date, synopsis;
            float mean;
            int rank, popularity, num_list_users, num_scoring_users;
            String nsfw;
            IDN[] genres;
            String created_at, updated_at, media_type, status;
            MyListStatus my_list_status;

            static class MyListStatus extends AutomatedToString {

                String status;
                int score, num_watched_episodes;
                boolean is_rewatching;
                String start_date, finish_date;
                int priority, num_times_rewatched, rewatch_value;
                String[] tags;
                String comments, updated_at;

            }

            int num_episodes;
            StartSeason start_season;

            static class StartSeason extends AutomatedToString {

                int year;
                String season;

            }

            Broadcast broadcast;

            static class Broadcast extends AutomatedToString {

                String day_of_the_week, start_time;

            }

            String source;
            int average_episode_duration;
            String rating;
            IDN[] studios;

        }

        static class ForumCategory extends AutomatedToString {

            String title;
            ForumBoard[] boards;

            static class ForumBoard extends AutomatedToString {

                long id;
                String title, description;
                SubBoard[] subboards;

                static class SubBoard extends AutomatedToString {

                    long id;
                    String title;

                }

            }

        }

        static class ForumTopicData extends AutomatedToString {

            String title;
            Post[] posts;

            static class Post extends AutomatedToString {

                long id;
                int number;
                String created_at;
                PostCreator created_by;
                String body, signature;

                static class PostCreator extends AutomatedToString {

                    long id;
                    String name, forum_avator; // fixme: API typo

                }

            }

            Poll poll; // API has conflicting schemas

            static class Poll extends AutomatedToString {

                long id;
                String question;
                boolean close;
                PollOption[] options;

                static class PollOption extends AutomatedToString {

                    long id;
                    String text;
                    int votes;

                }

            }

        }

        static class ForumTopicsData extends AutomatedToString {

            long id;
            String title, created_at;
            IDN created_by;
            int number_of_posts;
            String last_post_created_at;
            IDN last_post_created_by;
            boolean is_locked;

        }

        static class Manga extends AutomatedToString {

            long id;
            String title;
            Picture main_picture;
            AlternativeTitles alternative_titles;
            String start_date, end_date, synopsis;
            float mean;
            int rank, popularity, num_list_users, num_scoring_users;
            String nsfw;
            IDN[] genres;
            String created_at, updated_at, media_type, status;
            MyListStatus my_list_status;

            static class MyListStatus extends AutomatedToString {

                String status;
                int score, num_volumes_read, num_chapters_read;
                boolean is_rereading;
                String start_date, finish_date;
                int priority, num_times_reread, reread_value;
                String[] tags;
                String comments, updated_at;

            }

            int num_volumes, num_chapters;
            PersonRoleEdge[] authors;

            static class PersonRoleEdge extends Node<PersonRoleEdge.PersonBase> {

                String role;

                static class PersonBase extends AutomatedToString {

                    long id;
                    String first_name, last_name;

                }

            }

        }

    }

    //

    private static class AutomatedToString {

        @Override
        public final String toString(){
            final Class<?> _class = this.getClass();

            final List<Field[]> fieldSets = new ArrayList<>();
            fieldSets.add(_class.getDeclaredFields());

            Class<?> _super = _class; // add all inherited fields
            while((_super = _super.getSuperclass()) != null)
                fieldSets.add(_super.getDeclaredFields());

            Collections.reverse(fieldSets);

            final StringBuilder OUT = new StringBuilder();
            OUT.append(_class.getSimpleName()).append('{');
            for(final Field[] set : fieldSets){ // print all fields
                for(final Field field : set){
                    try{
                        final Object value = field.get(this);
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

}
