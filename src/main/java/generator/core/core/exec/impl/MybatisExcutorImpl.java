package generator.core.core.exec.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.internal.DefaultShellCallback;

import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.config.TableConfig;
import generator.core.core.exec.MybatisExcutor;
import generator.core.manager.ModulePathManager;
import generator.core.manager.PathManager;
import generator.core.utils.ConfigConverterUtil;

public class MybatisExcutorImpl implements MybatisExcutor {
	
	protected PathManager pathManager = PathManager.getInstance();
	
	protected MainConfig configuration;
	
	protected ModuleConfig moduleConfig;

	public MybatisExcutorImpl(MainConfig configuration, ModuleConfig moduleConfig) {
		this.configuration = configuration;
		this.moduleConfig = moduleConfig;
	}

	@Override
	public void generate() throws Exception {
		beforeGenerate();
		List<String> warnings = new ArrayList<>();
		Configuration cfg = ConfigConverterUtil.getMybatisConfiguration(configuration, moduleConfig, pathManager);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(cfg, callback, warnings);
		myBatisGenerator.generate(null);
		afterGenerate(cfg);
		
	}
	
	@Override
	public void beforeGenerate() {
		String moduleName = moduleConfig.getModuleName();
		Map<String, ModulePathManager> modulePathManagerMap = pathManager.getModulePathManagerMap();
		ModulePathManager modulePathManager = modulePathManagerMap.get(moduleName);
		File mavenJavaFile = modulePathManager.getModuleBasePath().resolve(PathManager.RELATEED_JAVA_PATH).toFile();
		File mavenResourceFile = modulePathManager.getModuleBasePath().resolve(PathManager.RELATEED_RESOURCE_PATH).toFile();
		if(!mavenJavaFile.exists()) {
			mavenJavaFile.mkdirs();			
		}
		if(!mavenResourceFile.exists()) {
			mavenResourceFile.mkdirs();			
		}
	}

	@Override
	public void afterGenerate(Configuration config) throws IOException {
		Set<String> domainNameSet = moduleConfig.getTables().stream().map(TableConfig::getDomainObjectName).collect(Collectors.toSet());
		String moduleName = moduleConfig.getModuleName();
		String basePackage = configuration.getBasePackage();
		String baseExamplePackage = basePackage + ".common.base";
		Map<String, ModulePathManager> modulePathManagerMap = pathManager.getModulePathManagerMap();
		ModulePathManager modulePathManager = modulePathManagerMap.get(moduleName);
		Path moduleBeanPath = modulePathManager.getModuleBeanPath();
		try(DirectoryStream<Path> entries = Files.newDirectoryStream(moduleBeanPath, "*Example.java")) {
			for(Path path : entries) {
				String className = path.getFileName().toString().replace(".java", "");
				if(domainNameSet.contains(className.replace("Example", ""))) {
					List<String> lines = Files.readAllLines(path);
					ListIterator<String> iterator = lines.listIterator();
					while(iterator.hasNext()) {
						String line = iterator.next();
						if(line.startsWith("public class")) {
							iterator.remove();
							iterator.add("public class " + className + " implements BaseExample {");
							iterator.previous();
							iterator.add("");
							iterator.previous();
							iterator.add("import " + baseExamplePackage + ".BaseExample;");
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
		}
		
		
		
	}
	
	

}
