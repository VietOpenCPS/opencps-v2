package org.opencps.cache.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

@FunctionalInterface
public interface PortalFunction<K extends Serializable, V extends Serializable> {

   /**
    * Executes the function on the given argument
    * @param key, argument to the function
    * @return the function result V
    * @throws PortalException
    */
    V execute(K key) throws PortalException;
}
