
package org.opencps.kernel.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author trungnt
 */
public class TreeIndexUtil {

	public TreeIndexUtil() {

	}

	@Deprecated
	public static String generateTreeIndex(
		String parentTreeIndex, int sibling) {

		if (sibling < 0) {

			sibling = 0;

		}

		String treeIndex = StringPool.BLANK;

		if (Validator.isNull(parentTreeIndex)) {

			String ext = StringPool.BLANK;

			for (int i = 0; i < 4 - String.valueOf(sibling).length(); i++) {

				ext += String.valueOf(0);

			}

			treeIndex += ext + sibling;

		}
		else {

			String ext = StringPool.BLANK;

			for (int i = 0; i < 4 - String.valueOf(sibling).length(); i++) {
				ext += String.valueOf(0);
			}

			treeIndex = parentTreeIndex + StringPool.PERIOD + ext +
				Integer.toHexString(Integer.valueOf(sibling));
		}

		return treeIndex;
	}

	protected int getMaxIntegerNumberOfRange(int length) {
		// Integer type range from -2147483648 to 2147483647(32bit) -> only
		// right when length less than 10, if length bigger than 10 then
		// maxNumber = 0

		int maxNumber = 0;

		String temp = StringPool.BLANK;

		if (length >= 10) {
			// Over range
			maxNumber = Integer.MAX_VALUE;
		}
		else {
			for (int i = 1; i <= length; i++) {
				temp += String.valueOf(9);
			}

			maxNumber = GetterUtil.getInteger(temp);
		}

		return maxNumber;
	}

	protected String getMaxHexNumberOfRange(int length) {

		String maxHexNumber = StringPool.BLANK;

		for (int i = 1; i <= length; i++) {
			maxHexNumber += "F";
		}

		return maxHexNumber;
	}

	protected long convertHexToNumber(String hex) {

		long maxNumber = Long.parseLong(hex);

		return maxNumber;
	}

//	public static void main(String[] args) {
//
//		System.out.println(new TreeIndexUtil().getMaxIntegerNumberOfRange(9));
//		System.out.println(generateNumberTreeIndex("0001.0001", 99999, 4));
//		System.out.println(generateHexTreeIndex("00011.00001", 65535, 5));
//		System.out.println(Integer.toHexString(300));
//		System.out.println(Integer.MAX_VALUE);
//	}

	public static String generateNumberTreeIndex(
		String parentTreeIndex, int sibling, int length) {

		// length = 0 -> free treeIndex, ex: 2012.479332457.24928

		int maxSibling = new TreeIndexUtil().getMaxIntegerNumberOfRange(length);

		if (sibling < 0) {
			sibling = 0;
		}

		if (sibling > maxSibling) {
			sibling = maxSibling;
		}

		String treeIndex = StringPool.BLANK;

		if (length == 0) {
			treeIndex = parentTreeIndex + StringPool.PERIOD + sibling;
		}
		else {
			String ext = StringPool.BLANK;
			for (int i = 0; i < length -
				String.valueOf(sibling).length(); i++) {

				ext += String.valueOf(0);

			}
			treeIndex += ext + sibling;
		}

		if (Validator.isNotNull(parentTreeIndex)) {
			treeIndex = parentTreeIndex + StringPool.PERIOD + treeIndex;
		}

		return treeIndex;
	}

	public static String generateHexTreeIndex(
		String parentTreeIndex, int sibling, int length) {

		// length = 0 -> free treeIndex, ex: 2012.47ae4e9332457.a24928

		int maxSibling = new TreeIndexUtil().getMaxIntegerNumberOfRange(length);

		if (sibling < 0) {
			sibling = 0;
		}

		if (sibling > maxSibling) {
			sibling = maxSibling;
		}

		String treeIndex = StringPool.BLANK;

		if (length == 0) {
			treeIndex = parentTreeIndex + StringPool.PERIOD +
				Integer.toHexString(sibling);
		}
		else {
			String ext = StringPool.BLANK;
			String hexSibling = Integer.toHexString(sibling);
			for (int i = 0; i < length - hexSibling.length(); i++) {

				ext += String.valueOf(0);

			}
			treeIndex += ext + hexSibling;
		}

		if (Validator.isNotNull(parentTreeIndex)) {
			treeIndex = parentTreeIndex + StringPool.PERIOD + treeIndex;
		}

		return treeIndex;
	}
}
