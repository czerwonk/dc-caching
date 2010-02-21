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
package de.dan_nrw.caching.tests;

import de.dan_nrw.caching.Cache;
import de.dan_nrw.caching.CachedDataExpiredException;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 *
 */
public class DefaultCacheTestSuite {

    @Test
    public void put_should_add_object_to_cache() throws CachedDataExpiredException {
        Integer integer = new Integer(397);
        
        Cache cache = Cache.getCurrent();
        cache.put("test1", integer, 0);
        
        assertTrue(cache.get("test1").equals(integer));
    }
    
    @Test
    public void put_should_override_object_formerly_added_to_cache() throws CachedDataExpiredException {
        Integer integer1 = new Integer(397);
        Integer integer2 = new Integer(123);
        
        Cache cache = Cache.getCurrent();
        cache.put("test2", integer1, 0);
        cache.put("test2", integer2, 0);
        
        assertTrue(cache.get("test2").equals(integer2));
    }
    
    @Test(expected = CachedDataExpiredException.class)
    public void get_should_throw_CachedDataExpiredException_if_data_is_expired() throws InterruptedException, 
                                                                                        IllegalArgumentException, 
                                                                                        CachedDataExpiredException {
        Integer integer = new Integer(397);
        
        Cache cache = Cache.getCurrent();
        cache.put("test3", integer, 499);
        
        Thread.sleep(500);
        
        cache.get("test3");
    }
    
    @Test
    public void contains_should_return_true_if_key_exists_in_cache() {
        Cache cache = Cache.getCurrent();
        cache.put("test4", "test", 5000);
        
        assertTrue(cache.containsKey("test4"));
    }
    
    @Test
    public void contains_should_return_false_if_key_is_not_in_cache() {
        assertFalse(Cache.getCurrent().containsKey("test5"));
    }
    
    @Test
    public void contains_should_return_false_if_data_is_expired() throws InterruptedException {
        Integer integer = new Integer(397);
        
        Cache cache = Cache.getCurrent();
        cache.put("test6", integer, 999);
        
        Thread.sleep(1000);
        
        assertFalse(cache.containsKey("test6"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void get_should_throw_IllegalArgumentException_invoked_using_an_invalid_key() throws IllegalArgumentException, CachedDataExpiredException {
        Cache.getCurrent().get("test7");
    }
    
    @Test
    public void removeKey_should_remove_object_from_cache() {
        Cache cache = Cache.getCurrent();
        cache.put("test8", new Integer(397), 0);
        cache.removeKey("test8");
        
        assertThat(cache.getKeys(), not(hasItem("test8")));
    }
}
