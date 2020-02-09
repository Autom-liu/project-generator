package generator.core.resource.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import generator.core.config.template.ModuleTemplateConfig.JavaFieldDesc;

public class JavaFileReader {
	
	public List<JavaFieldDesc> readJavaFile(Path path) throws IOException {
		List<String> lines = Files.readAllLines(path);
		List<JavaFieldDesc> retList = new ArrayList<>();
		Pattern compile = Pattern.compile("private (\\w+) (\\w+);");
		for(String line : lines) {
			Matcher matcher = compile.matcher(line);
			if(matcher.find()) {
				JavaFieldDesc javaFieldDesc = new JavaFieldDesc();
				javaFieldDesc.setType(matcher.group(1));
				javaFieldDesc.setVariableName(matcher.group(2));
				retList.add(javaFieldDesc);
			}
		}
		return retList;
	}

}
