module MyAnimeListAPI {

    requires java.net.http;
    requires jdk.httpserver;
    requires java.desktop;

    exports com.kttdevelopment.myanimelist.anime;
    exports com.kttdevelopment.myanimelist.anime.property;
    exports com.kttdevelopment.myanimelist.anime.property.time;
    exports com.kttdevelopment.myanimelist.forum;
    exports com.kttdevelopment.myanimelist.forum.property;
    exports com.kttdevelopment.myanimelist.manga;
    exports com.kttdevelopment.myanimelist.manga.property;
    exports com.kttdevelopment.myanimelist.property;
    exports com.kttdevelopment.myanimelist.query;
    exports com.kttdevelopment.myanimelist.user;
    exports com.kttdevelopment.myanimelist;

}