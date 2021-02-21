/**
 * <h1>Mal4J Documentation</h1>
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *     <li>{@link com.kttdevelopment.mal4j.MyAnimeList}</li>
 *     <li>{@link com.kttdevelopment.mal4j.MyAnimeListAuthenticator}</li>
 *     <li>{@link com.kttdevelopment.mal4j.Fields}</li>
 * </ul>
 *
 * <h2>Frequently Used Objects:</h2>
 * <ul>
 *     <li>
 *         {@link com.kttdevelopment.mal4j.anime.Anime}
 *         <ul>
 *             <li>{@link com.kttdevelopment.mal4j.anime.AnimeListStatus}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         {@link com.kttdevelopment.mal4j.manga.Manga}
 *         <ul>
 *             <li>{@link com.kttdevelopment.mal4j.manga.MangaListStatus}</li>
 *         </ul>
 *     </li>
 *     <li>{@link com.kttdevelopment.mal4j.user.User}</li>
 *     <li>
 *         {@link com.kttdevelopment.mal4j.forum.ForumTopicDetail}
 *         <ul>
 *             <li>{@link com.kttdevelopment.mal4j.forum.Post}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         {@link com.kttdevelopment.mal4j.forum.ForumCategory}
 *         <ul>
 *             <li>{@link com.kttdevelopment.mal4j.forum.ForumBoard}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         {@link com.kttdevelopment.mal4j.forum.ForumTopic}
 *     </li>
 * </ul>
 */
module Mal4J {

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
    exports com.kttdevelopment.mal4j.query;
    exports com.kttdevelopment.mal4j.user;
    exports com.kttdevelopment.mal4j;

}