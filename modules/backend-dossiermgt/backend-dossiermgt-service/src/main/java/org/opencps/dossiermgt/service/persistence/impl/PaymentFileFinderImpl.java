package org.opencps.dossiermgt.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.model.impl.PaymentFileImpl;
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
	
	private static final String SEARCH_PAYMENT_FILE_BY_S_M = PaymentFileFinder.class.getName()
			+ ".findPaymentByS_M";
	private static final String SEARCH_PAYMENT_FILE_BY_G_S_M = PaymentFileFinder.class.getName()
			+ ".findPaymentByG_S_M";
	
	private static final String CONDITION_PAYMENT_METHOD = "(opencps_paymentfile.paymentMethod = ?) AND";
	private static final String CONDITION_PAYMENT_METHOD_DIRECT = "(opencps_paymentfile.paymentMethod IS NULL) AND";
	private static final String CONDITION_PAYMENT_METHOD_ONLINE = "(opencps_paymentfile.paymentMethod IS NOT NULL) AND";
	private static final String DIRECT = "direct";
	private static final String ONLINE = "online";
	private static final String TOTAL = "total";

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentFile> findPaymentByS_M(int paymentStatus, String paymentMethod, int start, int end) {
		Session session = null;
		try
		{
			session = openSession();

			String sql = _customSQL.get(getClass(),SEARCH_PAYMENT_FILE_BY_S_M);
			
			if (paymentMethod.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_PAYMENT_METHOD, StringPool.BLANK);
			}
			
			if (paymentMethod.contentEquals(DIRECT)) {
				sql = StringUtil.replace(sql, CONDITION_PAYMENT_METHOD, CONDITION_PAYMENT_METHOD_DIRECT);
			}
			
			if (paymentMethod.contentEquals(ONLINE)) {
				sql = StringUtil.replace(sql, CONDITION_PAYMENT_METHOD, CONDITION_PAYMENT_METHOD_ONLINE);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			
			q.setCacheable(true);
			q.addEntity("PaymentFile", PaymentFileImpl.class);			

			QueryPos qPos = QueryPos.getInstance(q);

			if (Validator.isNotNull(paymentStatus)) {
				qPos.add(paymentStatus);
			}

			return (List<PaymentFile>) QueryUtil.list(q, getDialect(),QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		}catch (Exception e) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentFile> findPaymentByG_S_M(int paymentStatus, String paymentMethod, long groupId, int start,
			int end) {
		Session session = null;
		try
		{
			session = openSession();

			String sql = _customSQL.get(getClass(),SEARCH_PAYMENT_FILE_BY_G_S_M);
			
			if (paymentMethod.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_PAYMENT_METHOD, StringPool.BLANK);
			}
			
			if (paymentMethod.contentEquals(DIRECT)) {
				sql = StringUtil.replace(sql, CONDITION_PAYMENT_METHOD, CONDITION_PAYMENT_METHOD_DIRECT);
			}
			
			if (paymentMethod.contentEquals(ONLINE)) {
				sql = StringUtil.replace(sql, CONDITION_PAYMENT_METHOD, CONDITION_PAYMENT_METHOD_ONLINE);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			
			q.setCacheable(true);
			q.addEntity("PaymentFile", PaymentFileImpl.class);			

			QueryPos qPos = QueryPos.getInstance(q);

			if (Validator.isNotNull(paymentStatus)) {
				qPos.add(paymentStatus);
			}
			
			qPos.add(groupId);

			return (List<PaymentFile>) QueryUtil.list(q, getDialect(),QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		}catch (Exception e) {
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

	@Override
	public PaymentFile findPaymentFileByDossierId(long groupId, long dossierId) {
		Session session = null;
		PaymentFile paymentFile = null;
		String sql = " SELECT * FROM opencps_paymentfile WHERE"
				+ " dossierId = "+dossierId+" AND"
				+ " groupId = "+groupId+" "
				+ " ORDER BY createDate DESC";
		_log.info("SQL: "+ sql);
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("opencps_paymentfile", PaymentFileImpl.class);
			paymentFile = (PaymentFile) q.uniqueResult();

		}
		catch (Exception e) {
			try {
				throw new SystemException(e);
			}
			catch (SystemException se) {
				_log.error(se);
			}
		}
		finally {
			closeSession(session);
		}

		return paymentFile;
	}
}