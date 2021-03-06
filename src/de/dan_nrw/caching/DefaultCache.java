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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 */
final class DefaultCache extends Cache {
	
	private static Map<String, CacheEntry> internalCache = new Hashtable<String, CacheEntry>();
	
	
	/* (non-Javadoc)
     * @see de.dan_nrw.caching.Cache#getKeys()
     */
    @Override
    public synchronized Iterable<String> getKeys() {
        List<String> keys = new ArrayList<String>();

        for (Entry<String, CacheEntry> entry : internalCache.entrySet()) {
            if (entry.getValue().isValid()) {
                keys.add(entry.getKey());
            }
        }
        
        return keys;
    }

    /* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public synchronized void put(String key, Object value, long durability) {
		internalCache.put(key, new CacheEntry(value, durability));
	}
	
	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#get(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public synchronized <T> T get(String key) throws IllegalArgumentException, CachedDataExpiredException {
	    if (!internalCache.containsKey(key)) {
	        throw new IllegalArgumentException("key not found!");
	    }
	    
	    CacheEntry cacheEntry = internalCache.get(key);
	    
	    if (!cacheEntry.isValid()) {
	        throw new CachedDataExpiredException();
	    }
	    
		return (T)cacheEntry.getObject();
	}
	
	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#containsKey(java.lang.String)
	 */
	@Override
	public synchronized boolean containsKey(String key) {
	    if (internalCache.containsKey(key)) {
	        CacheEntry cacheEntry = internalCache.get(key);
	        return cacheEntry.isValid();
	    }
	    
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#removeKey(java.lang.String)
	 */
	@Override
	public synchronized void removeKey(String key) {
		internalCache.remove(key);
	}

    /* (non-Javadoc)
     * @see de.dan_nrw.caching.Cache#clear()
     */
    @Override
    public synchronized void clear() {
        internalCache.clear();
    }
}