package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
	Log _log = LogFactoryUtil.getLog(DossierFinderImpl.class);
	public static final String FIND_DOSSIER_BY_DAY = DossierFinder.class.getName() + ".findDossierByDay";
	public static final String FIND_DOSSIER_BY_DECLARATION_CODE = DossierFinder.class.getName() + ".findDossierByDeclarationCode";
	public static final String GET_VOTING_BY_DOSSIER = DossierFinder.class.getName() + ".getListVotingByDossier";


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
	public List<Object[]> getListVotingByDossier(long groupId, List<String> listDossier) {
		Session session;
		try {
			session = openSession();
			StringBuilder listDossierWithComma = new StringBuilder();
			int lengthListDossierId = listDossier.size();
			for(int i=0; i<=lengthListDossierId - 1; i++) {
				if(i == (lengthListDossierId - 1)) {
					listDossierWithComma.append(listDossier.get(i));
				} else {
					listDossierWithComma.append(listDossier.get(i)).append(",");
				}
			}

			String sql = "select OV.classPK, OV.votingCode, OV.votingId, OV.subject, sum(case when OVR.selected = 1 then 2 " +
					"    when OVR.selected = 2 then 1 " +
					"                       when OVR.selected = 3 then 0 " +
					"                       else 0 end) as point " +
					"            from opencps_voting OV left join opencps_votingresult OVR on ov.votingId = OVR.votingId " +
					"            where OV.groupId = " + groupId +" and OV.classPK in (" + listDossierWithComma.toString() + ") " +
					"            group by OV.votingId " +
					"            order by votingId asc";

			_log.info("Query get list voting: " + sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.setCacheable(false);
			query.addScalar("classPK", Type.STRING);
			query.addScalar("votingCode", Type.STRING);
			query.addScalar("votingId", Type.INTEGER);
			query.addScalar("subject", Type.STRING);
			query.addScalar("point",Type.INTEGER);

			return (List<Object[]>) query.list();
		} catch (Exception e) {
			_log.error(e.getMessage());
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
}
