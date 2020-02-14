package generator.core;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import generator.core.core.Generator;
import generator.core.core.GeneratorFactory;
import generator.core.core.GeneratorFactoryBuilder;
import generator.core.resource.ConfigReader;
import generator.core.resource.support.JsonConfigReader;

public class Main {

	public static void main(String[] args) throws Exception {
		ConfigReader configReader = new JsonConfigReader("generatorConfig.json");
		
		GeneratorFactory generatorFactory = new GeneratorFactoryBuilder().build(configReader);
		Generator generator = generatorFactory.openGenerator();
		generator.run();
		
	}
	
	public static void generateTemplateKey(String basePath) throws IOException {
		Path path = Paths.get(basePath);
		Files.walk(path, 2).forEach(p -> {
			String fileName = p.getFileName().toString();
			// System.out.println(fileName);
			if(fileName.endsWith(".java")) {
				String title = fileName.replace(".java", "");
				String key = title.replaceAll("[A-Z]", "_$0").toUpperCase().substring(1);
				System.out.println("public static final String " + key + " = \"" + key + "\";");
				System.out.println();
			}
		});
	}
	
	public static void generateCode(Path path, String pcb) throws IOException {
		DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.java");
		int i = 1;
		for(Path p : ds) {
			String fileName = p.getFileName().toString();
			String title = fileName.replace(".java", "");
			String key = title.replaceAll("[A-Z]", "_$0").toUpperCase().substring(1);
			System.out.println("Path toPath"+ i +" = pathManager."+ pcb +"().resolve(\""+ title +".java\");");
			System.out.println("String templateString"+ i +" = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath(\""+ title +".ftl\"));");
			System.out.println("GeneratorUtil.putTemplate(TemplateKey."+ key +", templateString"+ i +");");
			System.out.println("GeneratorUtil.generate(TemplateKey."+ key +", toPath"+ i +", templateConfig);");
			System.out.println();
			i++;
		}
		
	}
	
}
