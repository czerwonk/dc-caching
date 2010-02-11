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
public class CachedDataExpiredException extends Exception {

    private static final long serialVersionUID = 1746928408134431811L;
    
    
    /**
     * Creates a new instance of CachedObjectExpiredException
     */
    CachedDataExpiredException() {
        super();
    }

    /**
     * Creates a new instance of CachedObjectExpiredException
     * @param message
     * @param cause
     */
    private CachedDataExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance of CachedObjectExpiredException
     * @param message
     */
    private CachedDataExpiredException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of CachedObjectExpiredException
     * @param cause
     */
    private CachedDataExpiredException(Throwable cause) {
        super(cause);
    }
}
