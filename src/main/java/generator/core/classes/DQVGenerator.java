package generator.core.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import generator.config.pom.PomConfig;
import lombok.AllArgsConstructor;
import lombok.Data;

public class DQVGenerator {
	
	private String basePackage;
	
	private String moduleName;
	
	private List<BeanFiled> beanfields;
	
	private String beanName;

	
	public DQVGenerator(String basePackage, String moduleName) {
		this.basePackage = basePackage;
		this.moduleName = moduleName;
		beanfields = new ArrayList<>();
	}
	
	public void generateDTO(Path dtoPath) {
		try {
			Path path = dtoPath.resolve(beanName + "DTO.java");
			Files.write(path, dtoTemplate(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("DTO file write fail....");
			e.printStackTrace();
		}
	}
	
	public void generateVo(Path voPath) {
		try {
			Path path = voPath.resolve(beanName + "VO.java");
			Files.write(path, voTemplate(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("DTO file write fail....");
			e.printStackTrace();
		}
	}
	
	public void generateQuery(Path queryPath) {
		try {
			Path path = queryPath.resolve(beanName + "Query.java");
			Files.write(path, queryTemplate(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("DTO file write fail....");
			e.printStackTrace();
		}

	}

	public void parseBean(Path beanPath) throws IOException {
		List<String> lines = Files.readAllLines(beanPath);
		Pattern classPattern = Pattern.compile("public class (.+) implements Serializable \\{");
		Pattern fieldPattern = Pattern.compile("private ([^ ]+) ([^ ]+);");
		for(String line: lines) {
			Matcher matcher1 = classPattern.matcher(line);
			Matcher matcher2 = fieldPattern.matcher(line.trim());
			if(matcher1.matches()) {
				String beanName = matcher1.group(1);
				this.beanName = beanName;
			}
			if(matcher2.matches()) {
				beanfields.add(new BeanFiled(matcher2.group(1), matcher2.group(2)));
			}
			
		}
	}
	
	private List<String> dtoTemplate() {
		List<String> template = new ArrayList<>();
		
		template.add(String.format("package %s.%s.dto;", this.basePackage, this.moduleName));
		template.add("");
		template.add("import java.io.Serializable;");
		template.add("import java.util.Date;");
		template.add("");
		template.add("import lombok.Data;");
		template.add("");
		template.add("@Data");
		template.add(String.format("public class %sDTO implements Serializable {", this.beanName));
		template.add("    private static final long serialVersionUID = 1L;");
		for(BeanFiled field: beanfields) {
			template.add("");
			template.add(String.format("    private %s %s;", field.getType(), field.getName()));
		}
		template.add("}");
		return template;
	}
	
	private List<String> voTemplate() {
		List<String> template = new ArrayList<>();
		
		template.add(String.format("package %s.%s.vo;", this.basePackage, this.moduleName));
		template.add("");
		template.add("import java.io.Serializable;");
		template.add("import java.util.Date;");
		template.add("");
		template.add("import com.fasterxml.jackson.annotation.JsonInclude;");
		template.add("import com.fasterxml.jackson.annotation.JsonInclude.Include;");
		template.add("");
		template.add("import lombok.Data;");
		template.add("");
		template.add("@Data");
		template.add("@JsonInclude(Include.NON_NULL)");
		template.add(String.format("public class %sVO implements Serializable {", this.beanName));
		template.add("    private static final long serialVersionUID = 1L;");
		for(BeanFiled field: beanfields) {
			template.add("");
			template.add(String.format("    private %s %s;", field.getType(), field.getName()));
		}
		template.add("}");
		return template;
	}
	
	public String getBasePackage() {
		return basePackage;
	}

	public String getModuleName() {
		return moduleName;
	}

	public List<BeanFiled> getBeanfields() {
		return beanfields;
	}

	public String getBeanName() {
		return beanName;
	}

	private List<String> queryTemplate() throws IOException {
		List<String> template = new ArrayList<>();
		template.add(String.format("package %s.%s.query;", this.basePackage, this.moduleName));
		template.add("");
		template.add(String.format("import %s.%s.query.PageQuery;", this.basePackage, PomConfig.Common.getModuleName()));
		template.add("");
		template.add("import lombok.Data;");
		template.add("import lombok.EqualsAndHashCode;");
		template.add("");
		template.add("@Data");
		template.add("@EqualsAndHashCode(callSuper = false)");
		template.add(String.format("public class %sQuery extends PageQuery {", this.beanName));
		template.add("");
		template.add("	private static final long serialVersionUID = 1L;");
		template.add("");
		template.add("}");
		
		return template;
	}
	
	@Data
	@AllArgsConstructor
	private class BeanFiled {
		
		private String type;
		
		private String name;
		
	}

}
