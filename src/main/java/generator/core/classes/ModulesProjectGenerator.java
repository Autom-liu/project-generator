package generator.core.classes;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.PomConfig;
import generator.config.pom.ModulePomConfigFactory.ModulePomConfig;

public class ModulesProjectGenerator extends AbstractProjectGenerator {

	private ModulePomConfig moduleConfig;
	
	private Path projectPath;
	
	public ModulesProjectGenerator(ModulePomConfig moduleConfig) {
		this.moduleConfig = moduleConfig;
		this.projectPath = moduleConfig.getProjectPath();
	}

	@Override
	protected Path getTemplateBasepath() {
		return super.templateBase.resolve("modules");
	}

	@Override
	protected Path getTargetBasepath() {
		String packagePath = PomConfig.getGroupId().replace(".", "/") + "/" + moduleConfig.getModuleName();
		Path path = BasePath.JAVA_PATH.resolve(packagePath);
		return projectPath.resolve(path);
	}

	@Override
	protected Path getProjectPath() {
		return projectPath;
	}

	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("parentArtifact", PomConfig.getArtifactId());
		ret.put("artifactId", moduleConfig.getArtifactId());
		ret.put("basePackage", PomConfig.getBasePackage());
		ret.put("moduleName", moduleConfig.getModuleName());
		ret.put("mainClass", moduleConfig.getMainClass());
		return ret;
	}

	@Override
	protected void success() {
		try {
			String baseExamplePackage = PomConfig.getBasePackage() + ".common.bean.BaseExample";
			MybatisGenerator.generate(baseExamplePackage);
			
			DQVGenerator dqvGenerator = new DQVGenerator(PomConfig.getBasePackage(), moduleConfig.getModuleName());
			Path beanPackage = getTargetBasepath().resolve("bean");
			DirectoryStream<Path> beansStream = Files.newDirectoryStream(beanPackage);
			for(Path beanPath: beansStream) {
				String fileName = beanPath.getFileName().toString();
				if(!fileName.endsWith("Example.java")) {
					dqvGenerator.parseBean(beanPath);
					dqvGenerator.generateDTO(getTargetBasepath().resolve("dto"));
					dqvGenerator.generateVo(getTargetBasepath().resolve("vo"));
					dqvGenerator.generateQuery(getTargetBasepath().resolve("query"));
					
					ServiceGenerator sg = new ServiceGenerator(dqvGenerator);
					sg.generateInterface(this.getTargetBasepath().resolve("service"));
					sg.generatorImpl(this.getTargetBasepath().resolve("service/impl"));
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void generate() {
		super.generate();
		Path fromPath = super.templateBase.resolve("generatorConfig.xml");
		Path toPath = BasePath.RESOURCE_PATH.resolve("generatorConfig.xml");
		super.generator(fromPath, toPath, getConfig());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void fileGenerate(Path fromPath, Path toPath) {
		String mainFileName = ((Map<String, String>)getConfig()).get("mainClass") + ".java";
		if(toPath.getFileName().toString().endsWith("Application.java")) {
			Path targetPath = this.getTargetBasepath().resolve(Paths.get("..", mainFileName));
			super.fileGenerate(fromPath, targetPath);
		}
	}
	
	
}
