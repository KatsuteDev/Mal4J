package com.kttdevelopment.myanimelist;

final class MyAnimeListSchema {

    // applicable to anime, anime suggested, manga
    static class NodePaging {

        node[] data;
        paging paging;

    }

    static class node {

        long id;
        String title;
        main_picture main_picture;

    }

    static class main_picture {

        String medium, large;

    }

    static class paging {

        String next;

    }

    //

    static class _Anime_ID {

        long id;
        String title;
        main_picture main_picture;
        alternative_titles alternative_titles;
        String start_date, end_date, synopsis;
        double mean;
        int rank, popularity, num_list_users, num_scoring_users;
        String nsfw, created_at, updated_at, media_type, status;
        IDN[] genres;
        anime_list_status my_list_status;
        int num_episodes;
        start_season start_season;
        broadcast broadcast;
        String source;
        int average_episode_duration;
        String rating;
        main_picture[] pictures;
        String background;
        related[] related_anime, related_manga;
        recommendation[] recommendations;
        IDN[] studios;
        MyAnimeListSchema.statistics statistics;

    }

    static class alternative_titles {

        String[] synonyms;
        String en, ja;

    }

    // applicable to genres/studios
    static class IDN {

        long id;
        String name;

    }

    static class anime_list_status {

        String status;
        int score, num_episodes_watched;
        @SuppressWarnings("SpellCheckingInspection")
        boolean is_rewatching;
        String updated_at;

    }

    static class start_season {

        int year;
        String season;

    }

    static class broadcast {

        String day_of_the_week, start_time;

    }

    // applicable to related_anime & related_manga
    static class related {

        node node;
        String relation_type, relation_type_formatted;

    }

    static class recommendation {

        node node;
        int num_recommendations;

    }

    static class statistics {

        status_anime status;
        int num_list_users;

    }
    
    static class status_anime {

        String watching, completed, on_hold, dropped, plan_to_watch;

    }

    //

    static class _Anime_Ranking_Search {

        NodeRanking[] data;
        paging paging;

    }

    // applicable to anime ranking and manga ranking
    static class NodeRanking{

        node node;
        ranking ranking;

    }

    static class ranking {

        int rank;

    }

    //

    static class _Anime_Season_Search {

        node[] data;
        paging paging;
        season season;

    }

    static class season {

        int year;
        String season;

    }


    static class AnimeListStatusExtended {

        String status;
        int score, num_episodes_watched;
        @SuppressWarnings("SpellCheckingInspection")
        boolean is_rewatching;
        String updated_at;
        @SuppressWarnings("SpellCheckingInspection")
        int priority, num_times_rewatched, rewatch_value;
        String[] tags;
        String comments;

    }

    //

    static class _Anime_MyListStatus {

        String status;
        int score, num_watched_episodes;
        @SuppressWarnings("SpellCheckingInspection")
        boolean is_rewatching;
        String updated_at;
        @SuppressWarnings("SpellCheckingInspection")
        int priority, num_times_rewatched, rewatch_value;
        String[] tags;
        String comments;

    }

    //

    static class _Anime_AnimeList {

        AnimeNodeListing[] data;
        paging paging;

    }

    static class AnimeNodeListing {

        node node;
        anime_list_status list_status;

    }

    //

    static class _Forum {

        category[] categories;

    }

    static class category {

        String title;
        board[] boards;

    }

    static class board {

        long id;
        String title, description;
        @SuppressWarnings("SpellCheckingInspection")
        subboard[] subboards;

    }

    @SuppressWarnings("SpellCheckingInspection")
    static class subboard{

    }

    //

    static class _Forum_Topic_ID {

        forum_data[] data;
        paging paging;

    }

    static class forum_data{

        String title;
        post[] posts;
        poll poll;

    }

    static class post{

        String id;
        int number;
        String created_at;
        created_by created_by;

    }

    static class created_by {

        String id;
        String name;
        String form_avator; // fixme: API typo!

    }

    static class poll {

        String id, question, closed;
        option[] options;
        paging paging;

    }

    static class option {

        String id, text, votes;

    }

    //

    static class _Forum_Topics {

        topic[] data;
        paging paging;

    }

    static class topic {

        long id;
        String title, created_at;
        IDN created_by;
        int number_of_posts;
        String last_post_created_at;
        IDN last_post_created_by;
        int is_locked;

    }

    //

    static class _Manga_ID {

        long id;
        String title;
        main_picture main_picture;
        alternative_titles alternative_titles;
        String start_data, synopsis;
        double mean;
        int rank, popularity, num_list_users, num_scoring_users;
        String nsfw, created_at, updated_at, media_type, status;
        IDN[] genres;
        manga_list_status my_list_status;
        int num_volumes, num_chapters;
        author[] authors;
        main_picture[] pictures;
        String background;
        related[] related_anime;
        related[] related_manga;
        recommendation[] recommendations;
        serialization[] node;

    }

    static class manga_list_status {

        String status;
        boolean is_rereading;
        int num_volumes_read, num_chapters_read, score;
        String updated_at;

    }

    static class author {

        author_node node;
        String role;

    }

    static class author_node{

        long id;
        String first_name, last_name;

    }

    static class serialization{

        node node;

    }

    //

    static class _Manga_MyListStatus {

        String status;
        boolean is_rereading;
        int num_volumes_read, num_chapters_read, score;
        String updated_at;
        int priority, num_times_reread, reread_value;
        String[] tags;
        String comments;

    }

    //

    static class _Manga_Manga_List {

        MangaNodeListing[] data;
        paging paging;

    }

    static class MangaNodeListing {

        node node;
        manga_list_status list_status;

    }

    //

    static class user
    {

        long id;
        String name, location, joined_at;
        anime_statistics anime_statistics;

    }

    static class anime_statistics
    {
        @SuppressWarnings("SpellCheckingInspection")
        int num_items_watching, num_items_completed, num_items_on_hold, num_items_dropped, num_items_plan_to_watch, num_items, num_days_watched, num_days_watching, num_days_completed, num_days_on_hold, num_days_dropped, num_days, num_episodes, num_times_rewatched, num_mean_score;
    }

}
