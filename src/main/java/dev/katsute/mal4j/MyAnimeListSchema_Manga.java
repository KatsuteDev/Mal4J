/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package dev.katsute.mal4j;

import dev.katsute.mal4j.Json.JsonObject;
import dev.katsute.mal4j.anime.RelatedAnime;
import dev.katsute.mal4j.manga.*;
import dev.katsute.mal4j.manga.property.*;
import dev.katsute.mal4j.property.*;
import dev.katsute.mal4j.query.MangaListUpdate;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Manga extends MyAnimeListSchema {

    static Author asAuthor(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Author(){

            private final Long id           = schema.getJsonObject("node").getLong("id");
            private final String firstName  = schema.getJsonObject("node").getString("first_name");
            private final String lastName   = schema.getJsonObject("node").getString("last_name");
            private final String role       = schema.getString("role");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getFirstName(){
                return firstName;
            }

            @Override
            public final String getLastName(){
                return lastName;
            }

            @Override
            public final String getRole(){
                return role;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Author{" +
                       "id=" + id +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", role='" + role + '\'' +
                       '}';
            }

        };
    }

    static Publisher asPublisher(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Publisher(){

            private final Long id       = schema.getJsonObject("node").getLong("id");
            private final String name   = schema.getJsonObject("node").getString("name");
            private final String role   = schema.getString("role");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

           @Override
            public final String getName(){
                return name;
            }

            @Override
            public final String getRole(){
                return role;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Publisher{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", role='" + role + '\'' +
                       '}';
            }

        };
    }

    static Manga asManga(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Manga(){

            boolean draft = true; // if any field is null, try and fetch full values (only try once)

            private final Long id = schema.getLong("id");

            private String title;
            private Picture mainPicture;
            private AlternativeTitles alternativeTitles;
            private NullableDate startDate;
            private NullableDate endDate;
            private String synopsis;
            private Float meanRating;
            private Integer rank;
            private Integer popularity;
            private Integer usersListing;
            private Integer usersScoring;
            private String nsfw;
            private NSFW e_nsfw;
            private Genre[] genres;
            private Long createdAt;
            private Long updatedAt;
            private String type;
            private MangaType e_type;
            private String status;
            private MangaPublishStatus e_status;
            private MangaListStatus listStatus;
            private Integer volumes;
            private Integer chapters;
            private Author[] authors;

            private Picture[] pictures;
            private String background;
            private RelatedAnime[] relatedAnime;
            private RelatedManga[] relatedManga;
            private MangaRecommendation[] recommendations;
            private Publisher[] serialization;

            {
                populate(schema);
            }

            @SuppressWarnings("DataFlowIssue")
            private void populate(){
                if(draft){
                    draft = false;
                    populate(((MyAnimeListImpl) mal).getMangaSchema(id, ((String[]) null)));
                }
            }

            private void populate(final JsonObject schema){
                title             = schema.getString("title");
                mainPicture       = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));
                alternativeTitles = MyAnimeListSchema_Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles"));
                startDate         = parseNullableDate(schema.getString("start_date"));
                endDate           = parseNullableDate(schema.getString("end_date"));
                synopsis          = schema.getString("synopsis");
                meanRating        = schema.getFloat("mean");
                rank              = schema.getInt("rank");
                popularity        = schema.getInt("popularity");
                usersListing      = schema.getInt("num_list_users");
                usersScoring      = schema.getInt("num_scoring_users");
                nsfw              = schema.getString("nsfw");
                e_nsfw            = NSFW.asEnum(nsfw);
                genres            = adaptList(schema.getJsonArray("genres"), g -> MyAnimeListSchema_Common.asGenre(mal, g, false), Genre.class);
                createdAt         = parseISO8601(schema.getString("created_at"));
                updatedAt         = parseISO8601(schema.getString("updated_at"));
                type              = schema.getString("media_type");
                e_type            = MangaType.asEnum(type);
                status            = schema.getString("status");
                e_status          = MangaPublishStatus.asEnum(status);
                listStatus        = asMangaListStatus(mal, schema.getJsonObject("my_list_status"), id,this);
                volumes           = schema.getInt("num_volumes");
                chapters          = schema.getInt("num_chapters");
                authors           = adaptList(schema.getJsonArray("authors"), a -> asAuthor(mal, a), Author.class);

                pictures          = adaptList(schema.getJsonArray("pictures"), p -> MyAnimeListSchema_Common.asPicture(mal, p), Picture.class);
                background        = schema.getString("background");
                relatedAnime      = adaptList(schema.getJsonArray("related_anime"), a -> MyAnimeListSchema_Anime.asRelatedAnime(mal, a), RelatedAnime.class);
                relatedManga      = adaptList(schema.getJsonArray("related_manga"), m -> asRelatedManga(mal, m), RelatedManga.class);
                recommendations   = adaptList(schema.getJsonArray("recommendations"), r -> asMangaRecommendation(mal, r), MangaRecommendation.class);
                serialization     = adaptList(schema.getJsonArray("serialization"), s -> asPublisher(mal, s), Publisher.class);
            }

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                if(title == null && draft)
                    populate();
                return title;
            }

            @Override
            public final Picture getMainPicture(){
                if(mainPicture == null && draft)
                    populate();
                return mainPicture;
            }

            @Override
            public final AlternativeTitles getAlternativeTitles(){
                if(alternativeTitles == null && draft)
                    populate();
                return alternativeTitles;
            }

            @Override
            public final NullableDate getStartDate(){
                if(startDate == null && draft)
                    populate();
                return startDate;
            }

            @Override
            public final NullableDate getEndDate(){
                if(endDate == null && draft)
                    populate();
                return endDate;
            }

            @Override
            public final String getSynopsis(){
                if(synopsis == null && draft)
                    populate();
                return synopsis;
            }

            @Override
            public final Float getMeanRating(){
                if(meanRating == null && draft)
                    populate();
                return meanRating;
            }

            @Override
            public final Integer getRank(){
                if(rank == null && draft)
                    populate();
                return rank;
            }

            @Override
            public final Integer getPopularity(){
                if(popularity == null && draft)
                    populate();
                return popularity;
            }

            @Override
            public final Integer getUserListingCount(){
                if(usersListing == null && draft)
                    populate();
                return usersListing;
            }

            @Override
            public final Integer getUserScoringCount(){
                if(usersScoring == null && draft)
                    populate();
                return usersScoring;
            }

            @Override
            public final NSFW getNSFW(){
                if(e_nsfw == null && draft)
                    populate();
                return e_nsfw;
            }

            @Override
            public final String getRawNSFW(){
                if(nsfw == null && draft)
                    populate();
                return nsfw;
            }

            @Override
            public final Genre[] getGenres(){
                if(genres == null && draft)
                    populate();
                return genres != null ? Arrays.copyOf(genres, genres.length) : null;
            }

            @Override
            public final Date getCreatedAt(){
                if(createdAt == null && draft)
                    populate();
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                if(createdAt == null && draft)
                    populate();
                return createdAt;
            }

            @Override
            public final Date getUpdatedAt(){
                if(updatedAt == null && draft)
                    populate();
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                if(updatedAt == null && draft)
                    populate();
                return updatedAt;
            }

            @Override
            public final MangaType getType(){
                if(e_type == MangaType.Unknown && draft)
                    populate();
                return e_type;
            }

            @Override
            public final String getRawType(){
                if(type == null && draft)
                    populate();
                return type;
            }

            @Override
            public final MangaPublishStatus getStatus(){
                if(e_status == MangaPublishStatus.Unknown && draft)
                    populate();
                return e_status;
            }

            @Override
            public String getRawStatus(){
                if(status == null && draft)
                    populate();
                return status;
            }

            @Override
            public final MangaListStatus getListStatus(){
                if(listStatus == null && draft)
                    populate();
                return listStatus;
            }

            @Override
            public final Integer getVolumes(){
                if(volumes == null && draft)
                    populate();
                return volumes;
            }

            @Override
            public final Integer getChapters(){
                if(chapters == null && draft)
                    populate();
                return chapters;
            }

            @Override
            public final Author[] getAuthors(){
                if(authors == null && draft)
                    populate();
                return authors != null ? Arrays.copyOf(authors, authors.length) : null;
            }

            // full only

            @Override
            public final Picture[] getPictures(){
                if(pictures == null && draft)
                    populate();
                return pictures != null ? Arrays.copyOf(pictures, pictures.length) : null;
            }

            @Override
            public final String getBackground(){
                if(background == null && draft)
                    populate();
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime(){
                if(relatedAnime == null && draft)
                    populate();
                return relatedAnime != null ? Arrays.copyOf(relatedAnime, relatedAnime.length) : null;
            }

            @Override
            public final RelatedManga[] getRelatedManga(){
                if(relatedManga == null && draft)
                    populate();
                return relatedManga != null ? Arrays.copyOf(relatedManga, relatedManga.length) : null;
            }

            @Override
            public final MangaRecommendation[] getRecommendations(){
                if(recommendations == null && draft)
                    populate();
                return recommendations != null ? Arrays.copyOf(recommendations, recommendations.length) : null;
            }

            @Override
            public final Publisher[] getSerialization(){
                if(serialization == null && draft)
                    populate();
                return serialization != null ? Arrays.copyOf(serialization, serialization.length) : null;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Manga{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", mainPicture=" + mainPicture +
                       ", alternativeTitles=" + alternativeTitles +
                       ", startDate=" + startDate +
                       ", endDate=" + endDate +
                       ", synopsis='" + synopsis + '\'' +
                       ", meanRating=" + meanRating +
                       ", rank=" + rank +
                       ", popularity=" + popularity +
                       ", usersListing=" + usersListing +
                       ", usersScoring=" + usersScoring +
                       ", nsfw=" + nsfw +
                       ", genres=" + Arrays.toString(genres) +
                       ", createdAt=" + createdAt +
                       ", updatedAt=" + updatedAt +
                       ", type=" + type +
                       ", status=" + status +
                       ", listStatus=" + listStatus +
                       ", volumes=" + volumes +
                       ", chapters=" + chapters +
                       ", authors=" + Arrays.toString(authors) +
                       ", pictures=" + Arrays.toString(pictures) +
                       ", background='" + background + '\'' +
                       ", relatedAnime=" + Arrays.toString(relatedAnime) +
                       ", relatedManga=" + Arrays.toString(relatedManga) +
                       ", recommendations=" + Arrays.toString(recommendations) +
                       ", serialization=" + Arrays.toString(serialization) +
                       '}';
            }

        };
    }

    static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final long manga_id){
        return asMangaListStatus(mal, schema, manga_id, null);
    }

    static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final Manga manga){
        return asMangaListStatus(mal, schema, null, Objects.requireNonNull(manga, "Manga must not be null"));
    }

    static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final Long manga_id, final Manga manga_full){
        if(manga_id == null && manga_full == null)
            throw new NullPointerException("Manga and ID must not be both null");
        return schema == null ? null : new MangaListStatus(){

            private Manga manga                     = manga_full;
            private final Long id                   = manga_id != null ? manga_id : manga_full.getID();

            private final String status             = schema.getString("status");
            private final MangaStatus e_status      = MangaStatus.asEnum(status);
            private final Integer score             = schema.getInt("score");
            private final Long startDate            = parseDate(schema.getString("start_date"));
            private final Long finishDate           = parseDate(schema.getString("finish_date"));
            private final Integer priority          = schema.getInt("priority");
            private final Priority e_priority       = Priority.asEnum(priority);
            private final String[] tags             = schema.getStringArray("tags");
            private final String comments           = schema.getString("comments");
            private final Long updatedAt            = parseISO8601(schema.getString("updated_at"));
            private final Integer volumesRead       = schema.getInt("num_volumes_read");
            private final Integer chaptersRead      = schema.getInt("num_chapters_read");
            private final Boolean rereading         = schema.getBoolean("is_rereading");
            private final Integer timesReread       = schema.getInt("num_times_reread");
            private final Integer rereadValue       = schema.getInt("reread_value");
            private final RereadValue e_rereadValue = RereadValue.asEnum(rereadValue);

            // API methods

            @Override
            public final MangaStatus getStatus(){
                return e_status;
            }

            @Override
            public final String getRawStatus(){
                return status;
            }

            @Override
            public final Integer getScore(){
                return score;
            }

            @Override
            public final Date getStartDate(){
                return startDate == null ? null : new Date(startDate);
            }

            @Override
            public final Date getFinishDate(){
                return finishDate == null ? null : new Date(finishDate);
            }

            @Override
            public final Priority getPriority(){
                return e_priority;
            }

            @Override
            public final Integer getRawPriority(){
                return priority;
            }

            @Override
            public final String[] getTags(){
                return tags != null ? Arrays.copyOf(tags, tags.length) : null;
            }

            @Override
            public final String getComments(){
                return comments;
            }

            @Override
            public final Date getUpdatedAt(){
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                return updatedAt;
            }

            @Override
            public final Integer getVolumesRead(){
                return volumesRead;
            }

            @Override
            public final Integer getChaptersRead(){
                return chaptersRead;
            }

            @Override
            public final Boolean isRereading(){
                return rereading;
            }

            @Override
            public final Integer getTimesReread(){
                return timesReread;
            }

            @Override
            public final RereadValue getRereadValue(){
                return e_rereadValue;
            }

            @Override
            public final Integer getRawRereadValue(){
                return rereadValue;
            }

            // additional methods

            @Override
            public final Manga getManga(){
                return manga != null ? manga : (manga = mal.getManga(id));
            }

            @Override
            public final MangaListUpdate edit(){
                return mal.updateMangaListing(id != null ? id : manga.getID());
            }

            @Override
            public final String toString(){
                return "MangaListStatus{" +
                       "id=" + id +
                       ", status=" + status +
                       ", score=" + score +
                       ", startDate=" + startDate +
                       ", finishDate=" + finishDate +
                       ", priority=" + priority +
                       ", tags=" + Arrays.toString(tags) +
                       ", comments='" + comments + '\'' +
                       ", updatedAt=" + updatedAt +
                       ", volumesRead=" + volumesRead +
                       ", chaptersRead=" + chaptersRead +
                       ", rereading=" + rereading +
                       ", timesReread=" + timesReread +
                       ", rereadValue=" + rereadValue +
                       '}';
            }

        };
    }

    static MangaRanking asMangaRanking(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new MangaRanking(){

            private final Manga manga               = asManga(mal, schema.getJsonObject("node"));
            private final Integer ranking           = schema.getJsonObject("ranking").getInt("rank");
            private final Integer previousRanking   = schema.getJsonObject("ranking").getInt("previous_rank");

            // API method

            @Override
            public final Integer getRanking(){
                return ranking;
            }

            @Override
            public final Integer getPreviousRank(){
                return previousRanking;
            }

            // API methods

            @Override
            public final Manga getManga(){
                return manga;
            }

            @Override
            public final String toString(){
                return "MangaRanking{" +
                       "manga=" + manga +
                       ", ranking=" + ranking +
                       ", previousRanking=" + previousRanking +
                       '}';
            }

        };
    }

    static MangaRecommendation asMangaRecommendation(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new MangaRecommendation(){

            private final Manga manga               = asManga(mal, schema.getJsonObject("node"));
            private final Integer recommendations   = schema.getInt("num_recommendations");

            // API methods

            @Override
            public final Integer getRecommendations(){
                return recommendations;
            }

            // additional methods

            @Override
            public final Manga getManga(){
                return manga;
            }

            @Override
            public final String toString(){
                return "MangaRecommendation{" +
                       "manga=" + manga +
                       ", recommendations=" + recommendations +
                       '}';
            }

        };
    }

    static RelatedManga asRelatedManga(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new RelatedManga(){

            private final Manga manga                   = asManga(mal, schema.getJsonObject("node"));
            private final String relationType           = schema.getString("relation_type");
            private final RelationType e_relationType   = RelationType.asEnum(relationType);
            private final String relationTypeFormatted  = schema.getString("relation_type_formatted");

            // API methods

            @Override
            public final RelationType getRelationType(){
                return e_relationType;
            }

            @Override
            public final String getRawRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat(){
                return relationTypeFormatted;
            }

            // API methods

            @Override
            public final Manga getManga(){
                return manga;
            }

            @Override
            public final String toString(){
                return "RelatedManga{" +
                       "manga=" + manga +
                       ", relationType=" + relationType +
                       ", relationTypeFormatted='" + relationTypeFormatted + '\'' +
                       '}';
            }

        };
    }

}