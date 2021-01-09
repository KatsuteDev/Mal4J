/*
 * Copyright (C) 2021 Ktt Development
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

package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.forum.Post;
import com.kttdevelopment.myanimelist.property.IDN;
import com.kttdevelopment.myanimelist.user.property.UserRetrievable;

/**
 * Returns the post's author.
 *
 * @see Post#getAuthor()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PostAuthor implements IDN, UserRetrievable {

    // API methods

    /**
     * Returns the author's avatar URL.
     *
     * @return avatar URL
     *
     * @since 1.0.0
     */
    public abstract String getForumAvatarURL();

}
