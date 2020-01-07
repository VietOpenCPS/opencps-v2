package opencps.lang.portlet;

import java.util.Enumeration;
import java.util.ResourceBundle;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.language.UTF8Control;

@Component(property = { "language.id=vi_VN" }, service = ResourceBundle.class)
public class MyViVNResourceBundle extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		return _resourceBundle.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return _resourceBundle.getKeys();
	}

	private final ResourceBundle _resourceBundle = ResourceBundle.getBundle("content.Language_vi_VN",
			UTF8Control.INSTANCE);

}
