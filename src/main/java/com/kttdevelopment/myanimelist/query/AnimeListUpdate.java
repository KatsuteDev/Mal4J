package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put</a> <br>
 * Represents an Anime list update.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#updateAnimeListing(long)
 * @see ListUpdate
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListUpdate extends ListUpdate<AnimeListUpdate,AnimeListStatus,AnimeStatus> {

    protected Boolean rewatching;
    protected Integer watchedEpisodes, timesRewatched, rewatchValue;

    /**
     * Creates an Anime list update. Applications do not use this constructor.
     *
     * @param service MyAnimeList
     * @param id Anime id
     *
     * @see com.kttdevelopment.myanimelist.MyAnimeList#updateAnimeListing(long)
     * @since 1.0.0
     */
    public AnimeListUpdate(final MyAnimeListService service, final long id){
        super(service, id);
    }

    /**
     * Sets the rewatching status.
     *
     * @param rewatching rewatching
     * @return list update
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate rewatching(final boolean rewatching){
        this.rewatching = rewatching;
        return this;
    }

    /**
     * Sets the episodes watched.
     *
     * @param watchedEpisodes watched episodes
     * @return list update
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate episodesWatched(final int watchedEpisodes){
        this.watchedEpisodes = watchedEpisodes;
        return this;
    }

    /**
     * Sets the times rewatched.
     *
     * @param timesRewatched times rewatched
     * @return list update
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate timesRewatched(final int timesRewatched){
        this.timesRewatched = timesRewatched;
        return this;
    }

    /**
     * Sets the rewatch value.
     *
     * @param rewatchValue rewatch value
     * @return list update
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate rewatchValue(final int rewatchValue){
        this.rewatchValue = rewatchValue;
        return this;
    }


}
