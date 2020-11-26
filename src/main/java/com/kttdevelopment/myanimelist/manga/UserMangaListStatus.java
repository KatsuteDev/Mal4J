package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.MangaPreviewRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.property.UserListStatus;

public abstract class UserMangaListStatus extends MangaListStatus implements UserListStatus<MangaStatus>, MangaRetrievable, MangaPreviewRetrievable {

}
