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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.mockito.Mockito.*;

import de.dan_nrw.caching.Cachable;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 */
public class CachingAspectTests {

    @Test
    public void data_retrieving_method_should_use_cache_at_second_call_for_integer() {
        IDataRetriever<Integer> dataRetriever = mock(IIntegerDataRetriever.class);
        when(dataRetriever.getData()).thenReturn(10);
        ClassUnderTest<Integer> test = new ClassUnderTest<Integer>(dataRetriever);
        
        assertThat(10, equalTo(test.getDataFromDataRetrieverOrCache().intValue()));
        assertThat(10, equalTo(test.getDataFromDataRetrieverOrCache().intValue()));
        
        verify(dataRetriever, times(1)).getData();
    }
    
    @Test
    public void data_retrieving_method_should_use_cache_at_second_call_for_string() {
        IDataRetriever<String> dataRetriever = mock(IStringDataRetriever.class);
        when(dataRetriever.getData()).thenReturn("test");
        ClassUnderTest<String> test = new ClassUnderTest<String>(dataRetriever);
        
        assertThat("test", equalTo(test.getDataFromDataRetrieverOrCache()));
        assertThat("test", equalTo(test.getDataFromDataRetrieverOrCache()));
        
        verify(dataRetriever, times(1)).getData();
    }
    
    @Test
    public void data_retrieving_method_should_use_cache_at_second_call_for_list_of_string() {
        final List<String> list = new ArrayList<String>();
        list.add("This");
        list.add("is a ");
        list.add("test");
        
        IDataRetriever<List<String>> dataRetriever = mock(IStringListDataRetriever.class);
        when(dataRetriever.getData()).thenReturn(list);
        ClassUnderTest<List<String>> test = new ClassUnderTest<List<String>>(dataRetriever);
        
        assertThat(list, equalTo(test.getDataFromDataRetrieverOrCache()));
        assertThat(list, equalTo(test.getDataFromDataRetrieverOrCache()));
        
        verify(dataRetriever, times(1)).getData();
    }
    
    
    private static class ClassUnderTest<T> {
        
        private final IDataRetriever<T> dataRetriever;
        
        public ClassUnderTest(IDataRetriever<T> dataRetriever) {
            this.dataRetriever = dataRetriever;
        }
        
        @Cachable(key = "foo")
        public T getDataFromDataRetrieverOrCache() {
            return this.dataRetriever.getData();
        }
    }
    
    private interface IDataRetriever<T> {
        T getData();
    }
    
    private interface IIntegerDataRetriever extends IDataRetriever<Integer> {
        
    }
    
    private interface IStringDataRetriever extends IDataRetriever<String> {
        
    }
    
    private interface IStringListDataRetriever extends IDataRetriever<List<String>> {
        
    }
}