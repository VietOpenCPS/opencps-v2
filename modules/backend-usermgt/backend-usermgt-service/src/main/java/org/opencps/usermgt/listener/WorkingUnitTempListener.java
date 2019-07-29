package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ModelListener.class)
public class WorkingUnitTempListener extends BaseModelListener<WorkingUnit> {

	@Override
	public void onAfterCreate(WorkingUnit model) throws ModelListenerException {

		try {
			long companyId = model.getCompanyId();
			long userId = model.getUserId();
			String userName = model.getUserName();
			String name = model.getName();
			String enName = model.getEnName();
			String govAgencyCode = model.getGovAgencyCode();
			long parentWorkingUnitId = model.getParentWorkingUnitId();
			String sibling = model.getSibling();
			String treeIndex = model.getTreeIndex();
			int level = model.getLevel();
			String address = model.getAddress();
			String telNo = model.getTelNo();
			String faxNo = model.getFaxNo();
			String email = model.getEmail();
			String website = model.getWebsite();
			Date ceremonyDate = model.getCeremonyDate();
			ServiceContext serviceContext = new ServiceContext();

			WorkingUnitLocalServiceUtil.addWorkingUnitPublish(userId, 0l, companyId, userName, name, enName, govAgencyCode,
					parentWorkingUnitId, sibling, treeIndex, level, address, telNo, faxNo, email, website, ceremonyDate,
					serviceContext);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onAfterUpdate(WorkingUnit model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(WorkingUnit model) throws ModelListenerException {
//		try {
//			model.setName(HtmlUtil.escape(model.getName()));
//			model.setEnName(HtmlUtil.escape(model.getEnName()));
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setAddress(HtmlUtil.escape(model.getAddress()));
//			model.setTelNo(HtmlUtil.escape(model.getTelNo()));
//			model.setFaxNo(HtmlUtil.escape(model.getFaxNo()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setWebsite(HtmlUtil.escape(model.getWebsite()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(WorkingUnit model) throws ModelListenerException {
//		try {
//			model.setName(HtmlUtil.escape(model.getName()));
//			model.setEnName(HtmlUtil.escape(model.getEnName()));
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setAddress(HtmlUtil.escape(model.getAddress()));
//			model.setTelNo(HtmlUtil.escape(model.getTelNo()));
//			model.setFaxNo(HtmlUtil.escape(model.getFaxNo()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setWebsite(HtmlUtil.escape(model.getWebsite()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	private Log _log = LogFactoryUtil.getLog(WorkingUnitTempListener.class.getName());
}
