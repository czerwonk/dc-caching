package de.dan_nrw.caching;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author Daniel Czerwonk
 */
final class DefaultCache extends Cache {
	
	private static Map<String, Object> internalCache = new Hashtable<String, Object>();
	

	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, Object value) {
		internalCache.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#get(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T> T get(String key) {
		return (T)internalCache.get(key);
	}
	
	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#containsKey(java.lang.String)
	 */
	@Override
	public boolean containsKey(String key) {
		return internalCache.containsKey(key);
	}
	
	/* (non-Javadoc)
	 * @see de.dan_nrw.caching.Cache#removeKey(java.lang.String)
	 */
	@Override
	public void removeKey(String key) {
		internalCache.remove(key);
	}
}