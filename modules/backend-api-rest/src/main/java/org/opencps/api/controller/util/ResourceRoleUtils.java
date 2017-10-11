package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.resourcerole.model.ResourceRoleModel;
import org.opencps.usermgt.constants.ResourceRoleTerm;
import org.opencps.usermgt.model.ResourceRole;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

public class ResourceRoleUtils {

	public static List<ResourceRoleModel> mapperResourceRoleList(List<Document> listDocument) {

		List<ResourceRoleModel> results = new ArrayList<>();

		try {

			ResourceRoleModel ett = null;

			for (Document document : listDocument) {
				ett = new ResourceRoleModel();

				ett.setRoleId(Long.valueOf(document.get(ResourceRoleTerm.ROLE_ID)));
				ett.setRoleName(document.get(ResourceRoleTerm.ROLE_NAME));
				ett.setSelected(Boolean.valueOf(document.get("selected")));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static ResourceRoleModel mapperResourceRoleModel(ResourceRole resourceRole) {

		ResourceRoleModel ett = new ResourceRoleModel();

		try {

			ett.setRoleId(resourceRole.getRoleId());

			Role role = RoleLocalServiceUtil.fetchRole(resourceRole.getRoleId());

			ett.setRoleName(role.getName());

			ett.setSelected(true);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(ResourceRoleUtils.class);
}
