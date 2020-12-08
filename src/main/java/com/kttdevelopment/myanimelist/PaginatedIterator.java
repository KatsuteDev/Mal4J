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

    private int index = 0; // thread safe by methods
    private List<T> list = new ArrayList<>();

    private boolean hasNextItem(){
        return index < list.size();
    }

    @Override
    public final boolean hasNext(){
        return hasNextItem() || hasNextPage();
    }

    @Override
    public synchronized final T next(){
        if(hasNextItem())
            return list.get(index++);
        else if(hasNextPage()){
            nextPage();
            return list.get(index++);
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
    public abstract boolean hasNextPage();

    public synchronized final void nextPage(){
        if(hasNextPage()){
            list = getNextPage();
            index = -1;
        }else
            throw new NoSuchElementException();
    }

    /**
     * Returns the next page in the response.
     *
     * @return next page
     *
     * @since 1.0.0
     */
    protected abstract List<T> getNextPage();

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
