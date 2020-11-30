package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaPreviewRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaRetrievable;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_mangalist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_mangalist_get</a> <br>
 * Represents a user's Manga list status.
 *
 * @see MyAnimeList#getUserMangaListing()
 * @see MyAnimeList#getUserMangaListing(String)
 * @see MangaListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserMangaListStatus extends MangaListStatus implements MangaRetrievable,MangaPreviewRetrievable {

}
