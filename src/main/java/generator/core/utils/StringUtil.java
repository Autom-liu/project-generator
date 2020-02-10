package generator.core.utils;

import java.util.List;

import org.springframework.util.StringUtils;

public class StringUtil {

	
	public static String joinLineList(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for(String str : strs) {
			sb.append(str + System.lineSeparator());
		}
		return sb.toString();
	}
	
	public static String defaultString(String str) {
		return StringUtils.isEmpty(str) ? "" : str;
	}
	
}
