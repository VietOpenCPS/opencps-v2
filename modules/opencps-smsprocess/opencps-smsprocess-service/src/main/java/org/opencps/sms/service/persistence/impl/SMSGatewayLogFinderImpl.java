
package org.opencps.sms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

import org.opencps.sms.model.SMSGatewayLog;
import org.opencps.sms.model.impl.SMSGatewayLogImpl;
import org.opencps.sms.service.persistence.SMSGatewayLogFinder;

public class SMSGatewayLogFinderImpl extends SMSGatewayLogFinderBaseImpl implements SMSGatewayLogFinder {

    private static final String SQL_SEARCH_SMSLOG = SMSGatewayLogFinder.class.getName() + ".search";

    @ServiceReference(type = CustomSQL.class)
    private CustomSQL _customSQL;

    public List<SMSGatewayLog> searchSMSGateway(
        int smsType, Date reqFrom, Date reqTo, Date replyFrom, Date replyTo, String src, int status, int start,
        int end) {

        Session session = null;
        try {
            session = openSession();

            String sql = _customSQL.get(getClass(), SQL_SEARCH_SMSLOG);
            
            if (Validator.isNull(smsType)) {
                sql = StringUtil.replace(sql, "AND smsType = ?", "");
            }

            if (Validator.isNull(reqFrom)) {
                sql = StringUtil.replace(sql, "AND reqDate >= ?", "");
            }
            if (Validator.isNull(reqTo)) {
                sql = StringUtil.replace(sql, "AND reqDate <= ?", "");
               
            }
            if (Validator.isNull(replyFrom)) {
                sql = StringUtil.replace(sql, "AND replyDate >= ?", "");

            }
            if (Validator.isNull(replyTo)) {
                sql = StringUtil.replace(sql, "AND replyDate <= ?", "");

            }
            if (Validator.isNull(src)) {
                sql = StringUtil.replace(sql, "AND src = ?", "");

            }
            if (Validator.isNull(status)) {
                sql = StringUtil.replace(sql, "AND status = ?", "");

            }

            SQLQuery q = session.createSQLQuery(sql);

            q.setCacheable(false);
            q.addEntity("SMSGatewayLog", SMSGatewayLogImpl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            
            if (Validator.isNotNull(smsType)) {
                qPos.add(smsType);
            }

            if (Validator.isNotNull(reqFrom)) {
                qPos.add(reqFrom);
            }
            if (Validator.isNotNull(reqTo)) {
                qPos.add(reqTo);
               
            }
            if (Validator.isNotNull(replyFrom)) {
                qPos.add(replyFrom);

            }
            if (Validator.isNotNull(replyTo)) {
                qPos.add(replyTo);

            }
            if (Validator.isNotNull(src)) {
                qPos.add(src);

            }
            if (Validator.isNotNull(status)) {
                qPos.add(status);
            }

            return (List<SMSGatewayLog>) QueryUtil.list(q, getDialect(), start, end);

        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }
        return null;
    }
}
