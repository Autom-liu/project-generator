package generator.core.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private static final Pattern linePattern = Pattern.compile("_(\\w)");

	public static String lineToHump(String str) {
		final Matcher matcher = linePattern.matcher(str);
		final StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
