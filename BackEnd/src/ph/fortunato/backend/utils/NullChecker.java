/**
 * 
 */
package ph.fortunato.backend.utils;

import java.util.Map;

/**
 * @author Sean Ross
 *
 */
public class NullChecker {

	public static boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	public static boolean isEmpty(Long value) {
		return value == null || value.longValue() == 0L;
	}

	public static boolean isEmpty(Integer value) {
		return value == null || value.intValue() == 0;
	}

	public static boolean isEmpty(Double value) {
		return value == null || value.doubleValue() == 0.0;
	}

	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(byte[] array) {
		return array == null || array.length == 0;
	}

	public static <T, K> boolean isEmpty(Map<T, K> map) {
		return map == null || map.size() == 0;
	}

	public static <T> boolean isEmpty(T t) {
		return t == null;
	}
	
}
