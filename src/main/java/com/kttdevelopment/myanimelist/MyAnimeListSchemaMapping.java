package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.AnimeRanking;
import com.kttdevelopment.myanimelist.anime.property.AlternativeTitles;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.MangaRanking;
import com.kttdevelopment.myanimelist.property.Picture;

import java.text.*;
import java.util.Arrays;
import java.util.TimeZone;

import static com.kttdevelopment.myanimelist.MyAnimeListSchema.*;

/**
 * This is the master class for the MyAnimeList API, it converts the JSON mapping
 * to a more convenient interface.
 * <br><br>
 * DEVS: Parse values in instantiation and not in functions (this will improve performance).
 */
abstract class MyAnimeListSchemaMapping {

    // Anime

    static Anime asAnime(final MyAnimeList myAnimeList, final AnimeDetails schema){ }

    static AnimeList asAnimeListing(final MyAnimeList myAnimeList, final AnimeDetails.my_list_status schema){ }

    static AnimePreview asAnimePreview(final MyAnimeList myAnimeList, final schema.node schema){
        return new AnimePreview() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.node obj = schema;

            private final long id = obj.id;
            private final String title = obj.title;
            private final Picture mainPicture = asPicture(mal, obj.main_picture);

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            // additional methods

            @Override
            public final Anime getAnime(){
                return myAnimeList.getAnime(id);
            }

            @Override
            public String toString(){
                return "AnimePreview{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", mainPicture=" + mainPicture +
                       '}';
            }
        };
    }

    static AnimeRanking asAnimeRanking(final MyAnimeList myAnimeList, final schema.ranking.ranking_node schema){
        return new AnimeRanking() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.ranking.ranking_node obj = schema;

            private final AnimePreview anime = asAnimePreview(mal, obj.node);
            private final int ranking = obj.ranking.rank;

            // API methods

            @Override
            public final AnimePreview getAnimePreview(){
                return anime;
            }

            @Override
            public final int getRanking(){
                return ranking;
            }

            // additional methods

            @Override
            public String toString(){
                return "AnimeRanking{" +
                       "anime=" + anime +
                       ", ranking=" + ranking +
                       '}';
            }

        };
    }

    static AnimeRecommendation asAnimeRecommendation(final MyAnimeList myAnimeList, final schema.recommendation schema){
        return new AnimeRecommendation() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.recommendation obj = schema;

            private final AnimePreview anime = asAnimePreview(mal, obj.node);
            private final int recommendations = obj.num_recommendations;

            // API methods

            @Override
            public final AnimePreview getAnimePreview(){
                return anime;
            }

            @Override
            public final int getRecommendations(){
                return recommendations;
            }

            // additional methods

            @Override
            public String toString(){
                return "AnimeRecommendation{" +
                       "anime=" + anime +
                       ", recommendations=" + recommendations +
                       '}';
            }

        };
    }

    static RelatedAnime asRelatedAnime(final MyAnimeList myAnimeList, final schema.related schema){
        return new RelatedAnime() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.related obj = schema;

            private final AnimePreview anime = asAnimePreview(mal, obj.node);
            private final String relationType = obj.relation_type;
            private final String relationTypeFormat = obj.relation_type_formatted;

            // API methods

            @Override
            public final AnimePreview getAnimePreview(){
                return anime;
            }

            @Override
            public final String getRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat(){
                return relationTypeFormat;
            }

            // additional methods

            @Override
            public String toString(){
                return "RelatedAnime{" +
                       "anime=" + anime +
                       ", relationType='" + relationType + '\'' +
                       ", relationTypeFormat='" + relationTypeFormat + '\'' +
                       '}';
            }
        };
    }

    // Manga

    static Manga asManga(final MyAnimeList myAnimeList, final MangaDetails schema){ }

    static MangaList asMangaListing(final MyAnimeList myAnimeList, final MangaDetails.my_list_status schema){ }

    static MangaPreview asMangaPreview(final MyAnimeList myAnimeList, final schema.node schema){
        return new MangaPreview(){

            private final MyAnimeList mal = myAnimeList;
            private final schema.node obj = schema;

            private final long id = obj.id;
            private final String title = obj.title;
            private final Picture mainPicture = asPicture(mal, obj.main_picture);

            // API methods

            @Override
            public final long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            // additional methods

            @Override
            public final Manga getManga(){
                return myAnimeList.getManga(id);
            }

            @Override
            public String toString(){
                return "MangaPreview{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", mainPicture=" + mainPicture +
                       '}';
            }

        };
    }

    static MangaRanking asMangaRanking(final MyAnimeList myAnimeList, final schema.ranking.ranking_node schema){
        return new MangaRanking(){

            private final MyAnimeList mal = myAnimeList;
            private final schema.ranking.ranking_node obj = schema;

            private final MangaPreview manga = asMangaPreview(mal, obj.node);
            private final int ranking = obj.ranking.rank;

            // API methods

            @Override
            public final MangaPreview getMangaPreview(){
                return manga;
            }

            @Override
            public final int getRanking(){
                return ranking;
            }

            // additional methods

            @Override
            public String toString(){
                return "MangaRanking{" +
                       "manga=" + manga +
                       ", ranking=" + ranking +
                       '}';
            }

        };
    }

    static MangaRecommendation asMangaRecommendation(final MyAnimeList myAnimeList, final schema.recommendation schema){
        return new MangaRecommendation() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.recommendation obj = schema;

            private final MangaPreview manga = asMangaPreview(mal, obj.node);
            private final int recommendations = obj.num_recommendations;

            // API methods

            @Override
            public final MangaPreview getMangaPreview(){
                return manga;
            }

            @Override
            public final int getRecommendations(){
                return recommendations;
            }

            // additional methods

            @Override
            public String toString(){
                return "MangaRecommendation{" +
                       "manga=" + manga +
                       ", recommendations=" + recommendations +
                       '}';
            }

        };
    }

    static RelatedManga asRelatedManga(final MyAnimeList myAnimeList, final schema.related schema){
        return new RelatedManga(){

            private final MyAnimeList mal = myAnimeList;
            private final schema.related obj = schema;

            private final MangaPreview manga = asMangaPreview(mal, obj.node);
            private final String relationType = obj.relation_type;
            private final String relationTypeFormat = obj.relation_type_formatted;

            // API methods

            @Override
            public final MangaPreview getMangaPreview(){
                return manga;
            }

            @Override
            public final String getRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat(){
                return relationTypeFormat;
            }

            // additional methods

            @Override
            public String toString(){
                return "RelatedManga{" +
                       "manga=" + manga +
                       ", relationType='" + relationType + '\'' +
                       ", relationTypeFormat='" + relationTypeFormat + '\'' +
                       '}';
            }
        };
    }

    // Components

    static Picture asPicture(final MyAnimeList myAnimeList, final schema.node.picture schema){
        return new Picture() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.node.picture obj = schema;

            private final String mediumURL = obj.medium;
            private final String largeURL = obj.large;

            // API methods

            @Override
            public final String getMediumURL(){
                return mediumURL;
            }

            @Override
            public final String getLargeURL(){
                return largeURL;
            }

            // additional methods

            @Override
            public String toString(){
                return "Picture{" +
                       "mediumURL='" + mediumURL + '\'' +
                       ", largeURL='" + largeURL + '\'' +
                       '}';
            }

        };
    }

    static AlternativeTitles asAlternativeTitles(final MyAnimeList myAnimeList, final schema.alternative_titles schema){
        return new AlternativeTitles() {

            private final MyAnimeList mal = myAnimeList;
            private final schema.alternative_titles obj = schema;

            private final String[] synonyms = obj.synonyms;
            private final String english = obj.en;
            private final String japanese = obj.ja;

            // API methods

            @Override
            public final String[] getSynonyms(){
                return synonyms;
            }

            @Override
            public final String getEnglish(){
                return english;
            }

            @Override
            public final String getJapanese(){
                return japanese;
            }

            // additional methods

            @Override
            public String toString(){
                return "AlternativeTitles{" +
                       "synonyms=" + Arrays.toString(synonyms) +
                       ", english='" + english + '\'' +
                       ", japanese='" + japanese + '\'' +
                       '}';
            }
        };
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static long parseISO8601(final String timestamp){
        final TimeZone tz     = TimeZone.getTimeZone("UTC");
        final DateFormat df   = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);

        try{
            return df.parse(timestamp).getTime();
        }catch(final ParseException ignored){
            return -1;
        }
    }

    // Forum

    // todo

    // User

    // todo

}
