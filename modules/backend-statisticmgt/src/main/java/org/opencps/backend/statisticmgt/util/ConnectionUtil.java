package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author trungnt
 *
 */
public class ConnectionUtil {
	private static final Log _log = LogFactoryUtil.getLog(ConnectionUtil.class);

	public static Connection _getConnection() {
		if (Validator.isNotNull(_connection)) {
			try {
				if (!_connection.isClosed()) {
					return _connection;
				}
			} catch (Exception e) {
				_log.warn("connection is close");
			}

		}

		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			_connection = dataSource.getConnection();
		} catch (Exception e) {
			_log.warn(e, e);
		}

		return _connection;
	}

	public static void closeConnection() {
		if (Validator.isNotNull(_connection)) {
			try {
				_connection.close();
			} catch (SQLException e) {
				_log.error(e);
			}
		}
	}

	private static Connection _connection = null;
}
