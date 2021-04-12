package org.opencps.backend.statisticmgt.model;

import java.util.LinkedHashMap;

/**
 * @author trungnt
 *
 */
public class QuerySchema {
	public LinkedHashMap<String, Class<?>> columnMap;
	public String sql;
	public String sqlTemplate;
	public String subtype;
	public int type;

	public QuerySchema(String sql, String sqlTemplate, int type, String subtype,
			LinkedHashMap<String, Class<?>> columnMap) {
		setColumnMap(columnMap);
		setSql(sql);
		setSqlTemplate(sqlTemplate);
		setSubtype(subtype);
		setType(type);
	}

	public LinkedHashMap<String, Class<?>> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(LinkedHashMap<String, Class<?>> columnMap) {
		this.columnMap = columnMap;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSqlTemplate() {
		return sqlTemplate;
	}

	public void setSqlTemplate(String sqlTemplate) {
		this.sqlTemplate = sqlTemplate;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
