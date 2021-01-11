module MyAnimeListAPI {

    requires java.net.http;
    requires jdk.httpserver;
    requires java.desktop;

    exports com.kttdevelopment.mal4j.anime;
    exports com.kttdevelopment.mal4j.anime.property;
    exports com.kttdevelopment.mal4j.anime.property.time;
    exports com.kttdevelopment.mal4j.forum;
    exports com.kttdevelopment.mal4j.forum.property;
    exports com.kttdevelopment.mal4j.manga;
    exports com.kttdevelopment.mal4j.manga.property;
    exports com.kttdevelopment.mal4j.property;
    exports com.kttdevelopment.mal4j.query.property;
    exports com.kttdevelopment.mal4j.query;
    exports com.kttdevelopment.mal4j.user;
    exports com.kttdevelopment.mal4j;

}