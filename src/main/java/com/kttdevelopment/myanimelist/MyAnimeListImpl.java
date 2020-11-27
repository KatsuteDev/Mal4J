package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.Manga;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.query.*;
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
    public AnimeSearchQuery getAnime(){
        return null;
    }

    @Override
    public Anime getAnime(final long id){
        return null;
    }

    @Override
    public Anime getAnime(final long id, final String[] fields){
        return null;
    }

    @Override
    public AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType){
        return null;
    }

    @Override
    public AnimeSeasonQuery getAnimeSeason(final int year, final Season season){
        return null;
    }

    @Override
    public AnimeListUpdate updateAnimeListing(final long id){
        return null;
    }

    @Override
    public void deleteAnimeListing(final long id){

    }

    @Override
    public UserAnimeListQuery getUserAnimeListing(final String username){
        return null;
    }

    @Override
    public List<ForumCategory> getForumBoards(){
        return null;
    }

    @Override
    public ForumCategory getForumBoard(final long id){
        return null;
    }

    @Override
    public ForumCategory getForumBoard(final long id, final int limit){
        return null;
    }

    @Override
    public ForumCategory getForumBoard(final long id, final int limit, final int offset){
        return null;
    }

    @Override
    public List<ForumTopic> getForumTopics(){
        return null;
    }

    @Override
    public MangaSearchQuery getManga(){
        return null;
    }

    @Override
    public Manga getManga(final long id){
        return null;
    }

    @Override
    public Manga getManga(final long id, final String[] fields){
        return null;
    }

    @Override
    public MangaRankingQuery getMangaRanking(final MangaRankingType rankingType){
        return null;
    }

    @Override
    public MangaListUpdate updateMangaListing(final long id){
        return null;
    }

    @Override
    public void deleteMangaListing(final long id){

    }

    @Override
    public UserMangaListQuery getUserMangaListing(final String username){
        return null;
    }

    @Override
    public User getMyself(){
        return null;
    }

    @Override
    public User getMyself(final String[] fields){
        return null;
    }

    @Override
    public User getUser(final String username){
        return null;
    }

    @Override
    public User getUser(final String username, final String[] fields){
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
