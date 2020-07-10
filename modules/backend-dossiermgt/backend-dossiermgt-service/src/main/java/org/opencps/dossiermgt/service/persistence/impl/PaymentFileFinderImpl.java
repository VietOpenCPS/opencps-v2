package org.opencps.dossiermgt.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.opencps.dossiermgt.service.persistence.PaymentFileFinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaymentFileFinderImpl extends PaymentFileFinderBaseImpl implements PaymentFileFinder
{
	Log _log = LogFactoryUtil.getLog(PaymentFileFinderImpl.class);

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

	public static final String FILTER_GROUP_ID = "[$FILTER_GROUP_ID$]";

	private static final String FILTER_GROUP_ID_VALUE = "AND groupId = ?";

	public static final String FILTER_DATE = "[$DATE$]";

	public String findSumPaymentAmountDay(long groupId,String date,int start,int end)
	{
		Session session = null;
		try
		{
			session = openSession();

			String sql = _customSQL.get(getClass(),PaymentFileFinder.class.getName() + ".findSumPaymentAmountDay");
			sql = sql.replace(FILTER_DATE,"?");
			if (groupId > 0)
				sql = sql.replace(FILTER_GROUP_ID,FILTER_GROUP_ID_VALUE);
			else
				sql = sql.replace(FILTER_GROUP_ID,StringPool.BLANK);

			_log.debug("sql: " + sql);
			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addScalar("sumPaymentAmount",Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);
			if (groupId > 0)
			{
				qPos.add(groupId);
			}
			if (Validator.isNotNull(date))
				qPos.add(date);
			else
			{
				Date now = new Date();
				String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
				qPos.add(dateNow);
			}
			List<Object> list = (List<Object>) q.list();
			_log.debug("result: " +list);
			if (!list.isEmpty() && list.size()==1 && Validator.isNotNull(String.valueOf(list.get(0))))
			{
				Object o =list.get(0);
				return String.valueOf(o);
			}
			else
				return "0";
		}
		catch (Exception e)
		{
			try
			{
				throw new SystemException(e);
			}
			catch (SystemException se)
			{
				se.printStackTrace();
			}
		}
		finally
		{
			closeSession(session);
		}

		return "0";
	}
}