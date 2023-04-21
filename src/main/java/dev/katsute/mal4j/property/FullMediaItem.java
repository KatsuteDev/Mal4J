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

import dev.katsute.mal4j.anime.RelatedAnime;
import dev.katsute.mal4j.manga.RelatedManga;

/**
 * Indicates that the object contains these media fields.
 *
 * @param <MediaType> media type enum
 * @param <Status> status type enum
 * @param <ListStatus> list status object
 * @param <Recommendation> recommendation object
 * @param <Statistics> statistics object
 *
 * @see MediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public interface FullMediaItem<MediaType extends Enum<?>,Status extends Enum<?>,ListStatus extends dev.katsute.mal4j.property.ListStatus<?>,Recommendation extends dev.katsute.mal4j.property.Recommendation,Statistics extends dev.katsute.mal4j.property.Statistics> extends MediaItem<MediaType,Status,ListStatus> {

    /**
     * Returns the pictures for the media item.
     *
     * @return pictures
     *
     * @see Picture
     * @see MediaItem#getMainPicture()
     * @since 1.0.0
     */
    Picture[] getPictures();

    /**
     * Returns the background information.
     *
     * @return background
     *
     * @since 1.0.0
     */
    String getBackground();

    /**
     * Returns a list of related Anime.
     *
     * @return related Anime
     *
     * @see RelatedAnime
     * @since 1.0.0
     */
    RelatedAnime[] getRelatedAnime();

    /**
     * Returns a list of related Manga.
     *
     * @return related Manga
     *
     * @see RelatedManga
     * @since 1.0.0
     */
    RelatedManga[] getRelatedManga();

    /**
     * Returns a list of recommendations.
     *
     * @return recommendations
     *
     * @see dev.katsute.mal4j.anime.AnimeRecommendation
     * @see dev.katsute.mal4j.manga.MangaRecommendation
     * @since 1.0.0
     */
    Recommendation[] getRecommendations();

    /**
     * Returns the user statistics.
     *
     * @return statistics
     *
     * @see dev.katsute.mal4j.property.Statistics
     * @see dev.katsute.mal4j.anime.property.AnimeStatistics
     * @see dev.katsute.mal4j.manga.property.MangaStatistics
     * @since 1.0.0
     */
    @SuppressWarnings("deprecation")
    Statistics getStatistics();

}