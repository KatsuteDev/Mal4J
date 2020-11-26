package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.user.User;

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
    public final List<AnimePreview> getAnime(final String search, final int limit, final String[] fields) {
        return getAnime(search, limit, -1, fields);
    }

    @Override
    public final List<AnimePreview> getAnime(final String search, final String[] fields){
        return getAnime(search, -1, -1, fields);
    }

    @Override // todo
    public final List<AnimePreview> getAnime(final String search, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final Anime getAnime(final long id){
        return getAnime(id, null);
    }

    @Override // todo
    public final Anime getAnime(final long id, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType){
        return getAnimeRanking(rankingType, -1, -1, null);
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final String[] fields){
        return getAnimeRanking(rankingType, -1, -1, fields);
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit){
        return getAnimeRanking(rankingType, limit, -1, null);
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final int offset){
        return getAnimeRanking(rankingType, limit, offset, null);
    }

    @Override
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final String[] fields) {
        return getAnimeRanking(rankingType, limit, -1, null);
    }

    @Override // todo
    public final List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season){
        return getAnimeSeason(year, season, null, -1, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final String[] fields){
        return getAnimeSeason(year, season, null, -1, -1, fields);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit){
        return getAnimeSeason(year, season, null, limit, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final String[] fields){
        return getAnimeSeason(year, season, null, limit, -1, fields);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final int offset){
        return getAnimeSeason(year, season, null, limit, offset, null);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final int offset, final String[] fields){
        return getAnimeSeason(year, season, null, limit, offset, fields);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort){
        return getAnimeSeason(year, season, sort, -1, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final String[] fields){
        return getAnimeSeason(year, season, sort, -1, -1, fields);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit){
        return getAnimeSeason(year, season, sort, limit, -1, null);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final String[] fields){
        return getAnimeSeason(year, season, sort, limit, -1, fields);
    }

    @Override
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final int offset){
        return getAnimeSeason(year, season, sort, limit, offset, null);
    }

    @Override // todo
    public final List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override // todo
    public final AnimeListing updateAnimeListing(){
        return null;
    }

    @Override // todo
    public final void deleteAnimeListing(final long id){

    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username){
        return getUserAnimeListing(username, null, null, -1, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final int limit){
        return getUserAnimeListing(username, null, null, limit, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final int limit, final int offset){
        return getUserAnimeListing(username, null, null, limit, offset);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status){
        return getUserAnimeListing(username, null, status, -1, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit){
        return getUserAnimeListing(username, null, status, limit, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit, final int offset){
        return getUserAnimeListing(username, null, status, limit, offset);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort){
        return getUserAnimeListing(username, sort, null, -1, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final int limit){
        return getUserAnimeListing(username, sort, null, limit, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final int limit, final int offset){
        return getUserAnimeListing(username, sort, null, limit, offset);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final AnimeStatus status){
        return getUserAnimeListing(username, sort, status, -1, 0);
    }

    @Override
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final AnimeStatus status, final int limit){
        return getUserAnimeListing(username, sort, status, limit, 0);
    }

    @Override // todo
    public final List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeSort sort, final AnimeStatus status, final int limit, final int offset){
        return null;
    }

    @Override // todo
    public final List<ForumCategory> getForumBoards(){
        return null;
    }

    @Override
    public final ForumCategory getForumBoard(final long id){
        return getForumBoard(id, -1, 0);
    }

    @Override
    public final ForumCategory getForumBoard(final long id, final int limit){
        return getForumBoard(id, limit, 0);
    }

    @Override // todo
    public final ForumCategory getForumBoard(final long id, final int limit, final int offset){
        return null;
    }

    @Override // todo
    public final List<ForumTopic> getForumTopics(){
        return null;
    }

    @Override
    public final List<MangaPreview> getManga(){
        return getManga(null, -1, -1, null);
    }

    @Override
    public final List<MangaPreview> getManga(final String search){
        return getManga(search, -1, -1, null);
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final int limit){
        return getManga(search, limit, -1, null);
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final int limit, final int offset){
        return getManga(search, limit, offset, null);
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final int limit, final String[] fields) {
        return getManga(search, limit, -1, fields);
    }

    @Override
    public final List<MangaPreview> getManga(final String search, final String[] fields){
        return getManga(search, -1, -1, fields);
    }

    @Override // todo
    public final List<MangaPreview> getManga(final String search, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override
    public final Manga getManga(final long id){
        return getManga(id, null);
    }

    @Override // todo
    public final Manga getManga(final long id, final String[] fields){
        return null;
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType){
        return getMangaRanking(rankingType, -1, -1, null);
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final String[] fields){
        return getMangaRanking(rankingType, -1, -1, fields);
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit){
        return getMangaRanking(rankingType, limit, -1, null);
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset){
        return getMangaRanking(rankingType, limit, offset, null);
    }

    @Override
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final String[] fields) {
        return getMangaRanking(rankingType, limit, -1, fields);
    }

    @Override // todo
    public final List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset, final String[] fields){
        return null;
    }

    @Override // todo
    public final MangaListing updateMangaListing(){
        return null;
    }

    @Override // todo
    public final void deleteMangaListing(final long id){

    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username){
        return getUserMangaListing(username, null, null, -1, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final int limit){
        return getUserMangaListing(username, null, null, limit, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final int limit, final int offset){
        return getUserMangaListing(username, null, null, limit, offset);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status){
        return getUserMangaListing(username, null, status, -1, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit){
        return getUserMangaListing(username, null, status, limit, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit, final int offset){
        return getUserMangaListing(username, null, status, limit, offset);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort){
        return getUserMangaListing(username, sort, null, -1, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final int limit){
        return getUserMangaListing(username, sort, null, limit, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final int limit, final int offset){
        return getUserMangaListing(username, sort, null, limit, offset);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final MangaStatus status){
        return getUserMangaListing(username, sort, status, -1, -1);
    }

    @Override
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final MangaStatus status, final int limit){
        return getUserMangaListing(username, sort, status, limit, -1);
    }

    @Override // todo
    public final List<UserMangaListing> getUserMangaListing(final String username, final MangaSort sort, final MangaStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final User getMyself(){
        return getUser("@me", null);
    }

    @Override
    public final User getMyself(final String[] fields){
        return getUser("@me", fields);
    }

    @Override
    public final User getUser(final String username){
        return getUser(username, null);
    }

    @Override // todo
    public final User getUser(final String username, final String[] fields){
        return null;
    }

    //

    private static String parseFields(final String[] fields){
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
