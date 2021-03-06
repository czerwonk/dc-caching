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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import de.dan_nrw.caching.Cache;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 */
@Aspect
public class TestAspect {

    @Pointcut("execution(@org.junit.Test * *(..))")
    void testPointcut() {
        
    }
    
    @Before("testPointcut()")
    public void clearCacheBeforeExecutingTest(final JoinPoint joinPoint) {
        Cache.getCurrent().clear();
    }
}
