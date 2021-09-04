/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
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
package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.Json.JsonObject;
import com.kttdevelopment.mal4j.anime.RelatedAnime;
import com.kttdevelopment.mal4j.manga.*;
import com.kttdevelopment.mal4j.manga.property.*;
import com.kttdevelopment.mal4j.property.*;
import com.kttdevelopment.mal4j.query.MangaListUpdate;

import java.util.*;

@SuppressWarnings({"unused", "ConstantConditions"})
abstract class MyAnimeListSchema_Manga extends MyAnimeListSchema {
    
    static Author asAuthor(final MyAnimeList mal, final JsonObject schema){
        return new Author() {

            private final Long id           = requireNonNull(() -> schema.getJsonObject("node").getLong("id"));
            private final String firstName  = requireNonNull(() -> schema.getJsonObject("node").getString("first_name"));
            private final String lastName   = requireNonNull(() -> schema.getJsonObject("node").getString("last_name"));
            private final String role       = requireNonNull(() -> schema.getString("role"));

            // API methods

            @Override
            public final Long getID() {
                return id;
            }

            @Override
            public final String getFirstName() {
                return firstName;
            }

            @Override
            public final String getLastName() {
                return lastName;
            }

            @Override
            public final String getRole() {
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
        return new Publisher() {

            private final Long id       = requireNonNull(() -> schema.getJsonObject("node").getLong("id"));
            private final String name   = requireNonNull(() -> schema.getJsonObject("node").getString("name"));
            private final String role   = requireNonNull(() -> schema.getString("role"));

            // API methods

            @Override
            public final Long getID() {
                return id;
            }

           @Override
            public final String getName() {
                return name;
            }

            @Override
            public final String getRole() {
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
        return new Manga() {

            private final Long id               = requireNonNull(() -> schema.getLong("id"));
            private final String title          = requireNonNull(() -> schema.getString("title"));
            private final Picture mainPicture   = requireNonNull(() -> MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture")));
            private final AlternativeTitles alternativeTitles
                                                = requireNonNull(() -> MyAnimeListSchema_Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles")));
            private final Long startDate        = requireNonNull(() -> parseDate(schema.getString("start_date")));
            private final Long endDate          = requireNonNull(() -> parseDate(schema.getString("end_date")));
            private final String synopsis       = requireNonNull(() -> schema.getString("synopsis"));
            private final Float meanRating      = requireNonNull(() -> schema.getFloat("mean"));
            private final Integer rank          = requireNonNull(() -> schema.getInt("rank"));
            private final Integer popularity    = requireNonNull(() -> schema.getInt("popularity"));
            private final Integer usersListing  = requireNonNull(() -> schema.getInt("num_list_users"));
            private final Integer usersScoring  = requireNonNull(() -> schema.getInt("num_scoring_users"));
            private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.getString("nsfw")));
            private final Genre[] genres        = requireNonNull(() -> adaptList(schema.getJsonArray("genres"), g -> Genre.asMangaGenre(g.getInt("id")), Genre.class));
            private final Long createdAt        = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
            private final Long updatedAt        = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
            private final MangaType type        = requireNonNull(() -> MangaType.asEnum(schema.getString("media_type")));
            private final MangaPublishStatus status
                                                = requireNonNull(() -> MangaPublishStatus.asEnum(schema.getString("status")));
            private final MangaListStatus listStatus
                                                = requireNonNull(() -> asMangaListStatus(mal, schema.getJsonObject("my_list_status"), id,this));
            private final Integer volumes       = requireNonNull(() -> schema.getInt("num_volumes"));
            private final Integer chapters      = requireNonNull(() -> schema.getInt("num_chapters"));
            private final Author[] authors      = requireNonNull(() -> adaptList(schema.getJsonArray("authors"), a -> asAuthor(mal, a), Author.class));
            private final Picture[] pictures    = requireNonNull(() -> adaptList(schema.getJsonArray("pictures"), p -> MyAnimeListSchema_Common.asPicture(mal, p), Picture.class));
            private final String background     = requireNonNull(() -> schema.getString("background"));
            private final RelatedAnime[] relatedAnime
                                                = requireNonNull(() -> adaptList(schema.getJsonArray("related_anime"), a -> MyAnimeListSchema_Anime.asRelatedAnime(mal, a), RelatedAnime.class));
            private final RelatedManga[] relatedManga
                                                = requireNonNull(() -> adaptList(schema.getJsonArray("related_manga"), m -> asRelatedManga(mal, m), RelatedManga.class));
            private final MangaRecommendation[] recommendations = requireNonNull(() -> adaptList(schema.getJsonArray("recommendations"), r -> asMangaRecommendation(mal, r), MangaRecommendation.class));
            private final Publisher[] serialization
                                                = requireNonNull(() -> adaptList(schema.getJsonArray("serialization"), s -> asPublisher(mal, s), Publisher.class));

            // API methods

            @Override
            public final Long getID() {
                return id;
            }

            @Override
            public final String getTitle() {
                return title;
            }

            @Override
            public final Picture getMainPicture() {
                return mainPicture;
            }

            @Override
            public final AlternativeTitles getAlternativeTitles() {
                return alternativeTitles;
            }

            @Override
            public final Date getStartDate() {
                return startDate == null ? null : new Date(startDate);
            }

            @Override
            public final Date getEndDate() {
                return endDate == null ? null : new Date(endDate);
            }

            @Override
            public final String getSynopsis() {
                return synopsis;
            }

            @Override
            public final Float  getMeanRating() {
                return meanRating;
            }

            @Override
            public final Integer getRank() {
                return rank;
            }

            @Override
            public final Integer getPopularity() {
                return popularity;
            }

            @Override
            public final Integer getUserListingCount() {
                return usersListing;
            }

            @Override
            public final Integer getUserScoringCount() {
                return usersScoring;
            }

            @Override
            public final NSFW getNSFW() {
                return nsfw;
            }

            @Override
            public final Genre[] getGenres() {
                return genres != null ? Arrays.copyOf(genres, genres.length) : null;
            }

            @Override
            public final Date getCreatedAt() {
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                return createdAt;
            }

            @Override
            public final Date getUpdatedAt() {
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                return updatedAt;
            }

            @Override
            public final MangaType getType() {
                return type;
            }

            @Override
            public final MangaPublishStatus getStatus() {
                return status;
            }

            @Override
            public final MangaListStatus getListStatus() {
                return listStatus;
            }

            @Override
            public final Integer getVolumes() {
                return volumes;
            }

            @Override
            public final Integer getChapters() {
                return chapters;
            }

            @Override
            public final Author[] getAuthors() {
                return authors != null ? Arrays.copyOf(authors, authors.length) : null;
            }

            @Override
            public final Picture[] getPictures() {
                return pictures != null ? Arrays.copyOf(pictures, pictures.length) : null;
            }

            @Override
            public final String getBackground() {
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime() {
                return relatedAnime != null ? Arrays.copyOf(relatedAnime, relatedAnime.length) : null;
            }

            @Override
            public final RelatedManga[] getRelatedManga() {
                return relatedManga != null ? Arrays.copyOf(relatedManga, relatedManga.length) : null;
            }

            @Override
            public final MangaRecommendation[] getRecommendations() {
                return recommendations != null ? Arrays.copyOf(recommendations, recommendations.length) : null;
            }

            @Override
            public final Publisher[] getSerialization() {
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

    static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final MangaPreview mangaPreview){
        return asMangaListStatus(mal, schema, null, Objects.requireNonNull(mangaPreview, "Manga preview must not be null"));
    }

    private static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final Long manga_id, final MangaPreview manga_preview){
        if(manga_id == null && manga_preview == null)
            throw new NullPointerException("Manga id and manga preview must not be both null");
        return new MangaListStatus() {

            private final MangaPreview manga        = manga_preview;
            private final Long id                   = manga_id;

            private final MangaStatus status        = requireNonNull(() -> MangaStatus.asEnum(schema.getString("status")));
            private final Integer score             = requireNonNull(() -> schema.getInt("score"));
            private final Long startDate            = requireNonNull(() -> parseDate(schema.getString("start_date")));
            private final Long finishDate           = requireNonNull(() -> parseDate(schema.getString("finish_date")));
            private final Priority priority         = requireNonNull(() -> Priority.asEnum(schema.getInt("priority")));
            private final String[] tags             = requireNonNull(() -> schema.getStringArray("tags"));
            private final String comments           = requireNonNull(() -> schema.getString("comments"));
            private final Long updatedAt            = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
            private final Integer volumesRead       = requireNonNull(() -> schema.getInt("num_volumes_read"));
            private final Integer chaptersRead      = requireNonNull(() -> schema.getInt("num_chapters_read"));
            private final Boolean rereading         = requireNonNull(() -> schema.getBoolean("is_rereading"));
            private final Integer timesReread       = requireNonNull(() -> schema.getInt("num_times_reread"));
            private final RereadValue rereadValue   = requireNonNull(() -> RereadValue.asEnum(schema.getInt("reread_value")));

            // API methods

            @Override
            public final MangaStatus getStatus() {
                return status;
            }

            @Override
            public final Integer getScore() {
                return score;
            }

            @Override
            public final Date getStartDate() {
                return startDate == null ? null : new Date(startDate);
            }

            @Override
            public final Date getFinishDate() {
                return finishDate == null ? null : new Date(finishDate);
            }

            @Override
            public final Priority getPriority() {
                return priority;
            }

            @Override
            public final String[] getTags() {
                return tags != null ? Arrays.copyOf(tags, tags.length) : null;
            }

            @Override
            public final String getComments() {
                return comments;
            }

            @Override
            public final Date getUpdatedAt() {
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                return updatedAt;
            }

            @Override
            public final Integer getVolumesRead() {
                return volumesRead;
            }

            @Override
            public final Integer getChaptersRead() {
                return chaptersRead;
            }

            @Override
            public final Boolean isRereading() {
                return rereading;
            }

            @Override
            public final Integer getTimesReread() {
                return timesReread;
            }

            @Override
            public final RereadValue getRereadValue() {
                return rereadValue;
            }

            // additional methods

            @Override
            public final Manga getManga(){
                return manga != null ? manga.getManga() : mal.getManga(id);
            }

            @Override
            public final MangaPreview getMangaPreview(){
                return manga != null ? manga : mal.getManga(id);
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

    static MangaPreview asMangaPreview(final MyAnimeList mal, final JsonObject schema){
        return new MangaPreview() {

            private final Long id               = requireNonNull(() -> schema.getLong("id"));
            private final String title          = requireNonNull(() -> schema.getString("title"));
            private final Picture mainPicture   = requireNonNull(() -> MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture")));
            private final AlternativeTitles alternativeTitles
                                                = requireNonNull(() -> MyAnimeListSchema_Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles")));
            private final Long startDate        = requireNonNull(() -> parseDate(schema.getString("start_date")));
            private final Long endDate          = requireNonNull(() -> parseDate(schema.getString("end_date")));
            private final String synopsis       = requireNonNull(() -> schema.getString("synopsis"));
            private final Float meanRating      = requireNonNull(() -> schema.getFloat("mean"));
            private final Integer rank          = requireNonNull(() -> schema.getInt("rank"));
            private final Integer popularity    = requireNonNull(() -> schema.getInt("popularity"));
            private final Integer usersListing  = requireNonNull(() -> schema.getInt("num_list_users"));
            private final Integer usersScoring  = requireNonNull(() -> schema.getInt("num_scoring_users"));
            private final NSFW nsfw             = requireNonNull(() -> NSFW.asEnum(schema.getString("nsfw")));
            private final Genre[] genres        = requireNonNull(() -> adaptList(schema.getJsonArray("genres"), g -> Genre.asMangaGenre(g.getInt("id")), Genre.class));
            private final Long createdAt        = requireNonNull(() -> parseISO8601(schema.getString("created_at")));
            private final Long updatedAt        = requireNonNull(() -> parseISO8601(schema.getString("updated_at")));
            private final MangaType type        = requireNonNull(() -> MangaType.asEnum(schema.getString("media_type")));
            private final MangaPublishStatus status
                                                = requireNonNull(() -> MangaPublishStatus.asEnum(schema.getString("status")));
            private final MangaListStatus listStatus
                                                = requireNonNull(() -> asMangaListStatus(mal, schema.getJsonObject("my_list_status"), id, this));
            private final Integer volumes       = requireNonNull(() -> schema.getInt("num_volumes"));
            private final Integer chapters      = requireNonNull(() -> schema.getInt("num_chapters"));
            private final Author[] authors      = requireNonNull(() -> adaptList(schema.getJsonArray("authors"), a -> asAuthor(mal, a), Author.class));

            // API methods

            @Override
            public final Long getID() {
                return id;
            }

            @Override
            public final String getTitle() {
                return title;
            }

            @Override
            public final Picture getMainPicture() {
                return mainPicture;
            }

            @Override
            public final AlternativeTitles getAlternativeTitles() {
                return alternativeTitles;
            }

            @Override
            public final Date getStartDate() {
                return startDate == null ? null : new Date(startDate);
            }

            @Override
            public final Date getEndDate() {
                return endDate == null ? null : new Date(endDate);
            }

            @Override
            public final String getSynopsis() {
                return synopsis;
            }

            @Override
            public final Float  getMeanRating() {
                return meanRating;
            }

            @Override
            public final Integer getRank() {
                return rank;
            }

            @Override
            public final Integer getPopularity() {
                return popularity;
            }

            @Override
            public final Integer getUserListingCount() {
                return usersListing;
            }

            @Override
            public final Integer getUserScoringCount() {
                return usersScoring;
            }

            @Override
            public final NSFW getNSFW() {
                return nsfw;
            }

            @Override
            public final Genre[] getGenres() {
                return genres != null ? Arrays.copyOf(genres, genres.length) : null;
            }

            @Override
            public final Date getCreatedAt() {
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                return createdAt;
            }

            @Override
            public final Date getUpdatedAt() {
                return updatedAt == null ? null : new Date(updatedAt);
            }

            @Override
            public final Long getUpdatedAtEpochMillis(){
                return updatedAt;
            }

            @Override
            public final MangaType getType() {
                return type;
            }

            @Override
            public final MangaPublishStatus getStatus() {
                return status;
            }

            @Override
            public final MangaListStatus getListStatus() {
                return listStatus;
            }

            @Override
            public final Integer getVolumes() {
                return volumes;
            }

            @Override
            public final Integer getChapters() {
                return chapters;
            }

            @Override
            public final Author[] getAuthors() {
                return authors != null ? Arrays.copyOf(authors, authors.length) : null;
            }

            // additional methods

            @Override
            public final Manga getManga() {
                return mal.getManga(id);
            }

            @Override
            public final String toString(){
                return "MangaPreview{" +
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
                       '}';
            }

        };
    }

    static MangaRanking asMangaRanking(final MyAnimeList mal, final JsonObject schema){
        return new MangaRanking() {

            private final MangaPreview manga        = requireNonNull(() -> asMangaPreview(mal, schema.getJsonObject("node")));
            private final Integer ranking           = requireNonNull(() -> schema.getJsonObject("ranking").getInt("rank"));
            private final Integer previousRanking   = requireNonNull(() -> schema.getJsonObject("ranking").getInt("previous_rank"));

            // API method

            @Override
            public final MangaPreview getMangaPreview() {
                return manga;
            }

            @Override
            public final Integer getRanking() {
                return ranking;
            }

            @Override
            public final Integer getPreviousRank() {
                return previousRanking;
            }

            // API methods

            @Override
            public final Manga getManga() {
                return mal.getManga(manga.getID());
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
        return new MangaRecommendation() {

            private final MangaPreview manga        = requireNonNull(() -> asMangaPreview(mal, schema.getJsonObject("node")));
            private final Integer recommendations   = requireNonNull(() -> schema.getInt("num_recommendations"));

            // API methods

            @Override
            public final MangaPreview getMangaPreview() {
                return manga;
            }

            @Override
            public final Integer getRecommendations() {
                return recommendations;
            }

            // additional methods

            @Override
            public final Manga getManga() {
                return mal.getManga(manga.getID());
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
        return new RelatedManga() {

            private final MangaPreview manga            = requireNonNull(() -> asMangaPreview(mal, schema.getJsonObject("node")));
            private final RelationType relationType     = requireNonNull(() -> RelationType.asEnum(schema.getString("relation_type")));
            private final String relationTypeFormatted  = requireNonNull(() -> schema.getString("relation_type_formatted"));

            // API methods

            @Override
            public final MangaPreview getMangaPreview() {
                return manga;
            }

            @Override
            public final RelationType getRelationType() {
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat() {
                return relationTypeFormatted;
            }

            // API methods

            @Override
            public final Manga getManga() {
                return mal.getManga(manga.getID());
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
