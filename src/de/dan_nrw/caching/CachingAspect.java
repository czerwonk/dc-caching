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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * @author Daniel Czerwonk
 *
 */
@Aspect
public class CachingAspect {

	@Pointcut("execution(@Cachable * *(..))")
	void cachablePointcut() {
		
	}
	
	@Around("cachablePointcut()")
	public Object aroundCachable(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Cachable cachableAnnotation = (Cachable)methodSignature.getMethod().getAnnotation(Cachable.class);
		Cache cache = Cache.getCurrent();
		
		if (cache.containsKey(cachableAnnotation.key())) {
			// if key is already in cache
			return cache.get(cachableAnnotation.key());
		}

		// determinig data
		Object data = joinPoint.proceed();
		
		// cache is filled
		if (data != null) {
			cache.put(cachableAnnotation.key(), data);	
		}
		
		// returning result
		return data;
	}
}
