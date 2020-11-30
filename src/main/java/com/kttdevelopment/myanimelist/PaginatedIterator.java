package com.kttdevelopment.myanimelist;

import java.util.*;

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

    public abstract boolean hasNextPage();

    public synchronized final void nextPage(){
        if(hasNextPage()){
            list = getNextPage();
            index = -1;
        }else
            throw new NoSuchElementException();
    }

    protected abstract List<T> getNextPage();

    @Override
    public synchronized final void remove(){
        throw new UnsupportedOperationException();
    }

    public final List<T> toList(){
        return Collections.unmodifiableList(list);
    }

    public final Set<T> toSet(){
        return Collections.unmodifiableSet(new LinkedHashSet<>(list));
    }

}
