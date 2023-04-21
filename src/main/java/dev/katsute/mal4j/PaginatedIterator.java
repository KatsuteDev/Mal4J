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

import java.util.*;

/**
 * Represents a paged response.
 *
 * @param <T> type
 *
 * @since 1.0.0
 * @version 2.10.0
 * @author Katsute
 */
public abstract class PaginatedIterator<T> implements Iterator<T> {

    private int index = -1; // thread safe by methods
    List<T> list = new ArrayList<>();
    int size;

    private boolean hasNextItem(){
        return index+1 < size;
    }

    @Override
    public final boolean hasNext(){
        return hasNextItem() || hasNextPage();
    }

    @Override
    public synchronized final T next(){
        if(hasNextItem())
            return list.get(++index);
        else if(hasNextPage()){
            nextPage();
            return list.get(++index);
        }else
            throw new NoSuchElementException();
    }

    /**
     * Returns if the response has a next page.
     *
     * @return if has next page
     *
     * @since 1.0.0
     */
    abstract boolean hasNextPage();

    private synchronized void nextPage(){
        list = getNextPage();
        size = list.size();
        index = -1;
    }

    /**
     * Returns the next page in the response.
     *
     * @return next page
     *
     * @since 1.0.0
     */
    abstract List<T> getNextPage();

    @Override
    public synchronized final void remove(){
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the current page as a list.
     *
     * @return list
     *
     * @since 1.0.0
     */
    public final List<T> toList(){
        return new ArrayList<>(list);
    }

    /**
     * Returns the current page as a set.
     *
     * @return set
     *
     * @since 1.0.0
     */
    public final Set<T> toSet(){
        return new LinkedHashSet<>(list);
    }

}