package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.anime.property.AnimePreviewRetrievable;
import com.kttdevelopment.myanimelist.anime.property.AnimeRetrievable;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get</a> <br>
 * Represents a user's Anime list status.
 *
 * @see MyAnimeList#getUserAnimeListing()
 * @see MyAnimeList#getUserAnimeListing(String)
 * @see AnimeListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserAnimeListStatus extends AnimeListStatus implements AnimeRetrievable, AnimePreviewRetrievable {

}
