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