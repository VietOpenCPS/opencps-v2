package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.model.impl.NotarizationImpl;
import org.opencps.dossiermgt.service.persistence.NotarizationFinder;

public class NotarizationFinderImpl extends NotarizationFinderBaseImpl implements NotarizationFinder {
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;
	private static final String NOTARIZATION_ENTITY = "Notarization";
	
	public List<Notarization> findAdvancedSearch(long groupId, long dossierId, String fileName, int totalRecord, int totalPage, int totalCopy, long totalFee, 
			String notarizationNo,
			int notarizationYear,
			String notarizationDate,
			String signerName,
			String signerPosition,
			String statusCode,
			int start, int end) {
		Session session = null;
		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_ADVANCED_SEARCH);
			if (groupId > 0) {
				sql = sql.replace(FILTER_GROUP_ID, FILTER_GROUP_ID_VALUE);
			}
			else {
				sql = sql.replace(FILTER_GROUP_ID, StringPool.BLANK);
			}

			sql = sql.replace(FILTER_DOSSIER_ID, StringPool.BLANK);
			sql = sql.replace(FILTER_FILENAME, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_RECORD, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_PAGE, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_COPY, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_FEE, StringPool.BLANK);
			sql = sql.replace(FILTER_NOTARIZATION_NO, StringPool.BLANK);
			sql = sql.replace(FILTER_NOTARIZATION_YEAR, StringPool.BLANK);
			sql = sql.replace(FILTER_NOTARIZATION_DATE, StringPool.BLANK);
			sql = sql.replace(FILTER_SIGNER_NAME, StringPool.BLANK);
			sql = sql.replace(FILTER_SIGNER_POSITION, StringPool.BLANK);
			sql = sql.replace(FILTER_STATUS_CODE, StringPool.BLANK);
			_log.debug("Search notarization: " + sql);
			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity(NOTARIZATION_ENTITY, NotarizationImpl.class);
			
			QueryPos qPos = QueryPos.getInstance(q);
			if (groupId > 0) {
				qPos.add(groupId);				
			}
			
			return (List<Notarization>)QueryUtil.list(q, getDialect(), start, end);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return null;
	}
	
	public int countAdvancedSearch(long groupId, long dossierId, String fileName, int totalRecord, int totalPage, int totalCopy, long totalFee, 
			String notarizationNo,
			int notarizationYear,
			String notarizationDate,
			String signerName,
			String signerPosition,
			String statusCode) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_ADVANCED_SEARCH);

			if (groupId > 0) {
				sql = sql.replace(FILTER_GROUP_ID, FILTER_GROUP_ID_VALUE);
			}
			else {
				sql = sql.replace(FILTER_GROUP_ID, StringPool.BLANK);
			}

			sql = sql.replace(FILTER_DOSSIER_ID, StringPool.BLANK);
			sql = sql.replace(FILTER_FILENAME, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_RECORD, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_PAGE, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_COPY, StringPool.BLANK);
			sql = sql.replace(FILTER_TOTAL_FEE, StringPool.BLANK);
			sql = sql.replace(FILTER_NOTARIZATION_NO, StringPool.BLANK);
			sql = sql.replace(FILTER_NOTARIZATION_YEAR, StringPool.BLANK);
			sql = sql.replace(FILTER_NOTARIZATION_DATE, StringPool.BLANK);
			sql = sql.replace(FILTER_SIGNER_NAME, StringPool.BLANK);
			sql = sql.replace(FILTER_SIGNER_POSITION, StringPool.BLANK);
			sql = sql.replace(FILTER_STATUS_CODE, StringPool.BLANK);

			_log.debug("Search notarization: " + sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addScalar(COUNT_COLUMN, Type.INTEGER);
			
			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);				
			}
			
			List ls = (List) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (ls.size() > 0) {
				return Integer.getInteger(ls.get(0).toString());
			} 
			else {
				return 0;
			}
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
//		            se.printStackTrace();
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return 0;
	}
	
	Log _log = LogFactoryUtil.getLog(NotarizationFinderImpl.class);
	public static final String FIND_ADVANCED_SEARCH = NotarizationFinder.class.getName() + ".findAdvancedSearch";
	public static final String COUNT_ADVANCED_SEARCH = NotarizationFinder.class.getName() + ".countAdvancedSearch";	
	
	public static final String FILTER_GROUP_ID = "[$FILTER_GROUP_ID$]";
	public static final String FILTER_DOSSIER_ID = "[$FILTER_DOSSIER_ID$]";
	public static final String FILTER_FILENAME = "[$FILTER_FILENAME$]";
	public static final String FILTER_TOTAL_RECORD = "[$FILTER_TOTAL_RECORD$]";
	public static final String FILTER_TOTAL_PAGE = "[$FILTER_TOTAL_PAGE$]";
	public static final String FILTER_TOTAL_COPY = "[$FILTER_TOTAL_COPY$]";
	public static final String FILTER_TOTAL_FEE = "[$FILTER_TOTAL_FEE$]";
	public static final String FILTER_NOTARIZATION_NO = "[$FILTER_NOTARIZATION_NO$]";
	public static final String FILTER_NOTARIZATION_YEAR = "[$FILTER_NOTARIZATION_YEAR$]";
	public static final String FILTER_NOTARIZATION_DATE = "[$FILTER_NOTARIZATION_DATE$]";
	public static final String FILTER_SIGNER_NAME = "[$FILTER_SIGNER_NAME$]";
	public static final String FILTER_SIGNER_POSITION = "[$FILTER_SIGNER_POSITION$]";
	public static final String FILTER_STATUS_CODE = "[$FILTER_STATUS_CODE$]";
	
	private static final String FILTER_GROUP_ID_VALUE = "AND groupId = ?";
	private static final String FILTER_DOSSIER_ID_VALUE = "AND dossierId = ?";
	private static final String FILTER_FILENAME_VALUE = "AND fileName like ?";
	
	private static final String COUNT_COLUMN = "countnotarization";
}
