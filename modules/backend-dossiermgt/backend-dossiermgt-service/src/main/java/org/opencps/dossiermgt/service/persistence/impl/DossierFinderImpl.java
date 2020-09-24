package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;
import org.opencps.dossiermgt.input.model.DictItemModel;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.impl.DossierImpl;
import org.opencps.dossiermgt.service.persistence.DossierFinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DossierFinderImpl extends DossierFinderBaseImpl implements DossierFinder
{
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL           _customSQL;
	private static final String DOSSIER_ENTITY = "Dossier";
	private static final String DICTITEM_ENTITY = "DictItem";
	Log _log = LogFactoryUtil.getLog(DossierFinderImpl.class);
	public static final String FIND_DOSSIER_BY_DAY = DossierFinder.class.getName() + ".findDossierByDay";
	public static final String FIND_DOSSIER_BY_DECLARATION_CODE = DossierFinder.class.getName() + ".findDossierByDeclarationCode";
	public static final String FIND_DICT_ITEM_BY_SERVICE_DOMAIN = DossierFinder.class.getName() + ".findDictItemByServiceDomain";
	public static final String FIND_DICT_ITEM_BY_SERVICE_LEVEL = DossierFinder.class.getName() + ".findServiceConfigByServiceLevel";


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

	@Override
	public Dossier findDossierByDeclarationCode(String code, long groupId) {
		Session session = null;
		try {
		// Mở session
			session = openSession();
			String sql = _customSQL.get(getClass(),FIND_DOSSIER_BY_DECLARATION_CODE);
			String logSql = "SELECT d.* , JSON_EXTRACT(metaData, \"$.ma_to_khai\") AS metaData\n" +
					" FROM  opencps_dossier AS d\n" +
					" WHERE replace(replace(JSON_EXTRACT(metaData, \"$.ma_to_khai\"),'[\"',''), '\"]','') = " + code + "d.groupId = " + groupId;
			_log.info("SQL: " + logSql);
			_log.info("Mã tờ khai : " + code);
			_log.info("groupId: " + groupId);
			SQLQuery query = session.createSQLQuery(sql);
			query.setCacheable(false);
			query.addEntity(DOSSIER_ENTITY, DossierImpl.class);

			QueryPos queryPos = QueryPos.getInstance(query);
			queryPos.add(code);
			queryPos.add(groupId);
			Dossier dossier = (Dossier) query.uniqueResult();
			if(Validator.isNotNull(dossier)){
				return dossier;
			}
		}catch (Exception e){
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		}finally {
			closeSession(session);
		}
		return null;
	}
//	@Override
//	public List<DictItemModel> findDictItemByServiceDomain(String serviceLevel, long groupId) {
//		Session session = null;
//		try {
//			// Mở session
//			session = openSession();
//			String sql = _customSQL.get(getClass(),FIND_DICT_ITEM_BY_SERVICE_LEVEL);
//			_log.info("ServiceLevel : " + serviceLevel);
//			_log.info("groupId: " + groupId);
//			SQLQuery query = session.createSQLQuery(sql);
//			query.setCacheable(false);
//			query.addEntity(DICTITEM_ENTITY, DossierImpl.class);
//
//			QueryPos queryPos = QueryPos.getInstance(query);
//			queryPos.add(serviceLevel);
//			queryPos.add(groupId);
//			List<DictItemModel> dictItems = (List<DictItemModel>) query.uniqueResult();
//			if(Validator.isNotNull(dictItems)){
//				return dictItems;
//			}
//		}catch (Exception e){
//			try {
//				throw new SystemException(e);
//			} catch (SystemException se) {
//				se.printStackTrace();
//			}
//		}finally {
//			closeSession(session);
//		}
//		return null;
//	}
//	@Override
//	public List<DictItemModel> findServiceConfigByServiceLevel(String serviceLevel, long groupId) {
//		Session session = null;
//		try {
//			// Mở session
//			session = openSession();
//			String sql = _customSQL.get(getClass(),FIND_DICT_ITEM_BY_SERVICE_LEVEL);
//			_log.info("ServiceLevel : " + serviceLevel);
//			_log.info("groupId: " + groupId);
//			SQLQuery query = session.createSQLQuery(sql);
//			query.setCacheable(false);
//			query.addEntity(DICTITEM_ENTITY, DossierImpl.class);
//
//			QueryPos queryPos = QueryPos.getInstance(query);
//			queryPos.add(serviceLevel);
//			queryPos.add(groupId);
//			List<DictItemModel> dictItems = (List<DictItemModel>) query.uniqueResult();
//			if(Validator.isNotNull(dictItems)){
//				return dictItems;
//			}
//		}catch (Exception e){
//			try {
//				throw new SystemException(e);
//			} catch (SystemException se) {
//				se.printStackTrace();
//			}
//		}finally {
//			closeSession(session);
//		}
//		return null;
//	}
}
