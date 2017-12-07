package org.opencps.api.controller.impl;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DeliverablesManagement;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;

import com.liferay.portal.kernel.json.JSONObject;

public class DeliverablesManagementImpl implements DeliverablesManagement {

	@Override
	public Response getDeliverables(String state, String agency, String type, String applicant) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			JSONObject result = action.getListDeliverable(state, agency, type, applicant);
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}

	}

	@Override
	public Response insertDeliverables(String deliverableType, String deliverableCode, String govAgencyCode,
			String applicantIdNo, String applicantName, String subject, Date issueDate, Date expireDate,
			Date revalidate, Integer deliverableState) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			DeliverableImpl model = new DeliverableImpl();
			model.setDeliverableType(deliverableType);
			model.setDeliverableCode(deliverableCode);
			model.setGovAgencyCode(govAgencyCode);
			model.setApplicantIdNo(applicantIdNo);
			model.setApplicantName(applicantName);
			model.setSubject(subject);
			model.setIssueDate(issueDate);
			model.setExpireDate(expireDate);
			model.setRevalidate(revalidate);
			model.setDeliverableState(String.valueOf(deliverableState));
			action.addDeliverable(model);
			return Response.status(200).entity("Success").build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}

	}

	@Override
	public Response getDeliverablesDetail(Long id) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			Deliverable deliverable = action.getListDeliverableDetail(id);
			return Response.status(200).entity(deliverable).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

	@Override
	public Response deleteDeliverables(Long id) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			action.deleteDeliverable(id);
			return Response.status(200).entity("Success").build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

	@Override
	public Response getFormData(Long id) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			Deliverable deliverable = action.getListDeliverableDetail(id);
			return Response.status(200).entity(deliverable.getFormData()).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

	@Override
	public Response updateFormData(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getFormScript(Long id) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			Deliverable deliverable = action.getListDeliverableDetail(id);
			return Response.status(200).entity(deliverable.getFormScript()).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

	@Override
	public Response getPreview(Long id) {
		// TODO
		return null;
	}

	@Override
	public Response getDeliverableAction(Long id, String deliverableAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDeliverableLog(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateDeliverables(Long id, String deliverableType, String deliverableCode, String govAgencyCode,
			String applicantIdNo, String applicantName, String subject, Date issueDate, Date expireDate,
			Date revalidate, Integer deliverableState) {
		try {
			DeliverableActions action = new DeliverableActionsImpl();
			DeliverableImpl model = new DeliverableImpl();
			model.setDeliverableId(id);
			model.setDeliverableType(deliverableType);
			model.setDeliverableCode(deliverableCode);
			model.setGovAgencyCode(govAgencyCode);
			model.setApplicantIdNo(applicantIdNo);
			model.setApplicantName(applicantName);
			model.setSubject(subject);
			model.setIssueDate(issueDate);
			model.setExpireDate(expireDate);
			model.setRevalidate(revalidate);
			model.setDeliverableState(String.valueOf(deliverableState));
			action.updateDeliverable(model);
			return Response.status(200).entity("Success").build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}

	}

}
