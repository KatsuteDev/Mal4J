package com.kttdevelopment.myanimelist;

import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("unused")
@Deprecated
abstract class MyAnimeListSchema {
/*

    static class schema {

        static class node extends AutomatedToString {

            long            id;
            String          title;
            main_picture    main_picture;

            static class picture extends AutomatedToString{

                String  medium,
                        large;

            }

            static class main_picture extends picture { }

        }

        static class paging extends AutomatedToString {

            String next;

        }

        static class alternative_titles extends AutomatedToString{

            String[]    synonyms;
            String      en,
                        ja;

        }

        static class genre extends IDN { }

        static class related extends AutomatedToString {

            node  node;
            String  relation_type,
                    relation_type_formatted;

        }

        static class recommendation extends AutomatedToString {

            node  node;
            int     num_recommendations;

        }

        static class ranking extends AutomatedToString {

            ranking_node[]  data;
            schema.paging   paging;

            static class ranking_node extends AutomatedToString {

                schema.node node;
                ranking_scm ranking;

                static class ranking_scm extends AutomatedToString {

                    int rank;

                }

            }
        }

        static class season extends AutomatedToString {

            int     year;
            String  season;
        }

        static class IDN extends AutomatedToString {

            long    id;
            String  name;

        }

    }

    static class AnimeList extends AutomatedToString {

        schema.node[] data;
        schema.paging paging;

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

        static class my_list_status extends AutomatedToString {

            String  status;
            int     score,
                    num_episodes_watched;
            boolean is_rewatching;
            String  updated_at;

        }

        static class start_season extends schema.season { }

        static class broadcast extends AutomatedToString {

            String  day_of_week,
                    start_time;

        }

        static class studio extends schema.IDN { }

        static class statistics extends AutomatedToString {

            status  status;
            int     num_list_users;

            static class status extends AutomatedToString {

                String  watching,
                        completed,
                        on_hold,
                        dropped,
                        plan_to_watch;

            }

        }

    }

    static class AnimeRanking extends schema.ranking { }

    static class SeasonalAnime extends AutomatedToString {

        schema.node[]   data;
        schema.paging   paging;
        schema.season   season;

    }

    static class SuggestedAnime extends AnimeList { }

    @SuppressWarnings("SpellCheckingInspection")
    static class UpdateAnimeList extends AutomatedToString {

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

    }

    static class UserAnimeList extends AutomatedToString {

        list_node[]     data;
        schema.paging   paging;

        static class list_node extends AutomatedToString {

            schema.node                 node;
            AnimeDetails.my_list_status list_status;

        }

    }

    static class ForumBoards extends AutomatedToString {

        category_node[] categories;

        static class category_node extends AutomatedToString {

            String  title;
            board[] boards;

            @SuppressWarnings("SpellCheckingInspection")
            static class board extends AutomatedToString {

                long    id;
                String  title,
                        description;
                subboard[] subboards;

                static class subboard extends AutomatedToString{ }

            }

        }

    }

    static class ForumTopicDetail extends AutomatedToString {

        data_node       data;
        schema.paging   paging;

        static class data_node extends AutomatedToString {

            String  title;
            post[]  posts;
            poll    poll;

            static class post extends AutomatedToString {

                String  id;
                int     number;
                String  created_at;
                author  created_by;
                String  body,
                        signature;

                static class author extends AutomatedToString {

                    String  id,
                            name,
                            forum_avator; // fixme: API typo

                }

            }

            static class poll extends AutomatedToString {

                String      id,
                            question,
                            closed;
                option[]    options;

                static class option extends AutomatedToString {

                    String  id,
                            text,
                            votes;

                }

            }

        }

    }

    static class ForumTopic extends AutomatedToString {

        data_node[]     data;
        schema.paging   paging;

        static class data_node extends AutomatedToString {

            long    id;
            String  title,
                    created_at;
            creator created_by;
            int     num_of_posts;
            String  last_post_created_at;
            creator last_post_created_by;
            int     is_locked;

            static class creator extends AutomatedToString{

                long    id;
                String  name;

            }

        }

    }

    static class MangaList extends AutomatedToString {

        schema.node[] data;
        schema.paging paging;

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

        static class my_list_status extends AutomatedToString {

            String  status;
            boolean is_rereading;
            int     num_volumes_read,
                    num_chapters_read,
                    score;
            String updated_at;

        }

        static class author extends AutomatedToString {

            node    node;
            String  role;

            static class node extends AutomatedToString {

                long    id;
                String  first_name,
                        last_name;

            }

        }

        static class serialization extends AutomatedToString {

            node    node;
            String  role;

            static class node extends AutomatedToString {

                long    id;
                String  name;

            }

        }

        static class statistics extends AutomatedToString {

            status status;
            int     num_list_users;

            static class status extends AutomatedToString {

                String  reading,
                        completed,
                        on_hold,
                        dropped,
                        plan_to_read;

            }

        }

    }

    static class MangaRanking extends schema.ranking { }

    static class UpdateMangaList extends AutomatedToString {

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

    }

    static class UserMangaList extends AutomatedToString {

        list_node[]     data;
        schema.paging   paging;

        static class list_node extends AutomatedToString {

            schema.node                 node;
            MangaDetails.my_list_status list_status;

        }

    }

    static class UserInformation extends AutomatedToString {

        long                id;
        String              name,
                            location,
                            joined_at;
        anime_statistics    anime_statistics;
        manga_statistics    manga_statistics;

        @SuppressWarnings("SpellCheckingInspection")
        static class anime_statistics extends AutomatedToString {

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

        }

        static class manga_statistics extends AutomatedToString {

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
*/

}
