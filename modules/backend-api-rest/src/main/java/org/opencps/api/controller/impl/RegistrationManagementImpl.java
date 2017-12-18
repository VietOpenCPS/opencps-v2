package org.opencps.api.controller.impl;

import javax.ws.rs.core.Response;

import org.opencps.api.controller.RegistrationManagement;
import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.impl.RegistrationActionsImpl;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.impl.RegistrationImpl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class RegistrationManagementImpl implements RegistrationManagement {
	Log _log = LogFactoryUtil.getLog(RegistrationManagementImpl.class);

	@Override
	public Response getList(String stage, String agency, String keyword, String owner, String sort, String submitting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response add(Registration model) {
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			action.insert(model);
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity("Success").build();
	}

	@Override
	public Response getDetail(Long id) {
		Registration detail = null;
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			detail = action.getDetail(id);
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity(detail).build();
	}

	@Override
	public Response update(Registration model, Long id) {
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			action.update(model);
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity("Success").build();
	}

	@Override
	public Response delete(Long id) {
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			action.delete(id);
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity("Success").build();
	}

}
