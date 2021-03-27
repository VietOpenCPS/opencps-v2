package org.opencps.bundlemgt.classloader;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author trungnt
 */
public class JasperClassLoader implements BaseModelClassLoader {

	public JasperClassLoader() {

		BundleLoader bundleLoader = new BundleLoader("backend.jasper.process.service");

		this.setBundleLoader(bundleLoader);
	}

	@Override
	public Class<?> getClassLoader(String name) throws ClassNotFoundException {

		return getBundleLoader().getClassLoader().loadClass(name);
	}

	@Override
	public Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
			throws NoSuchMethodException, SecurityException {

		return clazz.getMethod(name, parameterTypes);
	}

	public Method getCreateReportFileMethod() throws NoSuchMethodException, SecurityException, ClassNotFoundException {

		return getBusinessService().getMethod(_createReportFile, String.class, String.class, Map.class, String.class);
	}

	public Method getCreateReportFileByJrxmlTemplateNoMethod()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException {

		return getBusinessService().getMethod(_createReportFileByJrxmlTemplateNo, String.class, String.class, Map.class,
				String.class, String.class);
	}

	public Class<?> getBusinessService() throws ClassNotFoundException {

		return getBundleLoader().getClassLoader().loadClass(_businessServiceClassName);
	}

	public BundleLoader getBundleLoader() {

		return _bundleLoader;
	}

	public void setBundleLoader(BundleLoader bundleLoader) {

		this._bundleLoader = bundleLoader;
	}

	private BundleLoader _bundleLoader;

	private static final String _businessServiceClassName = "org.opencps.jasper.utils.JRReportUtil";

	private static final String _createReportFile = "createReportFile";

	private static final String _createReportFileByJrxmlTemplateNo = "createReportFileByJrxmlTemplateNo";

}
