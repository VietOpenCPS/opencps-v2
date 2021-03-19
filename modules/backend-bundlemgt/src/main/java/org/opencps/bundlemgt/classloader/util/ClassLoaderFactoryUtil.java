package org.opencps.bundlemgt.classloader.util;

import org.opencps.bundlemgt.classloader.FeedbackClassLoader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.Map;

/**
 * @author trungnt
 */
public class ClassLoaderFactoryUtil {
	private int a = 1;
	private static Log _log = LogFactoryUtil.getLog(ClassLoaderFactoryUtil.class);

	public static Object getListComments(long groupId, String className, String classPK) {
		try {
			FeedbackClassLoader classLoader = new FeedbackClassLoader();
			Object object = classLoader.getComments().invoke(classLoader.getClassLoader(String.class.getName()),
					groupId, className, classPK);
			if (object != null) {
				return object;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
