
package org.opencps.bundlemgt.classloader;

import java.lang.reflect.Method;

/**
 * @author trungnt
 */
public interface BaseModelClassLoader {

	Class<?> getClassLoader(String name)
		throws ClassNotFoundException;

	Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
		throws NoSuchMethodException, SecurityException;

}
