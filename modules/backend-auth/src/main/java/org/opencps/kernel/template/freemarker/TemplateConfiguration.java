
package org.opencps.kernel.template.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * @author trungnt
 */
public class TemplateConfiguration {

	public TemplateConfiguration() {
		init();
	}

	private void init() {
		Configuration configuration =
			new Configuration(Configuration.getVersion());
		configuration.setDefaultEncoding("UTF-8");
		configuration.setObjectWrapper(
			new DefaultObjectWrapper(
				configuration.getIncompatibleImprovements()));
		
		this.setConfiguration(configuration);
	}

	public Configuration getConfiguration() {

		return _configuration;
	}

	public void setConfiguration(Configuration configuration) {

		this._configuration = configuration;
	}

	private Configuration _configuration;
}
