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

import java.util.Date;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 */
final class CacheEntry {

    private final Object object;
    private final long timeOfCreation;
    private final long durability;
    
    
    /**
     * Creates a new instance of CacheEntry
     * @param object
     * @param timeOfCreation
     * @param durability
     */
    public CacheEntry(Object object, long durability) {
        super();
        this.object = object;
        this.timeOfCreation = new Date().getTime();
        this.durability = durability;
    }
    
    public Object getObject() {
        return this.object;
    }

    public long getTimeOfCreation() {
        return this.timeOfCreation;
    }
    
    public long getDurability() {
        return this.durability;
    }
    
    /**
     * Determines if the entry is not expired
     * @return
     */
    public boolean isValid() {
        if (this.durability == 0) {
            return true;
        }
        
        Date now = new Date();
        long timePastSinceCreation = (now.getTime() - this.timeOfCreation);
      
        return (timePastSinceCreation <= this.durability);
    }
}
