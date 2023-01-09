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

package dev.katsute.mal4j.property;

import java.util.Date;

/**
 * Indicates that the object contains these media fields.
 *
 * @param <MediaType> media type enum
 * @param <Status> status type enum
 * @param <ListStatus> list status object
 *
 * @see FullMediaItem
 * @since 1.0.0
 * @version 3.0.0
 * @author Katsute
 */
public interface MediaItem<MediaType extends Enum<?>,Status extends Enum<?>,ListStatus extends dev.katsute.mal4j.property.ListStatus<?>> extends ID {

    /**
     * Returns the title.
     *
     * @return title
     *
     * @since 1.0.0
     */
    String getTitle();

    /**
     * Returns the main picture.
     *
     * @return main picture
     *
     * @see Picture
     * @since 1.0.0
     */
    Picture getMainPicture();

    /**
     * Returns the alternative titles.
     *
     * @return alternative titles
     *
     * @see AlternativeTitles
     * @since 1.0.0
     */
    AlternativeTitles getAlternativeTitles();

    /**
     * Returns the start date.
     *
     * @return start date
     *
     * @see NullableDate
     * @see #getEndDate()
     */
    NullableDate getStartDate();

    /**
     * Returns the end date.
     *
     * @return end date
     *
     * @see NullableDate
     * @see #getStartDate()
     * @since 1.0.0
     */
    NullableDate getEndDate();

    /**
     * Returns the synopsis.
     *
     * @return synopsis
     *
     * @since 1.0.0
     */
    String getSynopsis();

    /**
     * Returns the average user rating.
     *
     * @return mean rating
     *
     * @since 1.0.0
     */
    Float getMeanRating();

    /**
     * Returns the overall rank.
     *
     * @return rank
     *
     * @since 1.0.0
     */
    Integer getRank();

    /**
     * Returns the popularity.
     *
     * @return popularity
     *
     * @since 1.0.0
     */
    Integer getPopularity();

    /**
     * Returns how many users have this item on their list.
     *
     * @return user list count
     *
     * @since 1.0.0
     */
    Integer getUserListingCount();

    /**
     * Returns how many users have this item scored on their list.
     *
     * @return user scoring count
     *
     * @since 1.0.0
     */
    Integer getUserScoringCount();

    /**
     * Returns the NSFW rating.
     *
     * @return NSFW
     *
     * @see #getRawNSFW()
     * @see NSFW
     * @since 1.0.0
     */
    NSFW getNSFW();

    /**
     * Returns the raw NSFW rating.
     * <br>
     * It is recommended to use {@link #getNSFW()} rather than this method.
     * This method should only be used if the NSFW value is missing from {@link NSFW}.
     *
     * @return raw NSFW
     *
     * @see #getNSFW()
     * @since 2.9.0
     */
    String getRawNSFW();

    /**
     * Returns the genres.
     *
     * @return genres
     *
     * @since 1.0.0
     */
    Genre[] getGenres();

    /**
     * Returns the creation date.
     *
     * @return creation date
     *
     * @see #getCreatedAtEpochMillis()
     * @see #getUpdatedAt()
     * @see #getUpdatedAtEpochMillis()
     * @since 1.0.0
     */
    Date getCreatedAt();

    /**
     * Returns the creation date as milliseconds since epoch.
     *
     * @return creation time
     *
     * @see #getCreatedAt()
     * @see #getUpdatedAt()
     * @see #getUpdatedAt()
     * @since 1.0.0
     */
    Long getCreatedAtEpochMillis();

    /**
     * Returns when this was last updated.
     *
     * @return updated time
     *
     * @see #getUpdatedAtEpochMillis()
     * @see #getCreatedAt()
     * @see #getCreatedAtEpochMillis()
     * @since 1.0.0
     */
    Date getUpdatedAt();

    /**
     * Returns when this was last updated as milliseconds since epoch.
     *
     * @return updated time
     *
     * @see #getUpdatedAt()
     * @see #getCreatedAt()
     * @see #getCreatedAtEpochMillis()
     * @since 1.0.0
     */
    Long getUpdatedAtEpochMillis();

    /**
     * Returns the media type.
     *
     * @return media type
     *
     * @see #getRawType()
     * @see dev.katsute.mal4j.anime.property.AnimeType
     * @see dev.katsute.mal4j.manga.property.MangaType
     * @since 1.0.0
     */
    MediaType getType();

    /**
     * Returns the raw media type.
     * <br>
     * It is recommended to use {@link #getType()} rather than this method.
     * This method should only be used if the type value is missing from {@link dev.katsute.mal4j.anime.property.AnimeType} or {@link dev.katsute.mal4j.manga.property.MangaType}.
     *
     * @return raw media type
     *
     * @see #getType()
     * @since 2.9.0
     */
    String getRawType();

    /**
     * Returns the status.
     *
     * @return status
     *
     * @see #getRawStatus()
     * @see dev.katsute.mal4j.anime.property.AnimeAirStatus
     * @see dev.katsute.mal4j.manga.property.MangaPublishStatus
     * @since 1.0.0
     */
    Status getStatus();

    /**
     * Returns the raw status.
     * <br>
     * It is recommended to use {@link #getStatus()} rather than this method.
     * This method should only be used if the status is missing from {@link dev.katsute.mal4j.anime.property.AnimeAirStatus} or {@link dev.katsute.mal4j.manga.property.MangaPublishStatus}.
     *
     * @return raw status
     *
     * @see #getStatus()
     * @since 2.9.0
     */
    String getRawStatus();

    /**
     * Returns the user's list status.
     *
     * @return list status
     *
     * @see dev.katsute.mal4j.anime.AnimeListStatus
     * @see dev.katsute.mal4j.manga.MangaListStatus
     * @since 1.0.0
     */
    ListStatus getListStatus();

}
