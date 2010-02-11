/* 
 * de.dan_nrw.caching
 * 
 * Copyright (C) 2010, Daniel Czerwonk <d.czerwonk@googlemail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.dan_nrw.caching;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 *
 */
public abstract class Cache {

    private static Cache current = new DefaultCache();
    
    
    /**
     * Returns the cache instance currently used by the caching aspect 
     * @return
     */
    public static Cache getCurrent() {
        return current;
    }
    
    /**
     * Sets the cache used by the caching aspect
     * @param cache
     */
    public static void setCurrent(Cache cache) {
        current = cache;
    }
    
    
    /**
     * Returns a list of currently valid keys
     * @return
     */
    public abstract Iterable<String> getKeys();
    
    /**
     * Adds an object to the cache
     * @param key Key to use for indexing value
     * @param value Value to put in the cache
     * @param durability Time of validity in milliseconds (0 = unlimited)
     */
    public abstract void put(String key, Object value, long durability);
    
    /**
     * Retrieves an object by key
     * @param <T>
     * @param key Key to use for finding value
     * @return
     * @throws IllegalArgumentException if key was not found in cache
     * @throws CachedDataExpiredException if validity of cached data expired
     */
    public abstract <T> T get(String key) throws IllegalArgumentException, CachedDataExpiredException;
    
    /**
     * Determines if the cache contains the specified key
     * @param key Key to use for finding value
     * @return
     */
    public abstract boolean containsKey(String key);
    
    /**
     * Removes an object from the cache
     * @param key Key to use for finding value
     */
    public abstract void removeKey(String key);
}