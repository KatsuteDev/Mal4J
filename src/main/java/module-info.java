module myanimelist {

    requires jdk.httpserver;
    requires java.desktop;
    requires retrofit2;
    requires retrofit2.converter.gson;

    exports com.kttdevelopment.myanimelist;
    exports com.kttdevelopment.myanimelist.anime;
    exports com.kttdevelopment.myanimelist.anime.property;
    exports com.kttdevelopment.myanimelist.forum;
    exports com.kttdevelopment.myanimelist.manga;
    exports com.kttdevelopment.myanimelist.manga.property;
    exports com.kttdevelopment.myanimelist.user;

}