package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.impl.DossierImpl;
import org.opencps.dossiermgt.service.persistence.DossierFinder;
import org.opencps.dossiermgt.service.persistence.NotarizationFinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DossierFinderImpl extends DossierFinderBaseImpl implements DossierFinder
{
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL           _customSQL;
	private static final String DOSSIER_ENTITY = "Dossier";
	public static final String FIND_DOSSIER_BY_DAY = DossierFinder.class.getName() + ".findDossierByDay";

	@Override
	public List<Dossier> findDossierByDay(String date)
	{
		if (Validator.isNull(date))
		{
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date =format.format(now);
		}

		Session session = null;
		try
		{
			session = openSession();

			String sql = _customSQL.get(getClass(),FIND_DOSSIER_BY_DAY);
			SQLQuery query = session.createSQLQuery(sql);
			query.setCacheable(false);
			query.addEntity(DOSSIER_ENTITY,DossierImpl.class);
			QueryPos queryPos = QueryPos.getInstance(query);

			queryPos.add(date);
			return (List<Dossier>) QueryUtil.list(query,getDialect(),0,Integer.MAX_VALUE);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return null;
	}
}
