package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.anime.AnimeStatus;

public abstract class MangaList {

    // fixme: this class must be synchronized

    // https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put
    // todo
    @SuppressWarnings("SpellCheckingInspection")
    public abstract void updateAnime(
        final int manga_id,
        final MangaStatus status,
        final boolean rereading,
        final int score,
        final int volumes_read,
        final int chapters_read,
        final int priority,
        final int reread,
        final int reread_value,
        final String[] tags,
        final String comments
    );

    // https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_delete
    public abstract void deleteManga(final int anime_id);

}
