package org.opencps.usermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.model.impl.TrackClientImpl;
import org.opencps.usermgt.model.impl.TrackClientStatisticImpl;
import org.opencps.usermgt.service.persistence.TrackClientFinder;
import org.opencps.usermgt.service.persistence.TrackClientStatisticFinder;

import java.util.List;

public class TrackClientFinderImpl extends TrackClientFinderBaseImpl implements TrackClientFinder
{
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;


	@Override
	public List<TrackClient> findPreviousPage(String sessionId)
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientFinder.class.getName()+".findPreviousPage");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClient",TrackClientImpl.class);
			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(sessionId);
			queryPos.add(sessionId);

			return (List<org.opencps.usermgt.model.TrackClient>) QueryUtil.list(query,getDialect(),0,10000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List getOnline()
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL
				.get(getClass(),TrackClientFinder.class.getName() + ".getOnline");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);

			query.addScalar("user",Type.INTEGER);
			query.addScalar("guest", Type.INTEGER);

			return (List<Object[]>) query.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

}
