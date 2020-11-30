package com.kttdevelopment.myanimelist.property;

/**
 * Indicates that the object has an ID and name.
 *
 * @see ID
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface IDN extends ID{

    /**
     * Object name.
     *
     * @return name
     *
     * @since 1.0.0
     */
    String getName();

}
