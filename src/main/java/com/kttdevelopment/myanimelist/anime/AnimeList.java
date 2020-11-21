package com.kttdevelopment.myanimelist.anime;

public abstract class AnimeList {

    // fixme: this class must be synchronized

    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put
    // todo
    @SuppressWarnings("SpellCheckingInspection")
    public abstract void updateAnime(
        final int anime_id,
        final AnimeStatus status,
        final boolean rewatching,
        final int score,
        final int watched,
        final int priority,
        final int rewatched,
        final int rewatch_value,
        final String[] tags,
        final String comments
    );

    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_delete
    public abstract void deleteAnime(final int anime_id);

}
