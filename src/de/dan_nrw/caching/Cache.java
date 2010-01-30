package de.dan_nrw.caching;


/**
 * @author Daniel Czerwonk
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
     * Adds an object to the cache
     * @param key
     * @param value
     */
    public abstract void put(String key, Object value);
    
    /**
     * Retrieves an object by key
     * @param <T>
     * @param key
     * @return
     */
    public abstract <T> T get(String key);
    
    /**
     * Determines if the cache contains the specified key
     * @param key
     * @return
     */
    public abstract boolean containsKey(String key);
    
    /**
     * Removes an object from the cache
     * @param key
     */
    public abstract void removeKey(String key);
}
