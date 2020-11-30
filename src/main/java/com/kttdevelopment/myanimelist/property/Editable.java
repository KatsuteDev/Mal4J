package com.kttdevelopment.myanimelist.property;

/**
 * Indicates that the object can be edited.
 *
 * @param <U> object updater
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface Editable<U> {

    /**
     * Returns the editor for the object.
     *
     * @return object editor
     *
     * @since 1.0.0
     */
    U edit();

}
