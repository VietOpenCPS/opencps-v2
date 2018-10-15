package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ModelListener.class)
public class EmployeeTempListener extends BaseModelListener<Employee> {

	@Override
	public void onAfterCreate(Employee model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(Employee model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(Employee model) throws ModelListenerException {
//		try {
//			model.setFullName(StringEscapeUtils.escapeHtml4(model.getFullName()));
//			model.setEmployeeNo(StringEscapeUtils.escapeHtml4(model.getEmployeeNo()));
//			model.setTelNo(StringEscapeUtils.escapeHtml4(model.getTelNo()));
//			model.setMobile(StringEscapeUtils.escapeHtml4(model.getMobile()));
//			model.setEmail(StringEscapeUtils.escapeHtml4(model.getEmail()));
//			model.setTitle(StringEscapeUtils.escapeHtml4(model.getTitle()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(Employee model) throws ModelListenerException {
//		try {
//			model.setFullName(StringEscapeUtils.escapeHtml4(model.getFullName()));
//			model.setEmployeeNo(StringEscapeUtils.escapeHtml4(model.getEmployeeNo()));
//			model.setTelNo(StringEscapeUtils.escapeHtml4(model.getTelNo()));
//			model.setMobile(StringEscapeUtils.escapeHtml4(model.getMobile()));
//			model.setEmail(StringEscapeUtils.escapeHtml4(model.getEmail()));
//			model.setTitle(StringEscapeUtils.escapeHtml4(model.getTitle()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(EmployeeTempListener.class.getName());
}
