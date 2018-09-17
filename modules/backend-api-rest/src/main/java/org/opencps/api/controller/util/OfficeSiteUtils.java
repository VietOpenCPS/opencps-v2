package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.officesite.model.AdminUser;
import org.opencps.api.officesite.model.OfficeSiteModel;
import org.opencps.api.officesite.model.SiteGroup;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.constants.LocationTerm;
import org.opencps.usermgt.constants.OfficeSiteTerm;
import org.opencps.usermgt.model.OfficeSite;

public class OfficeSiteUtils {

	public static List<OfficeSiteModel> mapperOfficeSiteList(List<Document> listDocument) {

		List<OfficeSiteModel> results = new ArrayList<>();

		try {

			OfficeSiteModel ett = null;

			for (Document document : listDocument) {
				ett = new OfficeSiteModel();

				ett.setOfficeSiteId(Long.valueOf(document.get("entryClassPK")));
				ett.setCreateDate(Validator.isNotNull(document.getDate(LocationTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(LocationTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				ett.setName(document.get(OfficeSiteTerm.NAME));
				ett.setEnName(document.get(OfficeSiteTerm.EN_NAME));
				ett.setGovAgencyCode(document.get(OfficeSiteTerm.GOV_AGENCY_CODE));
				ett.setAddress(document.get(OfficeSiteTerm.ADDRESS));
				ett.setTelNo(document.get(OfficeSiteTerm.TEL_NO));
				ett.setFaxNo(document.get(OfficeSiteTerm.FAX_NO));
				ett.setEmail(document.get(OfficeSiteTerm.EMAIL));
				ett.setWebsite(document.get(OfficeSiteTerm.WEBSITE));
				
				SiteGroup siteGroup = new SiteGroup();
				
				Group group = GroupLocalServiceUtil.fetchGroup(Long.valueOf(document.get(OfficeSiteTerm.GROUP_ID)));
				
				siteGroup.setGroupId(Long.valueOf(document.get(OfficeSiteTerm.GROUP_ID)));
				siteGroup.setGroupName(group.getNameCurrentValue());
				
				ett.getSiteGroup().add(siteGroup);
				
				AdminUser adminUser = new AdminUser();
				
				adminUser.setUserId(Long.valueOf(document.get(OfficeSiteTerm.ADMIN_USER_ID)));
				adminUser.setScreenName(document.get("adminUser_screenName"));
				adminUser.setEmail(document.get("adminUser_email"));
				
				ett.getAdminUser().add(adminUser);
				
				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static OfficeSiteModel mapperOfficeSiteModel(OfficeSite officeSite) {

		OfficeSiteModel ett = new OfficeSiteModel();

		try {

			ett.setOfficeSiteId(officeSite.getOfficeSiteId());
			ett.setCreateDate(Validator.isNotNull(officeSite.getCreateDate()) ? APIDateTimeUtils
					.convertDateToString(officeSite.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setModifiedDate(
					Validator.isNotNull(officeSite.getModifiedDate()) ? APIDateTimeUtils.convertDateToString(
							officeSite.getModifiedDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

			ett.setName(officeSite.getName());
			ett.setEnName(officeSite.getEnName());
			ett.setGovAgencyCode(officeSite.getGovAgencyCode());
			ett.setAddress(officeSite.getAddress());
			ett.setTelNo(officeSite.getTelNo());
			ett.setFaxNo(officeSite.getFaxNo());
			ett.setEmail(officeSite.getEmail());
			ett.setWebsite(officeSite.getWebsite());
			
			SiteGroup siteGroup = new SiteGroup();
			
			Group group = GroupLocalServiceUtil.fetchGroup(officeSite.getGroupId());
			
			siteGroup.setGroupId(officeSite.getGroupId());
			siteGroup.setGroupName(group.getNameCurrentValue());
			
			ett.getSiteGroup().add(siteGroup);
			
			AdminUser adminUser = new AdminUser();
			
			adminUser.setUserId(officeSite.getAdminUserId());
			
			User user = UserLocalServiceUtil.fetchUser(officeSite.getAdminUserId());
			
			adminUser.setScreenName(user.getScreenName());
			adminUser.setEmail(user.getEmailAddress());
			
			ett.getAdminUser().add(adminUser);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(OfficeSiteUtils.class);
}
