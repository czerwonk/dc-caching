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
