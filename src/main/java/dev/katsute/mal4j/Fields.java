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

/**
 * The fields class holds all possible fields for a request. Usable in any methods that ask for fields.
 *
 * @since 1.1.0
 * @version 3.4.0
 * @author Katsute
 */
public abstract class Fields {

    private Fields(){ }

    /**
     * Indicates that only default fields should be returned.
     *
     * @since 1.1.0
     */
    public static final String[] NO_FIELDS = new String[0];

    /**
     * Indicates that all fields except the ones supplied should be returned. Add this to the fields parameter.
     *
     * @since 2.2.0
     */
    public static final String INVERTED = "%INVERTED_MODIFIER%"; // this field should never equal an existing MAL field

    // shared

    /**
     * Fields shared by {@link Anime} and {@link Manga}.
     *
     * @see Anime
     * @see Manga
     * @since 1.1.0
     * @version 2.2.1
     * @author Katsute
     */
    static class Common {

        private Common(){ }

        public static final String id = "id";

        public static final String title = "title";

        public static final String main_picture = "main_picture";

        public static final String alternative_titles = "alternative_titles";

        public static final String start_date = "start_date";

        public static final String end_date = "end_date";

        public static final String synopsis = "synopsis";

        public static final String mean = "mean";

        public static final String rank = "rank";

        public static final String popularity = "popularity";

        public static final String list_users = "num_list_users";

        public static final String scoring_users = "num_scoring_users";

        public static final String nsfw = "nsfw";

        public static final String created_at = "created_at";

        public static final String updated_at = "updated_at";

        public static final String media_type = "media_type";

        public static final String status = "status";

        public static final String genres = "genres";

        public static final String pictures = "pictures";

        public static final String background = "background";

        public static final String related_anime = "related_anime";

        public static final String related_manga = "related_manga";

        public static final String recommendations = "recommendations";

        /**
         * Fields shared by {@link Anime.ListStatus} and {@link Manga.ListStatus}.
         *
         * @see Anime.ListStatus
         * @see Anime.MyListStatus
         * @see Manga.ListStatus
         * @see Manga.MyListStatus
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        static class ListStatus {

            private ListStatus(){ }

            public static final String start_date = "start_date";

            public static final String finish_date = "finish_date";

            public static final String priority = "priority";

            public static final String tags = "tags";

            public static final String comments = "comments";

        }

    }

    // anime

    /**
     * All possible fields for an Anime.
     *
     * @see #anime
     * @since 1.1.0
     * @version 2.10.0
     * @author Katsute
     */
    public static class Anime extends Common {

        private Anime(){ }

        public static final String episodes = "num_episodes";

        public static final String start_season = "start_season";

        public static final String broadcast = "broadcast";

        public static final String source = "source";

        public static final String average_episode_duration = "average_episode_duration";

        public static final String rating = "rating";

        public static final String studios = "studios";

        public static final String statistics = "statistics";

        public static final String opening_themes = "opening_themes";

        public static final String ending_themes = "ending_themes";

        public static final String videos = "videos";

        //

        /**
         * All possible fields for an Anime list status.
         *
         * @see #list_status
         * @see #list_status(String...)
         * @see MyListStatus
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        @SuppressWarnings("SpellCheckingInspection")
        public static class ListStatus extends Common.ListStatus {

            private ListStatus(){ }

            public static final String times_rewatched = "num_times_rewatched";

            public static final String rewatch_value = "rewatch_value";

        }

        /**
         * All possible fields for an Anime list status.
         *
         * @see #my_list_status
         * @see #my_list_status(String...)
         * @see ListStatus
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        public static class MyListStatus extends ListStatus {

            private MyListStatus(){ }

        }

        /**
         * Returns all Anime list_status fields as an object. Ex: `list_status{start_date, ...}`
         *
         * @see #list_status(String...)
         * @see ListStatus
         * @since 1.1.0
         */
        public static final String list_status = list_status(
            Common.ListStatus.start_date,
            Common.ListStatus.finish_date,
            Common.ListStatus.priority,
            Common.ListStatus.tags,
            Common.ListStatus.comments,
            ListStatus.times_rewatched,
            ListStatus.rewatch_value
        );

        /**
         * Creates a my_list_status object from a set of fields. Ex: `list_status{start_date}`
         *
         * @param fields fields
         * @return list_status
         *
         * @see #list_status
         * @see ListStatus
         * @since 1.1.0
         */
        public static String list_status(final String... fields){
            return "list_status{" + String.join(",", fields == null ? new String[0] : fields) + '}';
        }

        /**
         * Returns all Anime my_list_status fields as an object. Ex: `my_list_status{start_date}`
         *
         * @see #my_list_status(String...)
         * @see MyListStatus
         * @since 1.1.0
         */
        public static final String my_list_status = "my_" + list_status;

        /**
         * Creates a my_list_status object from a set of fields. Ex: `my_list_status{start_date}`
         *
         * @param fields fields
         * @return my_list_status
         *
         * @see #my_list_status
         * @see MyListStatus
         * @since 1.1.0
         */
        public static String my_list_status(final String... fields){
            return "my_" + list_status(fields);
        }

    }

    /**
     * Returns all Anime fields as a comma separated string.
     *
     * @see Anime
     * @since 1.1.0
     */
    public static final String anime = String.join(",",
        Anime.id,
        Anime.title,
        Anime.main_picture,
        Anime.alternative_titles,
        Anime.start_date,
        Anime.end_date,
        Anime.synopsis,
        Anime.mean,
        Anime.rank,
        Anime.popularity,
        Anime.list_users,
        Anime.scoring_users,
        Anime.nsfw,
        Anime.created_at,
        Anime.updated_at,
        Anime.media_type,
        Anime.status,
        Anime.genres,
        Anime.pictures,
        Anime.background,
        Anime.related_anime,
        Anime.related_manga,
        Anime.recommendations,
        Anime.episodes,
        Anime.start_season,
        Anime.broadcast,
        Anime.source,
        Anime.average_episode_duration,
        Anime.rating,
        Anime.studios,
        Anime.statistics,
        Anime.opening_themes,
        Anime.ending_themes,
        Anime.videos,
        Anime.list_status,
        Anime.my_list_status
    );

    // manga

    /**
     * All possible fields for a Manga.
     *
     * @see #manga
     * @since 1.1.0
     * @version 2.2.1
     * @author Katsute
     */
    public static class Manga extends Common {

        private Manga(){ }

        public static final String volumes = "num_volumes";

        public static final String chapters = "num_chapters";

        //

        /**
         * All possible fields for a Manga list status.
         *
         * @see #list_status
         * @see #list_status(String...)
         * @see Manga.MyListStatus
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        public static class ListStatus extends Common.ListStatus {

            private ListStatus(){ }

            public static final String times_reread = "num_times_reread";

            public static final String reread_value = "reread_value";

        }

        /**
         * All possible fields for a Manga list status.
         *
         * @see #my_list_status
         * @see #my_list_status(String...)
         * @see Manga.ListStatus
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        public static class MyListStatus extends Manga.ListStatus {

            private MyListStatus(){ }

        }

        /**
         * Returns all Manga list_status fields as an object. Ex: `list_status{start_date, ...}`
         *
         * @see #list_status(String...)
         * @see Manga.ListStatus
         * @since 1.1.0
         */
        public static final String list_status = list_status(
            Common.ListStatus.start_date,
            Common.ListStatus.finish_date,
            Common.ListStatus.priority,
            Common.ListStatus.tags,
            Common.ListStatus.comments,
            ListStatus.times_reread,
            ListStatus.reread_value
        );

        /**
         * Creates a my_list_status object from a set of fields. Ex: `list_status{start_date}`
         *
         * @param fields fields
         * @return list_status
         *
         * @see #list_status
         * @see Manga.ListStatus
         * @since 1.1.0
         */
        public static String list_status(final String... fields){
            return "list_status{" + String.join(",", fields == null ? new String[0] : fields) + '}';
        }

        /**
         * Returns all Manga my_list_status fields as an object. Ex: `my_list_status{start_date}`
         *
         * @see #my_list_status(String...)
         * @see Manga.MyListStatus
         * @since 1.1.0
         */
        public static final String my_list_status = "my_" + list_status;

        /**
         * Creates a my_list_status object from a set of fields. Ex: `my_list_status{start_date}`
         *
         * @param fields fields
         * @return my_list_status
         *
         * @see #my_list_status
         * @see Manga.MyListStatus
         * @since 1.1.0
         */
        public static String my_list_status(final String... fields){
            return "my_" + list_status(fields);
        }

        //

        /**
         * All possible fields for an Author.
         *
         * @see #authors
         * @see #authors(String...)
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        public static class Authors {

            private Authors(){ }

            public static final String first_name = "first_name";

            public static final String last_name = "last_name";

        }

        /**
         * Returns all Author fields as an object. Ex: `authors{first_name,last_name}`
         *
         * @see #authors
         * @see Manga.Authors
         * @since 1.1.0
         */
        public static final String authors = authors(
            Authors.first_name,
            Authors.last_name
        );

        /**
         * Creates an authors object from a set of fields. Ex: `authors{first_name}`
         *
         * @param fields fields
         * @return authors
         *
         * @see #authors
         * @see Manga.Authors
         * @since 1.1.0
         */
        public static String authors(final String... fields){
            return "authors{" + String.join(",", fields == null ? new String[0] : fields) + '}';
        }

        //

        /**
         * All possible fields for serialization.
         *
         * @see #serialization
         * @see #serialization(String...)
         * @since 1.1.0
         * @version 2.2.1
         * @author Katsute
         */
        public static class Serialization {

            private Serialization(){ }

            public static final String name = "name";

            public static final String role = "role";

        }

        /**
         * Returns all serialization fields as an object. Ex: `serialization{name,role}`
         *
         * @see #serialization(String...)
         * @see Manga.Serialization
         * @since 1.1.0
         */
        public static final String serialization = serialization(
            Serialization.name,
            Serialization.role
        );

        /**
         * Creates a serialization object from a set of fields. Ex: `serialization{first_name}`
         *
         * @param fields fields
         * @return authors
         *
         * @see #serialization(String...)
         * @see Manga.Serialization
         * @since 1.1.0
         */
        public static String serialization(final String... fields){
            return "serialization{" + String.join(",", fields == null ? new String[0] : fields) + '}';
        }

    }

    /**
     * Returns all Manga fields as a comma separated string.
     *
     * @see Manga
     * @since 1.1.0
     */
    public static final String manga = String.join(",",
        Manga.id,
        Manga.title,
        Manga.main_picture,
        Manga.alternative_titles,
        Manga.start_date,
        Manga.end_date,
        Manga.synopsis,
        Manga.mean,
        Manga.rank,
        Manga.popularity,
        Manga.list_users,
        Manga.scoring_users,
        Manga.nsfw,
        Manga.created_at,
        Manga.updated_at,
        Manga.media_type,
        Manga.status,
        Manga.genres,
        Manga.pictures,
        Manga.background,
        Manga.related_anime,
        Manga.related_manga,
        Manga.recommendations,
        Manga.volumes,
        Manga.chapters,
        Manga.list_status,
        Manga.my_list_status,
        Manga.authors,
        Manga.serialization
    );

    // character

    /**
     * All possible fields for a character.
     *
     * @see #character
     * @since 3.1.0
     * @version 3.4.0
     * @author Katsute
     */
    public static class Character {

        private Character(){}

        public static final String id = "id";

        public static final String first_name = "first_name";

        public static final String last_name = "last_name";

        public static final String alternative_name = "alternative_name";

        public static final String main_picture = "main_picture";

        public static final String num_favorites = "num_favorites";

        public static final String pictures = "pictures";

        public static final String biography = "biography";

        @SuppressWarnings("SpellCheckingInspection")
        public static final String animeography = "animeography";

    }

    /**
     * Returns all character fields as a comma separated string.
     *
     * @see Character
     * @since 3.1.0
     */
    public static final String character = String.join(",",
        Character.id,
        Character.first_name,
        Character.last_name,
        Character.alternative_name,
        Character.main_picture,
        Character.num_favorites,
        Character.pictures,
        Character.biography,
        Character.animeography
    );

    // person

    /**
     * All possible fields for a person.
     *
     * @see #people
     * @since 3.2.0
     * @version 3.4.0
     */
    public static class People {

        private People(){}

        public static final String first_name = "first_name";

        public static final String last_name = "last_name";

        public static final String birthday = "birthday";

        public static final String alternative_names = "alternative_names";

        public static final String main_picture = "main_picture";

        public static final String num_favorites = "num_favorites";

        public static final String more = "more";

    }

    /**
     * Returns all people fields as a comma separated string.
     *
     * @see People
     * @since 3.2.0
     */
    public static final String people = String.join(",",
        People.first_name,
        People.last_name,
        People.birthday,
        People.alternative_names,
        People.main_picture,
        People.num_favorites,
        People.more
    );

    // user

    /**
     * All possible fields for a User.
     *
     * @see #user
     * @since 1.1.0
     * @version 2.2.1
     * @author Katsute
     */
    public static class User {

        private User(){ }

        public static final String birthday = "birthday";

        public static final String timezone = "time_zone";

        public static final String anime_statistics = "anime_statistics";

        public static final String supporter = "is_supporter";

    }

    /**
     * Returns all user fields as a comma separated string.
     *
     * @see User
     * @since 1.1.0
     */
    public static final String user = String.join(",",
        User.birthday,
        User.timezone,
        User.anime_statistics,
        User.supporter
    );

}