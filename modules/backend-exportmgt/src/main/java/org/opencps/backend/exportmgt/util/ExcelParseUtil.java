package org.opencps.backend.exportmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author trungnt
 *
 */
public class ExcelParseUtil {

	private static final Pattern scanRangePattern = Pattern
			.compile("\\$\\{(indexs:)[0-9]+\\,[0-9]+\\,[0-9]+\\,[0-9]+\\}", Pattern.CASE_INSENSITIVE);

	private static final Pattern dataListMapPattern = Pattern
			.compile("\\$\\{(datalist:)\\[([a-z]+|[A-Z]+)([0-9]*)([,])([0-9]+)\\]}", Pattern.CASE_INSENSITIVE);

	private static final Pattern dataPattern = Pattern.compile("\\$\\{([a-z0-9\\[\\]\\;\\:]+|[A-Z0-9\\[\\]\\;\\:]+)\\}",
			Pattern.CASE_INSENSITIVE);

	private static final Pattern variableNamePattern = Pattern
			.compile("(name:)\\[([a-z0-9\\_\\-\\.]+|[A-Z0-9\\_\\-\\.]+)\\]", Pattern.CASE_INSENSITIVE);

	private static final Pattern dataTypePattern = Pattern.compile("(type:)\\[((array)|(text)|(list))\\]",
			Pattern.CASE_INSENSITIVE);

	private static final Pattern posPattern = Pattern.compile("(pos:)\\[([0-9]+)\\]", Pattern.CASE_INSENSITIVE);

	private static final Pattern valuePattern = Pattern.compile("\\[([a-z0-9\\_\\-\\.]+|[A-Z0-9\\_\\-\\.]+)\\]",
			Pattern.CASE_INSENSITIVE);

	public static boolean isDataPattern(String txt) {

		String template = StringPool.BLANK;

		Matcher matcher = dataPattern.matcher(txt);

		while (matcher.find()) {
			template = matcher.group();
			if (Validator.isNotNull(template)) {
				break;
			}
		}

		return Validator.isNotNull(template);
	}

	public static String parse(String template, Map<String, Object> map, int... indexs) {
		if (isDataPattern(template)) {
			Matcher matcher = dataPattern.matcher(template);

			String subPattern = StringPool.BLANK;

			while (matcher.find()) {
				subPattern = matcher.group();
				String value = StringPool.BLANK;
				if (Validator.isNotNull(subPattern)) {
					String name = getName(subPattern);
					String type = getType(subPattern);
					int pos = getPos(subPattern);

					if (Validator.isNotNull(name)) {
						System.out.println("Name: " + name);
						switch (type.toLowerCase()) {
						case "text":
							value = (map != null && map.containsKey(name)) ? ((String) map.get(name))
									: StringPool.BLANK;
							break;
						case "array":
							Object[] dataRow = (map != null && map.containsKey(name)) ? ((Object[]) map.get(name))
									: null;
							if (dataRow != null && pos >= 0 && dataRow.length > pos) {
								value = (String) dataRow[pos];
							}
							break;
						case "list":
							List<Object[]> dataList = (map != null && map.containsKey(name))
									? ((List<Object[]>) map.get(name))
									: null;
							if (dataList != null) {
								int index = (indexs != null && indexs.length > 0) ? indexs[0] : -1;
								if (index >= 0 && dataList.size() > index) {
									dataRow = dataList.get(index);

									if (dataRow != null && pos >= 0 && dataRow.length > pos) {
										value = (String) dataRow[pos];
									}
								}
							}
							break;

						default:
							break;
						}
					}

					template = template.replace(subPattern, value);
				}
			}

		}

		return template;

	}

	public static String getName(String pattern) {

		String value = StringPool.BLANK;

		Matcher matcher = variableNamePattern.matcher(pattern);

		while (matcher.find()) {
			value = matcher.group();
			if (Validator.isNotNull(value)) {
				break;
			}
		}

		if (Validator.isNotNull(value)) {
			matcher = valuePattern.matcher(value);
			while (matcher.find()) {
				value = matcher.group();
				if (Validator.isNotNull(value)) {
					value = value.replace(StringPool.OPEN_BRACKET, StringPool.BLANK).replace(StringPool.CLOSE_BRACKET,
							StringPool.BLANK);
					break;
				}
			}
		}

		return value;
	}

	public static String getType(String pattern) {

		String value = StringPool.BLANK;

		Matcher matcher = dataTypePattern.matcher(pattern);
		while (matcher.find()) {
			value = matcher.group();
			if (Validator.isNotNull(value)) {
				break;
			}
		}

		if (Validator.isNotNull(value)) {
			matcher = valuePattern.matcher(value);
			while (matcher.find()) {
				value = matcher.group();
				if (Validator.isNotNull(value)) {
					value = value.replace(StringPool.OPEN_BRACKET, StringPool.BLANK).replace(StringPool.CLOSE_BRACKET,
							StringPool.BLANK);
					break;
				}
			}
		}

		return value;
	}

	public static int getPos(String pattern) {

		String value = StringPool.BLANK;

		Matcher matcher = posPattern.matcher(pattern);

		while (matcher.find()) {
			value = matcher.group();
			if (Validator.isNotNull(value)) {
				break;
			}
		}

		if (Validator.isNotNull(value)) {
			matcher = valuePattern.matcher(value);
			while (matcher.find()) {
				value = matcher.group();
				if (Validator.isNotNull(value)) {
					value = value.replace(StringPool.OPEN_BRACKET, StringPool.BLANK).replace(StringPool.CLOSE_BRACKET,
							StringPool.BLANK);
					break;
				}
			}
		}

		return GetterUtil.getInteger(value, -1);
	}

	public static int[] getCellIndexs(String rawTxt) {

		int[] indexs = new int[] { 0, 0, 0, 0 };

		if (Validator.isNull(rawTxt)) {
			return indexs;
		}

		Matcher matcher = scanRangePattern.matcher(rawTxt);

		String data = StringPool.BLANK;

		while (matcher.find()) {
			data = matcher.group();
			break;
		}

		if (Validator.isNotNull(data)) {

			data = data.replace(StringPool.OPEN_CURLY_BRACE, StringPool.BLANK)
					.replace(StringPool.CLOSE_CURLY_BRACE, StringPool.BLANK).replace("indexs:", StringPool.BLANK)
					.replace(StringPool.DOLLAR, StringPool.BLANK);
			System.out.println("Data -----: " + JSONFactoryUtil.looseSerialize(data));

			indexs = com.liferay.portal.kernel.util.StringUtil.split(data, 0);

		}

		return indexs;
	}

	public static HashMap<Integer, String> getDataListMap(String pattern) {

		HashMap<Integer, String> map = new HashMap<Integer, String>();

		if (Validator.isNull(pattern)) {
			return map;
		}

		Matcher matcher = dataListMapPattern.matcher(pattern);

		String subPattern = StringPool.BLANK;

		while (matcher.find()) {
			subPattern = matcher.group();
			if (Validator.isNotNull(subPattern)) {
				subPattern = subPattern.replace(StringPool.DOLLAR + "{datalist:", StringPool.BLANK)
						.replace(StringPool.OPEN_CURLY_BRACE, StringPool.BLANK)
						.replace(StringPool.CLOSE_CURLY_BRACE, StringPool.BLANK)
						.replace(StringPool.OPEN_BRACKET, StringPool.BLANK)
						.replace(StringPool.CLOSE_BRACKET, StringPool.BLANK);

				String name = subPattern.substring(0, subPattern.lastIndexOf(StringPool.COMMA));

				int rowIndex = GetterUtil.getInteger(
						subPattern.substring(subPattern.lastIndexOf(StringPool.COMMA) + 1, subPattern.length()), -1);

				if (rowIndex >= 0) {
					map.put(rowIndex, name);
				}

			}

		}

		return map;
	}

}
