/**
 * <h1>Mal4J Documentation</h1>
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *     <li>{@link dev.katsute.mal4j.MyAnimeList}</li>
 *     <li>{@link dev.katsute.mal4j.MyAnimeListAuthenticator}</li>
 *     <li>{@link dev.katsute.mal4j.MyAnimeListAuthenticator.LocalServerBuilder}</li>
 *     <li>{@link dev.katsute.mal4j.Fields}</li>
 * </ul>
 *
 * <h2>Frequently Used Objects:</h2>
 * <ul>
 *     <li>
 *         {@link dev.katsute.mal4j.anime.Anime}
 *         <ul>
 *             <li>{@link dev.katsute.mal4j.anime.AnimeListStatus}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         {@link dev.katsute.mal4j.manga.Manga}
 *         <ul>
 *             <li>{@link dev.katsute.mal4j.manga.MangaListStatus}</li>
 *         </ul>
 *     </li>
 *     <li>{@link dev.katsute.mal4j.character.Character}</li>
 *     <li>{@link dev.katsute.mal4j.people.Person}</li>
 *     <li>{@link dev.katsute.mal4j.user.User}</li>
 *     <li>
 *         {@link dev.katsute.mal4j.forum.ForumTopicDetail}
 *         <ul>
 *             <li>{@link dev.katsute.mal4j.forum.Post}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         {@link dev.katsute.mal4j.forum.ForumCategory}
 *         <ul>
 *             <li>{@link dev.katsute.mal4j.forum.ForumBoard}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         {@link dev.katsute.mal4j.forum.ForumTopic}
 *     </li>
 * </ul>
 */
module Mal4J {

    requires jdk.httpserver;
    requires java.desktop;
    requires java.logging;

    exports dev.katsute.mal4j.exception;
    exports dev.katsute.mal4j.anime;
    exports dev.katsute.mal4j.anime.property;
    exports dev.katsute.mal4j.anime.property.time;
    exports dev.katsute.mal4j.character;
    exports dev.katsute.mal4j.forum;
    exports dev.katsute.mal4j.forum.property;
    exports dev.katsute.mal4j.manga;
    exports dev.katsute.mal4j.manga.property;
    exports dev.katsute.mal4j.people;
    exports dev.katsute.mal4j.property;
    exports dev.katsute.mal4j.query;
    exports dev.katsute.mal4j.user;
    exports dev.katsute.mal4j.user.property;
    exports dev.katsute.mal4j;

}