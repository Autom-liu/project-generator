package generator.core.utils;

import java.util.List;

public class StringUtil {

	
	public static String joinLineList(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for(String str : strs) {
			sb.append(str + System.lineSeparator());
		}
		return sb.toString();
	}
	
}
