package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.impl.DossierMarkImpl;
import org.opencps.dossiermgt.service.persistence.DossierMarkFinder;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

public class DossierMarkFinderImpl extends DossierMarkFinderBaseImpl implements DossierMarkFinder {
    Log _log = LogFactoryUtil.getLog(DossierMarkFinderImpl.class);

    @Override
    public List<DossierMark> findDossierMarkByDossierId(long groupId, long dossierId) {
        Session session = null;
        List<DossierMark> lstDossierMark = null;
        String sql = "SELECT * FROM opencps_dossiermark WHERE"
                + " groupId = "+groupId+ " AND dossierId = " +dossierId+ " ORDER BY dossierId desc ";

        try {
            session = openSession();

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("opencps_dossiermark", DossierMarkImpl.class);

            lstDossierMark = q.list();
        }catch (Exception e) {
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
        return lstDossierMark;

    }
}
