package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import org.opencps.usermgt.model.Employee;


//@Component(immediate = true, service = ModelListener.class)
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
//			model.setFullName(HtmlUtil.escape(model.getFullName()));
//			model.setEmployeeNo(HtmlUtil.escape(model.getEmployeeNo()));
//			model.setTelNo(HtmlUtil.escape(model.getTelNo()));
//			model.setMobile(HtmlUtil.escape(model.getMobile()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setTitle(HtmlUtil.escape(model.getTitle()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(Employee model) throws ModelListenerException {
//		try {
//			model.setFullName(HtmlUtil.escape(model.getFullName()));
//			model.setEmployeeNo(HtmlUtil.escape(model.getEmployeeNo()));
//			model.setTelNo(HtmlUtil.escape(model.getTelNo()));
//			model.setMobile(HtmlUtil.escape(model.getMobile()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setTitle(HtmlUtil.escape(model.getTitle()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	//private Log _log = LogFactoryUtil.getLog(EmployeeTempListener.class.getName());
}
