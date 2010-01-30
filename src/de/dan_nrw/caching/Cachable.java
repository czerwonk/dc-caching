package de.dan_nrw.caching;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Daniel Czerwonk <d.czerwonk@googlemail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cachable {

	/**
	 * Key used in cache
	 * @return
	 */
	public String key();
}
