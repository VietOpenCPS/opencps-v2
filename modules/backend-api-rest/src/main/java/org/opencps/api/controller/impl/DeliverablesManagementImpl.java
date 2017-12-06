package org.opencps.api.controller.impl;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.opencps.api.controller.DeliverablesManagement;

public class DeliverablesManagementImpl implements DeliverablesManagement {

	@Override
	public Response getDeliverables(String state, String agency, String keyword, String type, String applicant,
			String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response insertDeliverables(String deliverableType, String deliverableCode, String govAgencyCode,
			String applicantIdNo, String applicantName, String subject, Date issueDate, Date expireDate,
			Date revalidate, Integer deliverableState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDeliverablesDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateDeliverables(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteDeliverables(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getFormData(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateFormData(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getFormScript(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getPreview(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDeliverableAction(String id, String deliverableAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDeliverableLog(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
