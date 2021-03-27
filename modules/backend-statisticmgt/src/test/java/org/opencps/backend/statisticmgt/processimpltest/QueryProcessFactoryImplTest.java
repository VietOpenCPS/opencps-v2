package org.opencps.backend.statisticmgt.processimpltest;

import org.junit.Test;
import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.model.QuerySchema;
import org.opencps.backend.statisticmgt.processimpl.QueryProcessFactoryImpl;
import org.opencps.backend.statisticmgt.util.QueryUtil;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

/**
 * @author trungnt
 *
 */
public class QueryProcessFactoryImplTest {
	
	@Test
	public void testGetQuerySchema() {
		QueryProcessFactoryImpl factoryImpl = new QueryProcessFactoryImpl();

		String sqlTemplate = QueryUtil.getSQLQueryTemplate(5, Constants.GROUP_COUNT);

		QuerySchema querySchema = factoryImpl.getQuerySchema5(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 5, Constants.GROUP_COUNT);

		String sql = querySchema.getSql();

		System.out.println("type:5,subtye:group_count " + sql);

		LinkedHashMap<String, Class<?>> columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(5, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema5(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 5, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:5,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------6
		sqlTemplate = QueryUtil.getSQLQueryTemplate(6, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema6(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 6, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:6,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(6, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema6(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 6, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:6,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------7
		sqlTemplate = QueryUtil.getSQLQueryTemplate(7, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema7(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 7, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:7,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(7, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema7(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 7, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:7,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------8
		sqlTemplate = QueryUtil.getSQLQueryTemplate(8, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema8(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 8, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:8,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(8, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema8(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 8, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:8,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------9
		sqlTemplate = QueryUtil.getSQLQueryTemplate(9, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema9(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 9, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:9,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(9, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema9(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 9, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:9,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------10
		sqlTemplate = QueryUtil.getSQLQueryTemplate(10, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema10(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 10, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:10,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(10, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema10(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 10, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:10,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------11
		sqlTemplate = QueryUtil.getSQLQueryTemplate(11, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema11(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 11, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:11,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(11, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema11(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 11, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:11,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------12
		sqlTemplate = QueryUtil.getSQLQueryTemplate(12, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema12(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 12, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:12,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(12, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema12(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 12, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:12,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------13
		sqlTemplate = QueryUtil.getSQLQueryTemplate(13, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema13(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 13, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:13,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(12, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema13(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 13, Constants.LIST);

		sql = querySchema.getSql();

		System.out.println("type:13,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------14
		sqlTemplate = QueryUtil.getSQLQueryTemplate(14, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema13(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 14, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:14,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(13, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema14(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 14, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:14,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------15
		sqlTemplate = QueryUtil.getSQLQueryTemplate(15, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema15(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, 2, "domainCode", 0, 20, sqlTemplate, 15, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:15,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(15, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema15(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, 2, "domainCode", 0, 20, sqlTemplate, 15, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:15,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------16
		sqlTemplate = QueryUtil.getSQLQueryTemplate(16, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema16(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 16, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:16,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(16, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema16(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 16, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:16,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------17
		sqlTemplate = QueryUtil.getSQLQueryTemplate(17, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema17(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 17, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:17,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(17, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema17(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 17, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:17,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------18
		sqlTemplate = QueryUtil.getSQLQueryTemplate(18, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema18(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 18, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:18,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(5, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema18(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 18, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:18,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------19
		sqlTemplate = QueryUtil.getSQLQueryTemplate(19, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema19(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 19, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:19,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(19, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema19(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 19, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:19,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------20
		sqlTemplate = QueryUtil.getSQLQueryTemplate(20, Constants.COUNT);

		querySchema = factoryImpl.getQuerySchema20(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 20, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:20,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		
		// ----------------2
		sqlTemplate = QueryUtil.getSQLQueryTemplate(2, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema2(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 2, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:2,subtye:group_count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(2, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema2(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 2, Constants.LIST);

		sql = querySchema.getSql();

		System.out.println("type:2,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		// ----------------4
		sqlTemplate = QueryUtil.getSQLQueryTemplate(4, Constants.COUNT);

		querySchema = factoryImpl.getQuerySchema4(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 4, Constants.COUNT);

		sql = querySchema.getSql();

		System.out.println("type:4,subtye:count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(4, Constants.LIST);

		querySchema = factoryImpl.getQuerySchema4(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 4, Constants.LIST);

		sql = querySchema.getSql();

		System.out.println("type:4,subtye:list " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());

		sqlTemplate = QueryUtil.getSQLQueryTemplate(4, Constants.GROUP_COUNT);

		querySchema = factoryImpl.getQuerySchema4(1L, "2020-01-01", "2021-12-31", new int[] { 1, 2 },
				new String[] { "DC1", "DC2" }, new String[] { "GC1", "GC2" }, new String[] { "SC1", "SC2" },
				new String[] { "done" }, "domainCode", 0, 20, sqlTemplate, 4, Constants.GROUP_COUNT);

		sql = querySchema.getSql();

		System.out.println("type:4,subtye:count " + sql);

		columnMap = querySchema.getColumnMap();

		assertFalse(sql == null || sql.isEmpty() || sql.contains("{") || sql.contains("}") || sql.contains("[")
				|| sql.contains("]") || columnMap == null || columnMap.isEmpty());
	}
	
}
