package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.MangaSort;
import com.kttdevelopment.myanimelist.user.User;
import retrofit2.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public final class MyAnimeListImpl extends MyAnimeList{

    private transient final String auth;

    private final MyAnimeListService service = MyAnimeListService.create();

    public MyAnimeListImpl(final String auth){
        this.auth = auth;
    }

    //

    @Override
    public final List<AnimePreview> getAnime(){
        return getAnime(null, -1, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnime(final String search){
        return getAnime(search, -1, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnime(final String search, final int limit){
        return getAnime(search, limit, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnime(final String search, final int limit, final int offset){
        return getAnime(search, limit, offset, null);
    }

    @Override
    public final List<AnimePreview> getAnime(final String search, final String[] fields){
        return getAnime(search, -1, -1, fields);
    }

    @Override
    public final List<AnimePreview> getAnime(final String search, final int limit, final int offset, final String[] fields){
        return null; // todo
    }

    @Override
    public final Anime getAnime(final int id){
        return getAnime(id, null);
    }

    @Override
    public final Anime getAnime(final int id, final String[] fields){
        try{
            final MyAnimeListSchema._Anime_ID anime = service
                .getAnime(
                    auth,
                    id,
                    parseFields(fields)
                )
                .execute()
                .body();

            return null; // todo
        }catch(final IOException e){
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType){
        return null;
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit){
        return null;
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final AnimeListing updateAnimeListing(){
        return null;
    }

    @Override
    public final void deleteAnimeListing(final int id){

    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final int limit){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final int limit){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final AnimeStatus status){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final AnimeStatus status, final int limit){
        return null;
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final AnimeStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<ForumCategory> getForumBoards(){
        return null;
    }

    @Override
    public final ForumCategory getForumBoard(final int id){
        return null;
    }

    @Override
    public final ForumCategory getForumBoard(final int id, final int limit){
        return null;
    }

    @Override
    public final ForumCategory getForumBoard(final int id, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<ForumTopic> getForumTopics(){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(final String search){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final int limit){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final String[] fields){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final MangaPreview getManga(final int id){
        return null;
    }

    @Override
    public final MangaPreview getManga(final int id, final String[] fields){
        return null;
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType){
        return null;
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final String[] fields){
        return null;
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit){
        return null;
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final MangaListing updateMangaListing(){
        return null;
    }

    @Override
    public final void deleteMangaListing(final int id){

    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final int limit){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final int limit){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final MangaStatus status){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final MangaStatus status, final int limit){
        return null;
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final MangaStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final User getMyself(){
        return null;
    }

    @Override
    public final User getMyself(final String[] fields){
        return null;
    }

    @Override
    public final User getUser(final String username){
        return null;
    }

    @Override
    public final User getUser(final String username, final String[] fields){
        return null;
    }

    //

    private String parseFields(final String[] fields){
        if(fields != null && fields.length > 0){
            final StringBuilder SB = new StringBuilder();
            for(final String field : fields)
                if(!field.isBlank())
                    SB
                        .append(field)
                        .append(',');

            final String str = SB.toString();
            if(!str.isBlank())
                return str.substring(0, str.length() -1);
        }
        return null;
    }

}
