package org.opencps.dossiermgt.service.comparator;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;

import org.opencps.dossiermgt.model.DossierAction;

/**
 * @author trungnt
 */
public class DossierActionComparator extends OrderByComparator<DossierAction> {

	private static final long serialVersionUID = 1L;

	private static final String _TABLE_NAME = "opencps_dossieraction";

	private static final String _defaultColumnName = "createDate";

	public String _ORDER_BY_ASC = _TABLE_NAME + StringPool.PERIOD + getColumnName() + " ASC";

	public String _ORDER_BY_DESC = _TABLE_NAME + StringPool.PERIOD + getColumnName() + " DESC";

	private boolean _ascending;

	private String _columnName;

	private Class<?> _columnType;

	public DossierActionComparator() {

		this(false, _defaultColumnName, String.class);
	}

	public DossierActionComparator(boolean ascending, String columnName, Class<?> columnType) {

		_ascending = ascending;

		_columnName = columnName;

		_columnType = columnType;
	}

	@Override
	public int compare(DossierAction dossierAction0, DossierAction dossierAction1) {

		int value = -1;

		Object value0 = getValue(dossierAction0.getClass(), dossierAction0);
		Object value1 = getValue(dossierAction1.getClass(), dossierAction1);
		if (value0 == null && value1 != null) {
			return -1;
		}
		else if (value0 != null && value1 == null) {
			return 1;
		}
		else if (value0 == null && value1 == null) {
			return 0;
		}
		if (_columnType.isAssignableFrom(String.class)) {
			value = ((String) value0).compareTo(((String) value1));
		} else if (_columnType.isAssignableFrom(Date.class)) {
			value = ((Date) value0).compareTo(((Date) value1));
		} else if (_columnType.isAssignableFrom(Long.class)
				|| _columnType.isAssignableFrom(long.class)) {
			value = ((Long) value0).compareTo(((Long) value1));
		} else if (_columnType.isAssignableFrom(Integer.class)
				|| _columnType.isAssignableFrom(int.class)) {
			value = ((Integer) value0).compareTo(((Integer) value1));
		} else if (_columnType.isAssignableFrom(Double.class)
				|| _columnType.isAssignableFrom(double.class)) {
			value = ((Double) value0).compareTo(((Double) value1));
		} else if (_columnType.isAssignableFrom(Float.class)
				|| _columnType.isAssignableFrom(float.class)) {
			value = ((Float) value0).compareTo(((Float) value1));
		} else if (_columnType.isAssignableFrom(Short.class)
				|| _columnType.isAssignableFrom(short.class)) {
			value = ((Short) value0).compareTo(((Short) value1));
		}

		return _ascending ? value : -value;
	}

	public String getColumnName() {

		if (Validator.isNull(_columnName)) {
			return _defaultColumnName;
		}
		return _columnName;
	}

	public String getORDER_BY_ASC() {

		return _TABLE_NAME + StringPool.PERIOD + getColumnName() + " ASC";
	}

	public String getORDER_BY_DESC() {

		return _TABLE_NAME + StringPool.PERIOD + getColumnName() + " DESC";
	}

	@Override
	public String getOrderBy() {

		if (_ascending) {
			return _ORDER_BY_ASC;
		} else {
			return _ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {

		return new String[] { getColumnName() };
	}

	@Override
	public boolean isAscending() {

		return _ascending;
	}

	public void setColumnName(String columnName) {

		this._columnName = columnName;
	}

	public void setORDER_BY_ASC(String orderByASC) {

		_ORDER_BY_ASC = orderByASC;
	}

	public void setORDER_BY_DESC(String orderByDESC) {

		_ORDER_BY_DESC = orderByDESC;
	}

	public Class<?> getColumnType() {

		return _columnType;
	}

	public void setColumnType(Class<?> columnType) {

		this._columnType = columnType;
	}

	/**
	 * @param clazz
	 * @param object
	 * @return
	 */
	private Object getValue(Class<?> clazz, Object object) {

		PropertyDescriptor[] propertyDescriptors = null;

		Object value = null;

		try {
			propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
		} catch (Exception e) {
			_log.error(e);
		}

		if (propertyDescriptors != null && propertyDescriptors.length > 0) {

			try {
				for (PropertyDescriptor pd : propertyDescriptors) {

					if (pd.getReadMethod() != null && !"class".equals(pd.getName())
							&& pd.getDisplayName().equals(getColumnName())) {

						value = pd.getReadMethod().invoke(object);

						break;

					}

				}
			} catch (Exception e) {
				_log.error(e);
			}

		}

		return value;
	}

	private Log _log = LogFactoryUtil.getLog(DossierActionComparator.class.getName());

}
