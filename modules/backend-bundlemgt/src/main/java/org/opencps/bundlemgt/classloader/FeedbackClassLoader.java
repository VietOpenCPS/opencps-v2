package org.opencps.bundlemgt.classloader;

import java.lang.reflect.Method;

public class FeedbackClassLoader implements BaseModelClassLoader {
    private BundleLoader _bundleLoader;

    private static final String _businessServiceClassName = "backend.feedback.service.CommentLocalServiceUtil";

    private static final String _getComments = "findByF_groupId_className_classPK";

    public FeedbackClassLoader() {

        BundleLoader bundleLoader = new BundleLoader("backend.feedback.api");

        this.setBundleLoader(bundleLoader);
    }

    public void setBundleLoader(BundleLoader bundleLoader) {

        this._bundleLoader = bundleLoader;
    }

    public Method getComments() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        //get comments
        return getBusinessService().getMethod(_getComments, long.class, String.class, String.class);
    }

    public Class<?> getBusinessService() throws ClassNotFoundException {

        return getBundleLoader().getClassLoader().loadClass(_businessServiceClassName);
    }

    @Override
    public Class<?> getClassLoader(String name) throws ClassNotFoundException {
        return getBundleLoader().getClassLoader().loadClass(name);
    }

    public BundleLoader getBundleLoader() {
        return _bundleLoader;
    }

    @Override
    public Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {

        return clazz.getMethod(name, parameterTypes);
    }
}
