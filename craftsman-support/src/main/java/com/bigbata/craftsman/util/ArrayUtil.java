package com.bigbata.craftsman.util;

import java.util.List;

/**
 * 模块名称: 类名称： ArrayUtil 类描述：  创建时间：2009-11-27 下午02:15:32
 * 修改时间：2009-11-27 下午02:15:32 修改备注：
 * 
 * @version
 * 
 */
public class ArrayUtil {

	/**
	 * 翻转数组
	 * 
	 * @param source
	 *            待翻转数组
	 * @param goal
	 *            翻转后数组
	 */
	public static void reverse(Object[] source, Object[] goal) {
		for (int i = 0; i < source.length; i++) {
			goal[source.length - i - 1] = source[i];
		}
	}

	/**
	 * @param source
	 * @param sperator
	 * @return
	 */
	public static String join(Object[] source, String sperator) {
		if(source == null) return null;
		StringBuffer buffer = new StringBuffer();
		for (Object obj : source) {
			buffer.append(obj.toString()).append(sperator);
		}
		if (buffer.length() > 0)
			buffer.delete(buffer.length() - sperator.length(), buffer.length());
		return buffer.toString();
	}

	public static String join(List list, String sperator) {
		return join(list == null?null:list.toArray(), sperator);
	}

	public static String join(Object[] source) {
		return join(source, ",");
	}

	public static String toString(Object[] array, SingleToString format) {
		return toString(array, "[", ",", "]", format);
	}

	public static String toString(Object[] array, String startQuote,
			String splitStr, String endQuote, SingleToString format) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(startQuote);
		for (Object str : array) {
			buffer.append(format.toString(str)).append(splitStr);
		}

		if (buffer.length() > 1)
			buffer.delete(buffer.length() - 1, buffer.length());

		buffer.append(endQuote);

		return buffer.toString();
	}

	public interface SingleToString {
		public String toString(Object value);
	}

	public class SimpleSingleToString implements SingleToString {
		public String toString(Object value) {
			return value.toString();
		}
	}
}
