package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.service.persistence.DeliverableFinder;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class DeliverableFinderImpl extends DeliverableFinderBaseImpl
		 implements DeliverableFinder {

	Log _log = LogFactoryUtil.getLog(DeliverableFinderImpl.class);

	private static final String SEARCH_FILE_TEMPLATE_NO = DeliverableFinder.class.getName()
			+ ".findDossierFileSearch";

	private static final String CONDITION_DOSSIER_ID = "(opencps_dossierfile.dossierId = ?) AND";
	private static final String CONDITION_FILE_TEMPLATE_NO = "(opencps_dossierfile.fileTemplateNo = ?) AND";

	public DossierFile findFileTemplateNo(long id, String fileTemplateNo) {

		Session session = null;

		try {
			session = openSession();

			_log.info("SEARCH_FILE_TEMPLATE_NO: "+SEARCH_FILE_TEMPLATE_NO);
			String sql = _customSQL.get(getClass(), SEARCH_FILE_TEMPLATE_NO);

			if (id == 0) {
				sql = StringUtil.replace(sql, CONDITION_DOSSIER_ID, StringPool.BLANK);
			}
			if (Validator.isNull(fileTemplateNo)) {
				sql = StringUtil.replace(sql, CONDITION_FILE_TEMPLATE_NO, StringPool.BLANK);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addEntity("DossierFile", DossierFileImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add dossierId parameter */
			if (id > 0) {
				qPos.add(id);
			}

			/* add fileTemplateNo parameter */
			if (Validator.isNotNull(fileTemplateNo)) {
				qPos.add(fileTemplateNo);
			}

			List<DossierFile> ls = (List<DossierFile>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			if (ls.size() > 0) {
				return ls.get(0);
			} 


		} catch (Exception e) {
			_log.error(e);
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}
