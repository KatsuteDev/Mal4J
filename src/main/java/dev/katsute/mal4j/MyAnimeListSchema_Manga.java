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

import java.util.*;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Manga extends MyAnimeListSchema {

    static Author asAuthor(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Author() {

            private final Long id           = schema.getJsonObject("node").getLong("id");
            private final String firstName  = schema.getJsonObject("node").getString("first_name");
            private final String lastName   = schema.getJsonObject("node").getString("last_name");
            private final String role       = schema.getString("role");

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
        return schema == null ? null : new Publisher() {

            private final Long id       = schema.getJsonObject("node").getLong("id");
            private final String name   = schema.getJsonObject("node").getString("name");
            private final String role   = schema.getString("role");

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
        return asManga(mal, schema, false);
    }

    private static Manga asManga(final MyAnimeList mal, final JsonObject schema, final boolean isPreview){
        return schema == null ? null : new Manga() {

            private final Long id               = schema.getLong("id");
            private final String title          = schema.getString("title");
            private final Picture mainPicture   = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));
            private final AlternativeTitles alternativeTitles
                                                = MyAnimeListSchema_Common.asAlternativeTitles(mal, schema.getJsonObject("alternative_titles"));
            private final NullableDate startDate = parseNullableDate(schema.getString("start_date"));
            private final NullableDate endDate  = parseNullableDate(schema.getString("end_date"));
            private final String synopsis       = schema.getString("synopsis");
            private final Float meanRating      = schema.getFloat("mean");
            private final Integer rank          = schema.getInt("rank");
            private final Integer popularity    = schema.getInt("popularity");
            private final Integer usersListing  = schema.getInt("num_list_users");
            private final Integer usersScoring  = schema.getInt("num_scoring_users");
            private final String nsfw           = schema.getString("nsfw");
            private final NSFW e_nsfw           = NSFW.asEnum(nsfw);
            private final Genre[] genres        = adaptList(schema.getJsonArray("genres"), g -> MyAnimeListSchema_Common.asGenre(mal, g, false), Genre.class);
            private final Long createdAt        = parseISO8601(schema.getString("created_at"));
            private final Long updatedAt        = parseISO8601(schema.getString("updated_at"));
            private final String type           = schema.getString("media_type");
            private final MangaType e_type      = MangaType.asEnum(type);
            private final String status         = schema.getString("status");
            private final MangaPublishStatus e_status
                                                = MangaPublishStatus.asEnum(status);
            private final MangaListStatus listStatus
                                                = asMangaListStatus(mal, schema.getJsonObject("my_list_status"), id,this);
            private final Integer volumes       = schema.getInt("num_volumes");
            private final Integer chapters      = schema.getInt("num_chapters");
            private final Author[] authors      = adaptList(schema.getJsonArray("authors"), a -> asAuthor(mal, a), Author.class);

            // full only

            private boolean isFull = !isPreview;

            @SuppressWarnings("DataFlowIssue")
            private void populate(){
                if(!isFull){
                    final Manga manga = mal.getManga(id);

                    pictures        = manga.getPictures();
                    background      = manga.getBackground();
                    relatedAnime    = manga.getRelatedAnime();
                    relatedManga    = manga.getRelatedManga();
                    recommendations = manga.getRecommendations();
                    serialization   = manga.getSerialization();

                    isFull = true;
                }
            }

            @SuppressWarnings("BooleanMethodIsAlwaysInverted")
            private boolean isPopulate(){
                final String ln = new Exception().getStackTrace()[2].toString();
                return ln.startsWith("dev.katsute.mal4j.MyAnimeListSchema_Manga") && ln.substring(43).startsWith(".populate(MyAnimeListSchema_Manga.java:");
            }

            private Picture[] pictures          = adaptList(schema.getJsonArray("pictures"), p -> MyAnimeListSchema_Common.asPicture(mal, p), Picture.class);
            private String background           = schema.getString("background");
            private RelatedAnime[] relatedAnime = adaptList(schema.getJsonArray("related_anime"), a -> MyAnimeListSchema_Anime.asRelatedAnime(mal, a), RelatedAnime.class);
            private RelatedManga[] relatedManga = adaptList(schema.getJsonArray("related_manga"), m -> asRelatedManga(mal, m), RelatedManga.class);
            private MangaRecommendation[] recommendations = adaptList(schema.getJsonArray("recommendations"), r -> asMangaRecommendation(mal, r), MangaRecommendation.class);
            private Publisher[] serialization   = adaptList(schema.getJsonArray("serialization"), s -> asPublisher(mal, s), Publisher.class);

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
            public final NullableDate getStartDate() {
                return startDate;
            }

            @Override
            public final NullableDate getEndDate() {
                return endDate;
            }

            @Override
            public final String getSynopsis() {
                return synopsis;
            }

            @Override
            public final Float getMeanRating() {
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
                return e_nsfw;
            }

            @Override
            public final String getRawNSFW(){
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
                return e_type;
            }

            @Override
            public final String getRawType(){
                return type;
            }

            @Override
            public final MangaPublishStatus getStatus() {
                return e_status;
            }

            @Override
            public String getRawStatus(){
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

            // full only

            @Override
            public final Picture[] getPictures() {
                if(!isFull) populate();
                return pictures != null ? Arrays.copyOf(pictures, pictures.length) : null;
            }

            @Override
            public final String getBackground() {
                if(!isFull) populate();
                return background;
            }

            @Override
            public final RelatedAnime[] getRelatedAnime() {
                if(!isFull) populate();
                return relatedAnime != null ? Arrays.copyOf(relatedAnime, relatedAnime.length) : null;
            }

            @Override
            public final RelatedManga[] getRelatedManga() {
                if(!isFull) populate();
                return relatedManga != null ? Arrays.copyOf(relatedManga, relatedManga.length) : null;
            }

            @Override
            public final MangaRecommendation[] getRecommendations() {
                if(!isFull) populate();
                return recommendations != null ? Arrays.copyOf(recommendations, recommendations.length) : null;
            }

            @Override
            public final Publisher[] getSerialization() {
                if(!isFull) populate();
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

    static Manga asMangaPreview(final MyAnimeList mal, final JsonObject schema){
        return asManga(mal, schema, true);
    }

    static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final long manga_id){
        return asMangaListStatus(mal, schema, manga_id, null);
    }

    static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final Manga manga){
        return asMangaListStatus(mal, schema, null, Objects.requireNonNull(manga, "Manga must not be null"));
    }

    private static MangaListStatus asMangaListStatus(final MyAnimeList mal, final JsonObject schema, final Long manga_id, final Manga manga_full){
        if(manga_id == null && manga_full == null)
            throw new NullPointerException("Manga and ID must not be both null");
        return schema == null ? null : new MangaListStatus() {

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
            public final MangaStatus getStatus() {
                return e_status;
            }

            @Override
            public final String getRawStatus(){
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
                return e_priority;
            }

            @Override
            public final Integer getRawPriority(){
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
        return schema == null ? null : new MangaRanking() {

            private final Manga manga               = asMangaPreview(mal, schema.getJsonObject("node"));
            private final Integer ranking           = schema.getJsonObject("ranking").getInt("rank");
            private final Integer previousRanking   = schema.getJsonObject("ranking").getInt("previous_rank");

            // API method

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
        return schema == null ? null : new MangaRecommendation() {

            private final Manga manga               = asMangaPreview(mal, schema.getJsonObject("node"));
            private final Integer recommendations   = schema.getInt("num_recommendations");

            // API methods

            @Override
            public final Integer getRecommendations() {
                return recommendations;
            }

            // additional methods

            @Override
            public final Manga getManga() {
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
        return schema == null ? null : new RelatedManga() {

            private final Manga manga                   = asMangaPreview(mal, schema.getJsonObject("node"));
            private final String relationType           = schema.getString("relation_type");
            private final RelationType e_relationType   = RelationType.asEnum(relationType);
            private final String relationTypeFormatted  = schema.getString("relation_type_formatted");

            // API methods

            @Override
            public final RelationType getRelationType() {
                return e_relationType;
            }

            @Override
            public final String getRawRelationType(){
                return relationType;
            }

            @Override
            public final String getRelationTypeFormat() {
                return relationTypeFormatted;
            }

            // API methods

            @Override
            public final Manga getManga() {
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
