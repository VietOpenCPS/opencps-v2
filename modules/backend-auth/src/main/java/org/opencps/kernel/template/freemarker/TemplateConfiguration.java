
package org.opencps.kernel.template.freemarker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * @author trungnt
 */
public class TemplateConfiguration {

//	public String process(String name, Object context) throws Exception {
//		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();
//
//		process(name, context, unsyncStringWriter);
//
//		return unsyncStringWriter.toString();
//	}
//
//	public void process(String name, Object context, Writer writer)
//		throws Exception {
//
//		Template template = _getConfiguration().getTemplate(name);
//
//		template.process(context, writer);
//	}
//
//	public Configuration _getConfiguration() {
//		if (_configuration != null) {
//			return _configuration;
//		}
//
//		_configuration = new Configuration(Configuration.getVersion());
//
//		DefaultObjectWrapperBuilder defaultObjectWrapperBuilder =
//			new DefaultObjectWrapperBuilder(Configuration.getVersion());
//
//		_configuration.setObjectWrapper(defaultObjectWrapperBuilder.build());
//
//		_configuration.setTemplateLoader(
//			new ClassTemplateLoader(TemplateConfiguration.class, StringPool.SLASH));
//		_configuration.setTemplateUpdateDelayMilliseconds(Long.MAX_VALUE);
//
//		return _configuration;
//	}
//
//	private static Configuration _configuration;
	public TemplateConfiguration() {
		init();
	}

	private static Log _log = LogFactoryUtil.getLog(TemplateConfiguration.class);
	private void init() {
		try {
			Configuration configuration = new Configuration(Configuration.getVersion());
			configuration.setDefaultEncoding("UTF-8");
			configuration.setObjectWrapper(new DefaultObjectWrapper(configuration.getIncompatibleImprovements()));

			this.setConfiguration(configuration);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	public Configuration getConfiguration() {

		return _configuration;
	}

	public void setConfiguration(Configuration configuration) {

		this._configuration = configuration;
	}

	private Configuration _configuration;
}
