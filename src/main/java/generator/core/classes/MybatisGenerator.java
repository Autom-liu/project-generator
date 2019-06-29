package generator.core.classes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import generator.config.BasePath;

public class MybatisGenerator {
	public static void generate(String baseExamplePackage) throws Exception{

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		//指定 逆向工程配置文件
		Path path = BasePath.RESOURCE_PATH.resolve("generatorConfig.xml");
		File configFile = path.toFile();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		regeneratorExample(config, baseExamplePackage);
	}
	
	private static void regeneratorExample(Configuration config, String baseExamplePackage) {
		Context context = config.getContext("DB2Tables");
		Set<String> domainNameList = context.getTableConfigurations()
				.stream().map(TableConfiguration::getDomainObjectName)
				.collect(Collectors.toSet());
		String project = context.getJavaModelGeneratorConfiguration().getTargetProject();
		String targetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
		
		String pathString = project + "/" + targetPackage.replace(".", "/");
		
		Path finalPath = Paths.get(pathString).toAbsolutePath().normalize();
		
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(finalPath, "*Example.java")) {
			for(Path path : entries) {
				String className = path.getFileName().toString().replace(".java", "");
				if(domainNameList.contains(className.replace("Example", ""))) {
					List<String> lines = Files.readAllLines(path);
					ListIterator<String> iterator = lines.listIterator();
					while(iterator.hasNext()) {
						String line = iterator.next();
						if(line.startsWith("public class")) {
							iterator.remove();
							iterator.add("public class " + className + " extends BaseExample {");
							iterator.previous();
							iterator.add("");
							iterator.previous();
							iterator.add("import " + baseExamplePackage + ";");
							break;
						}
					}
					// 写文件
					try (PrintWriter pw = new PrintWriter(path.toFile())) {
						for(String line : lines) {
							pw.println(line);
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
