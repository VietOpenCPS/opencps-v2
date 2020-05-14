package org.opencps.usermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.model.impl.TrackClientImpl;
import org.opencps.usermgt.service.persistence.TrackClientFinder;


import java.util.List;

public class TrackClientFinderImpl extends TrackClientFinderBaseImpl implements TrackClientFinder
{
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;


	@Override
	public List<TrackClient> findPreviousPage(String sessionId)
	{
		Session session ;
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

			return (List<TrackClient>) QueryUtil.list(query,getDialect(),0,10000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Object[]> getOnline()
	{
		Session session ;
		try
		{
			int reducedTime= ConfigConstants.OPENCPS_DEFAULT_TIME_ONLINE_REDUCED;
			session = openSession();
			String SQL = _customSQL
				.get(getClass(),TrackClientFinder.class.getName() + ".getOnline");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);

			query.addScalar("user",Type.INTEGER);
			query.addScalar("guest", Type.INTEGER);

			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(reducedTime);
			queryPos.add(reducedTime);

			return (List<Object[]>) query.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Object[]> getTopURLUserAccess(long userId)
	{
		Session session;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientFinder.class.getName() + ".getTopURLUserAccess");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);

			query.addScalar("url",Type.STRING);
			query.addScalar("count",Type.INTEGER);

			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(userId);

			return (List<Object[]>) query.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
