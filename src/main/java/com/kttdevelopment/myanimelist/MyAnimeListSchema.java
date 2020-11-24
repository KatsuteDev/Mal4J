package com.kttdevelopment.myanimelist;

import java.util.Arrays;

/**
 * Mapping for {@link MyAnimeListService}. Used for objects in {@link MyAnimeListSchemaMapping}.
 *
 * @see MyAnimeListService
 * @see MyAnimeListSchemaMapping
 */
abstract class MyAnimeListSchema {

    static class schema {

        static class node {

            long            id;
            String          title;
            main_picture    main_picture;

            static class picture{

                String  medium,
                        large;

                @Override
                public String toString(){
                    return "picture{" +
                           "medium='" + medium + '\'' +
                           ", large='" + large + '\'' +
                           '}';
                }

            }

            static class main_picture extends picture {

                @Override
                public String toString(){
                    return "main_picture{" +
                           "medium='" + medium + '\'' +
                           ", large='" + large + '\'' +
                           '}';
                }

            }

            @Override
            public String toString(){
                return "node{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", main_picture=" + main_picture +
                       '}';
            }

        }

        static class paging {

            String next;

            @Override
            public String toString(){
                return "paging{" +
                       "next='" + next + '\'' +
                       '}';
            }

        }

        static class alternative_titles {

            String[]    synonyms;
            String      en,
                        ja;

            @Override
            public String toString(){
                return "alternative_titles{" +
                       "synonyms=" + Arrays.toString(synonyms) +
                       ", en='" + en + '\'' +
                       ", ja='" + ja + '\'' +
                       '}';
            }

        }

        static class genre extends IDN {

            @Override
            public String toString(){
                return "genre{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
            }

        }

        static class related {

            node  node;
            String  relation_type,
                    relation_type_formatted;

            @Override
            public String toString(){
                return "related{" +
                       "node=" + node +
                       ", relation_type='" + relation_type + '\'' +
                       ", relation_type_formatted='" + relation_type_formatted + '\'' +
                       '}';
            }

        }

        static class recommendation {

            node  node;
            int     num_recommendations;

            @Override
            public String toString(){
                return "recommendation{" +
                       "node=" + node +
                       ", num_recommendations=" + num_recommendations +
                       '}';
            }

        }

        static class ranking {

            ranking_node[]  data;
            schema.paging   paging;

            @Override
            public String toString(){
                return "ranking{" +
                       "data=" + Arrays.toString(data) +
                       ", paging=" + paging +
                       '}';
            }

            static class ranking_node {

                schema.node node;
                ranking_scm ranking;

                @Override
                public String toString(){
                    return "ranking_node{" +
                           "node=" + node +
                           ", ranking=" + ranking +
                           '}';
                }

                static class ranking_scm {

                    int rank;

                    @Override
                    public String toString(){
                        return "ranking{" +
                               "rank=" + rank +
                               '}';
                    }

                }

            }
        }

        static class season {

            int     year;
            String  season;

            @Override
            public String toString(){
                return "season{" +
                       "year=" + year +
                       ", season='" + season + '\'' +
                       '}';
            }

        }

        static class IDN {

            long    id;
            String  name;

            @Override
            public String toString(){
                return "genre{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
            }

        }

    }

    static class AnimeList {

        schema.node[] data;
        schema.paging paging;

        @Override
        public String toString(){
            return "AnimeList{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

    }

    static class AnimeDetails extends schema.node {

        schema.alternative_titles   alternative_titles;
        String                      start_date,
                                    end_date,
                                    synopsis;
        double                      mean;
        int                         rank,
                                    popularity,
                                    num_list_users,
                                    num_scoring_users;
        String                      nsfw,
                                    created_at,
                                    updated_at,
                                    media_type,
                                    status;
        schema.genre[]              genres;
        my_list_status              my_list_status;
        int                         num_episodes;
        start_season                start_season;
        broadcast                   broadcast;
        String                      source;
        int                         average_episode_duration;
        String                      rating;
        schema.node.picture[]       pictures;
        String                      background;
        schema.related[]            related_anime,
                                    related_manga;
        schema.recommendation[]     recommendations;
        studio[]                    studios;
        statistics                  statistics;

        @Override
        public String toString(){
            return "AnimeDetails{" +
                   "id=" + id +
                   ", title='" + title + '\'' +
                   ", main_picture=" + main_picture +
                   ", alternative_titles=" + alternative_titles +
                   ", start_date='" + start_date + '\'' +
                   ", end_date='" + end_date + '\'' +
                   ", synopsis='" + synopsis + '\'' +
                   ", mean=" + mean +
                   ", rank=" + rank +
                   ", popularity=" + popularity +
                   ", num_list_users=" + num_list_users +
                   ", num_scoring_users=" + num_scoring_users +
                   ", nsfw='" + nsfw + '\'' +
                   ", created_at='" + created_at + '\'' +
                   ", updated_at='" + updated_at + '\'' +
                   ", media_type='" + media_type + '\'' +
                   ", status='" + status + '\'' +
                   ", genres=" + Arrays.toString(genres) +
                   ", my_list_status=" + my_list_status +
                   ", num_episodes=" + num_episodes +
                   ", start_season=" + start_season +
                   ", broadcast=" + broadcast +
                   ", source='" + source + '\'' +
                   ", average_episode_duration=" + average_episode_duration +
                   ", rating='" + rating + '\'' +
                   ", pictures=" + Arrays.toString(pictures) +
                   ", background='" + background + '\'' +
                   ", related_anime=" + Arrays.toString(related_anime) +
                   ", related_manga=" + Arrays.toString(related_manga) +
                   ", recommendations=" + Arrays.toString(recommendations) +
                   ", studios=" + Arrays.toString(studios) +
                   ", statistics=" + statistics +
                   '}';
        }

        @SuppressWarnings("SpellCheckingInspection")
        static class my_list_status {

            String  status;
            int     score,
                    num_episodes_watched;
            boolean is_rewatching;
            String  updated_at;

            @Override
            public String toString(){
                return "my_list_status{" +
                       "status='" + status + '\'' +
                       ", score=" + score +
                       ", num_episodes_watched=" + num_episodes_watched +
                       ", is_rewatching=" + is_rewatching +
                       ", updated_at='" + updated_at + '\'' +
                       '}';
            }

        }

        static class start_season extends schema.season {

            @Override
            public String toString(){
                return "start_season{" +
                       "year=" + year +
                       ", season='" + season + '\'' +
                       '}';
            }

        }

        static class broadcast {

            String  day_of_week,
                    start_time;

            @Override
            public String toString(){
                return "broadcast{" +
                       "day_of_week='" + day_of_week + '\'' +
                       ", start_time='" + start_time + '\'' +
                       '}';
            }

        }

        static class studio extends schema.IDN {

            @Override
            public String toString(){
                return "studio{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
            }

        }

        static class statistics {

            status  status;
            int     num_list_users;

            @Override
            public String toString(){
                return "statistics{" +
                       "status=" + status +
                       ", num_list_users=" + num_list_users +
                       '}';
            }

            static class status {

                String  watching,
                        completed,
                        on_hold,
                        dropped,
                        plan_to_watch;

                @Override
                public String toString(){
                    return "status{" +
                           "watching='" + watching + '\'' +
                           ", completed='" + completed + '\'' +
                           ", on_hold='" + on_hold + '\'' +
                           ", dropped='" + dropped + '\'' +
                           ", plan_to_watch='" + plan_to_watch + '\'' +
                           '}';
                }

            }

        }

    }

    static class AnimeRanking extends schema.ranking {

        @Override
        public String toString(){
            return "AnimeRanking{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

    }

    static class SeasonalAnime {

        schema.node[]   data;
        schema.paging   paging;
        schema.season   season;

        @Override
        public String toString(){
            return "SeasonalAnime{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   ", season=" + season +
                   '}';
        }

    }

    static class SuggestedAnime extends AnimeList {

        @Override
        public String toString(){
            return "SuggestedAnime{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    static class UpdateAnimeList {

        String      status;
        int         score,
                    num_watched_episodes;
        boolean     is_rewatching;
        String      updated_at;
        int         priority,
                    num_times_rewatched,
                    rewatch_value;
        String[]    tags;
        String      comments;

        @Override
        public String toString(){
            return "UpdateAnimeList{" +
                   "status='" + status + '\'' +
                   ", score=" + score +
                   ", num_watched_episodes=" + num_watched_episodes +
                   ", is_rewatching=" + is_rewatching +
                   ", updated_at='" + updated_at + '\'' +
                   ", priority=" + priority +
                   ", num_times_rewatched=" + num_times_rewatched +
                   ", rewatch_value=" + rewatch_value +
                   ", tags=" + Arrays.toString(tags) +
                   ", comments='" + comments + '\'' +
                   '}';
        }

    }

    static class UserAnimeList {

        list_node[]     data;
        schema.paging   paging;

        @Override
        public String toString(){
            return "UserAnimeList{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

        static class list_node {

            schema.node                 node;
            AnimeDetails.my_list_status list_status;

            @Override
            public String toString(){
                return "list_node{" +
                       "node=" + node +
                       ", list_status=" + list_status +
                       '}';
            }

        }

    }

    static class ForumBoards {

        category_node[] categories;

        @Override
        public String toString(){
            return "ForumBoards{" +
                   "categories=" + Arrays.toString(categories) +
                   '}';
        }

        static class category_node {

            String  title;
            board[] boards;

            @Override
            public String toString(){
                return "category_node{" +
                       "title='" + title + '\'' +
                       ", boards=" + Arrays.toString(boards) +
                       '}';
            }

            @SuppressWarnings("SpellCheckingInspection")
            static class board {

                long    id;
                String  title,
                        description;
                subboard[] subboards;

                @Override
                public String toString(){
                    return "board{" +
                           "id=" + id +
                           ", title='" + title + '\'' +
                           ", description='" + description + '\'' +
                           ", subboards=" + Arrays.toString(subboards) +
                           '}';
                }

                static class subboard {

                    @Override
                    public String toString(){
                        return "subboard{}";
                    }

                }

            }

        }

    }

    static class ForumTopicDetail {

        data_node       data;
        schema.paging   paging;

        @Override
        public String toString(){
            return "ForumTopicDetail{" +
                   "data=" + data +
                   ", paging=" + paging +
                   '}';
        }

        static class data_node {

            String  title;
            post[]  posts;
            poll    poll;

            @Override
            public String toString(){
                return "data_node{" +
                       "title='" + title + '\'' +
                       ", posts=" + Arrays.toString(posts) +
                       ", poll=" + poll +
                       '}';
            }

            static class post {

                String  id;
                int     number;
                String  created_at;
                author  created_by;
                String  body,
                        signature;

                @Override
                public String toString(){
                    return "post{" +
                           "id='" + id + '\'' +
                           ", number=" + number +
                           ", created_at='" + created_at + '\'' +
                           ", created_by=" + created_by +
                           ", body='" + body + '\'' +
                           ", signature='" + signature + '\'' +
                           '}';
                }

                static class author {

                    String  id,
                            name,
                            forum_avator; // fixme: API typo

                    @Override
                    public String toString(){
                        return "author{" +
                               "id='" + id + '\'' +
                               ", name='" + name + '\'' +
                               ", forum_avator='" + forum_avator + '\'' +
                               '}';
                    }

                }

            }

            static class poll {

                String      id,
                            question,
                            closed;
                option[]    options;

                @Override
                public String toString(){
                    return "poll{" +
                           "id='" + id + '\'' +
                           ", question='" + question + '\'' +
                           ", closed='" + closed + '\'' +
                           ", options=" + Arrays.toString(options) +
                           '}';
                }

                static class option {

                    String  id,
                            text,
                            votes;

                    @Override
                    public String toString(){
                        return "options{" +
                               "id='" + id + '\'' +
                               ", text='" + text + '\'' +
                               ", votes='" + votes + '\'' +
                               '}';
                    }

                }

            }

        }

    }

    static class ForumTopic {

        data_node[]     data;
        schema.paging   paging;

        static class data_node {

            long    id;
            String  title,
                    created_at;
            creator created_by;
            int     num_of_posts;
            String  last_post_created_at;
            creator last_post_created_by;
            int     is_locked;

            @Override
            public String toString(){
                return "data_node{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", created_at='" + created_at + '\'' +
                       ", created_by=" + created_by +
                       ", num_of_posts=" + num_of_posts +
                       ", last_post_created_at='" + last_post_created_at + '\'' +
                       ", last_post_created_by=" + last_post_created_by +
                       ", is_locked=" + is_locked +
                       '}';
            }

            static class creator {

                long    id;
                String  name;

                @Override
                public String toString(){
                    return "creator{" +
                           "id=" + id +
                           ", name='" + name + '\'' +
                           '}';
                }

            }

        }

    }

    static class MangaList {

        schema.node[] data;
        schema.paging paging;

        @Override
        public String toString(){
            return "MangaList{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

    }

    static class MangaDetails extends schema.node {

        schema.alternative_titles   alternative_titles;
        String                      start_date,
                                    end_date,
                                    synopsis;
        double                      mean;
        int                         rank,
                                    popularity,
                                    num_list_users,
                                    num_scoring_users;
        String                      nsfw,
                                    created_at,
                                    updated_at,
                                    media_type,
                                    status;
        schema.genre[]              genres;
        my_list_status              my_list_status;
        int                         num_volumes,
                                    num_chapters;
        author[]                    authors;
        schema.node.picture[]       pictures;
        String                      background;
        schema.related[]            related_anime,
                                    related_manga;
        schema.recommendation[]     recommendations;
        serialization[]             serialization;
        statistics                  statistics;

        @Override
        public String toString(){
            return "MangaDetails{" +
                   "id=" + id +
                   ", title='" + title + '\'' +
                   ", main_picture=" + main_picture +
                   ", alternative_titles=" + alternative_titles +
                   ", start_date='" + start_date + '\'' +
                   ", end_date='" + end_date + '\'' +
                   ", synopsis='" + synopsis + '\'' +
                   ", mean=" + mean +
                   ", rank=" + rank +
                   ", popularity=" + popularity +
                   ", num_list_users=" + num_list_users +
                   ", num_scoring_users=" + num_scoring_users +
                   ", nsfw='" + nsfw + '\'' +
                   ", created_at='" + created_at + '\'' +
                   ", updated_at='" + updated_at + '\'' +
                   ", media_type='" + media_type + '\'' +
                   ", status='" + status + '\'' +
                   ", genres=" + Arrays.toString(genres) +
                   ", my_list_status=" + my_list_status +
                   ", num_volumes=" + num_volumes +
                   ", num_chapters=" + num_chapters +
                   ", authors=" + Arrays.toString(authors) +
                   ", pictures=" + Arrays.toString(pictures) +
                   ", background='" + background + '\'' +
                   ", related_anime=" + Arrays.toString(related_anime) +
                   ", related_manga=" + Arrays.toString(related_manga) +
                   ", recommendations=" + Arrays.toString(recommendations) +
                   ", serialization=" + Arrays.toString(serialization) +
                   ", statistics=" + statistics +
                   '}';
        }

        static class my_list_status {

            String  status;
            boolean is_rereading;
            int     num_volumes_read,
                    num_chapters_read,
                    score;
            String updated_at;

            @Override
            public String toString(){
                return "my_list_status{" +
                       "status='" + status + '\'' +
                       ", is_rereading=" + is_rereading +
                       ", num_volumes_read=" + num_volumes_read +
                       ", num_chapters_read=" + num_chapters_read +
                       ", score=" + score +
                       ", updated_at='" + updated_at + '\'' +
                       '}';
            }

        }

        static class author {

            node    node;
            String  role;

            @Override
            public String toString(){
                return "author{" +
                       "node=" + node +
                       ", role='" + role + '\'' +
                       '}';
            }

            static class node {

                long    id;
                String  first_name,
                        last_name;

                @Override
                public String toString(){
                    return "node{" +
                           "id=" + id +
                           ", first_name='" + first_name + '\'' +
                           ", last_name='" + last_name + '\'' +
                           '}';
                }

            }

        }

        static class serialization {

            node    node;
            String  role;

            @Override
            public String toString(){
                return "serialization{" +
                       "node=" + node +
                       ", role='" + role + '\'' +
                       '}';
            }

            static class node {

                long    id;
                String  name;

                @Override
                public String toString(){
                    return "node{" +
                           "id=" + id +
                           ", name='" + name + '\'' +
                           '}';
                }

            }

        }

        static class statistics {

            status status;
            int     num_list_users;

            @Override
            public String toString(){
                return "statistics{" +
                       "status=" + status +
                       ", num_list_users=" + num_list_users +
                       '}';
            }

            static class status {

                String  reading,
                        completed,
                        on_hold,
                        dropped,
                        plan_to_read;

                @Override
                public String toString(){
                    return "status{" +
                           "reading='" + reading + '\'' +
                           ", completed='" + completed + '\'' +
                           ", on_hold='" + on_hold + '\'' +
                           ", dropped='" + dropped + '\'' +
                           ", plan_to_read='" + plan_to_read + '\'' +
                           '}';
                }

            }

        }

    }

    static class MangaRanking extends schema.ranking {

        @Override
        public String toString(){
            return "MangaRanking{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

    }

    static class UpdateMangaList {

        String      status;
        boolean     is_rereading;
        int         num_volumes_read,
                    num_chapters_read,
                    score;
        String      updated_at;
        int         priority,
                    num_times_reread,
                    reread_value;
        String[]    tags;
        String      comments;

        @Override
        public String toString(){
            return "UpdateMangaList{" +
                   "status='" + status + '\'' +
                   "is_rereading=" + is_rereading +
                   ", num_volumes_read=" + num_volumes_read +
                   ", num_chapters_read=" + num_chapters_read +
                   ", score=" + score +
                   ", updated_at='" + updated_at + '\'' +
                   ", priority=" + priority +
                   ", num_times_reread=" + num_times_reread +
                   ", reread_value=" + reread_value +
                   ", tags=" + Arrays.toString(tags) +
                   ", comments='" + comments + '\'' +
                   '}';
        }

    }

    static class UserMangaList {

        list_node[]     data;
        schema.paging   paging;

        @Override
        public String toString(){
            return "UserMangaList{" +
                   "data=" + Arrays.toString(data) +
                   ", paging=" + paging +
                   '}';
        }

        static class list_node {

            schema.node                 node;
            MangaDetails.my_list_status list_status;

            @Override
            public String toString(){
                return "list_node{" +
                       "node=" + node +
                       ", list_status=" + list_status +
                       '}';
            }

        }

    }

    static class UserInformation {

        long                id;
        String              name,
                            location,
                            joined_at;
        anime_statistics    anime_statistics;
        manga_statistics    manga_statistics;

        @Override
        public String toString(){
            return "UserInformation{" +
                   "id=" + id +
                   ", name='" + name + '\'' +
                   ", location='" + location + '\'' +
                   ", joined_at='" + joined_at + '\'' +
                   ", anime_statistics=" + anime_statistics +
                   ", manga_statistics=" + manga_statistics +
                   '}';
        }

        @SuppressWarnings("SpellCheckingInspection")
        static class anime_statistics {

            int     num_items_watching,
                    num_items_completed,
                    num_items_on_hold,
                    num_items_plan_to_watch,
                    num_items_dropped,
                    num_items;
            double  num_days_watching,
                    num_days_completed,
                    num_days_on_hold,
                    num_days_dropped,
                    num_days;
            int     num_episodes,
                    num_times_rewatched;
            double  mean_score;

            @Override
            public String toString(){
                return "anime_statistics{" +
                       "num_items_watching=" + num_items_watching +
                       ", num_items_completed=" + num_items_completed +
                       ", num_items_on_hold=" + num_items_on_hold +
                       ", num_items_plan_to_watch=" + num_items_plan_to_watch +
                       ", num_items_dropped=" + num_items_dropped +
                       ", num_items=" + num_items +
                       ", num_days_watching=" + num_days_watching +
                       ", num_days_completed=" + num_days_completed +
                       ", num_days_on_hold=" + num_days_on_hold +
                       ", num_days_dropped=" + num_days_dropped +
                       ", num_days=" + num_days +
                       ", num_episodes=" + num_episodes +
                       ", num_times_rewatched=" + num_times_rewatched +
                       ", mean_score=" + mean_score +
                       '}';
            }

        }

        static class manga_statistics {

            int     num_items_reading,
                    num_items_completed,
                    num_items_on_hold,
                    num_items_plan_to_read,
                    num_items_dropped,
                    num_items;
            double  num_days_reading,
                    num_days_completed,
                    num_days_on_hold,
                    num_days_dropped,
                    num_days;
            int     num_volumes,
                    num_chapters,
                    num_times_reread;
            double  mean_score;

            @Override
            public String toString(){
                return "manga_statistics{" +
                       "num_items_reading=" + num_items_reading +
                       ", num_items_completed=" + num_items_completed +
                       ", num_items_on_hold=" + num_items_on_hold +
                       ", num_items_plan_to_read=" + num_items_plan_to_read +
                       ", num_items_dropped=" + num_items_dropped +
                       ", num_items=" + num_items +
                       ", num_days_reading=" + num_days_reading +
                       ", num_days_completed=" + num_days_completed +
                       ", num_days_on_hold=" + num_days_on_hold +
                       ", num_days_dropped=" + num_days_dropped +
                       ", num_days=" + num_days +
                       ", num_volumes=" + num_volumes +
                       ", num_chapters=" + num_chapters +
                       ", num_times_reread=" + num_times_reread +
                       ", mean_score=" + mean_score +
                       '}';
            }

        }

    }

}
