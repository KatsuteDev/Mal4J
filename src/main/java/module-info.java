module myanimelist {

    requires jdk.httpserver;
    requires java.desktop;
    requires org.json;
    requires retrofit2;

    exports com.kttdevelopment.myanimelist;
    exports com.kttdevelopment.myanimelist.anime;
    exports com.kttdevelopment.myanimelist.forum;
    exports com.kttdevelopment.myanimelist.manga;
    exports com.kttdevelopment.myanimelist.user;

}