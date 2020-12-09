package com.kttdevelopment.myanimelist;

import java.util.*;

/**
 * Represents a paged response.
 *
 * @param <T> type
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PaginatedIterator<T> implements Iterator<T> {

    private int index = -1; // thread safe by methods
    private List<T> list = new ArrayList<>();

    private boolean hasNextItem(){
        return index+1 < list.size();
    }

    @Override
    public final boolean hasNext(){
        return hasNextItem() || hasNextPage();
    }

    @Override
    public synchronized final T next(){
        if(hasNextItem()){
            index++;
            return list.get(index);
        }else if(hasNextPage()){
            nextPage();
            index++;
            return list.get(index);
        }else
            throw new NoSuchElementException();
    }

    /**
     * Returns if the response has a next page.
     *
     * @return if next page
     *
     * @since 1.0.0
     */
    abstract boolean hasNextPage();

    private synchronized void nextPage(){
        list = getNextPage();
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
        return Collections.unmodifiableList(list);
    }

    /**
     * Returns the current page as a set.
     *
     * @return set
     *
     * @since 1.0.0
     */
    public final Set<T> toSet(){
        return Collections.unmodifiableSet(new LinkedHashSet<>(list));
    }

}
