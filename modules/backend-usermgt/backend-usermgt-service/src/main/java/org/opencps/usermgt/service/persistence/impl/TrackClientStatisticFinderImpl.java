package org.opencps.usermgt.service.persistence.impl;


import java.util.List;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.opencps.usermgt.model.impl.TrackClientStatisticImpl;
import org.opencps.usermgt.service.persistence.TrackClientStatisticFinder;

public class TrackClientStatisticFinderImpl extends TrackClientStatisticFinderBaseImpl implements TrackClientStatisticFinder
{
	@Override
	public List<org.opencps.usermgt.model.TrackClientStatistic> findPeriod(String startDay, String endDay)
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientStatisticFinder.class.getName()+".findPeriod");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClientStatistic",TrackClientStatisticImpl.class);
			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(startDay);
			queryPos.add(endDay);

			return (List<org.opencps.usermgt.model.TrackClientStatistic>) QueryUtil.list(query,getDialect(),0,10000);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<org.opencps.usermgt.model.TrackClientStatistic> findAllYear()
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientStatisticFinder.class.getName()+".findAllYear");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClientStatistic",TrackClientStatisticImpl.class);

			return (List<org.opencps.usermgt.model.TrackClientStatistic>) QueryUtil.list(query,getDialect(),0,10000);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<org.opencps.usermgt.model.TrackClientStatistic> findURLAllYear()
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientStatisticFinder.class.getName()+".findURLAllYear");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClientStatistic",TrackClientStatisticImpl.class);

			return (List<org.opencps.usermgt.model.TrackClientStatistic>) QueryUtil.list(query,getDialect(),0,10000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}
	@Override
	public List<org.opencps.usermgt.model.TrackClientStatistic> findAccessURLAllYear(String url)
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientStatisticFinder.class.getName()+".findAccessURLAllYear");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClientStatistic",TrackClientStatisticImpl.class);
			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(url);

			return (List<org.opencps.usermgt.model.TrackClientStatistic>) QueryUtil.list(query,getDialect(),0,10000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<org.opencps.usermgt.model.TrackClientStatistic> findURLPeriod(String startDay, String endDay)
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientStatisticFinder.class.getName()+".findURLPeriod");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClientStatistic",TrackClientStatisticImpl.class);
			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(startDay);
			queryPos.add(endDay);

			return (List<org.opencps.usermgt.model.TrackClientStatistic>) QueryUtil.list(query,getDialect(),0,10000);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}
	@Override
	public List<org.opencps.usermgt.model.TrackClientStatistic> findAccessURLPeriod(String startDay, String endDay,String url)
	{
		Session session = null;
		try
		{
			session = openSession();
			String SQL = _customSQL.get(getClass(),TrackClientStatisticFinder.class.getName()+".findAccessURLPeriod");
			SQLQuery query = session.createSQLQuery(SQL);
			query.setCacheable(false);
			query.addEntity("TrackClientStatistic",TrackClientStatisticImpl.class);
			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(startDay);
			queryPos.add(endDay);
			queryPos.add(url);
			return (List<org.opencps.usermgt.model.TrackClientStatistic>) QueryUtil.list(query,getDialect(),0,10000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;


}
