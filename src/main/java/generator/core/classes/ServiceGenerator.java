package generator.core.classes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import generator.config.BasePath;
import generator.core.AbstractGenerator;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class ServiceGenerator extends AbstractGenerator {
	
	private String basePackage;
	
	private String moduleName;
	
	private String beanName;
	
	private String mapperVariable;
	
	private String beanListVariable;
	
	protected Path templateBase;
	
	public ServiceGenerator(DQVGenerator dqvGenerator) {
		super();
		this.basePackage = dqvGenerator.getBasePackage();
		this.moduleName = dqvGenerator.getModuleName();
		this.beanName = dqvGenerator.getBeanName();
		this.mapperVariable = this.toLowerFirstCase(beanName) + "Mapper";
		this.beanListVariable = this.toLowerFirstCase(beanName) + "s";
		this.templateBase = BasePath.TEST_RESOURCE.resolve(Paths.get("generator", "template"));
	}

	public void generateInterface(Path servicePath) {
		Path fromPath = templateBase.resolve("service/Service.java");
		Path toPath = servicePath.resolve(this.beanName + "Service.java");
		super.generator(fromPath, toPath, this);
		
	}
	
	@SneakyThrows
	public void generatorImpl(Path implPath) {
		if(!Files.isDirectory(implPath)) {
			Files.createDirectories(implPath);
		}
		Path fromPath = templateBase.resolve("service/ServiceImpl.java");
		Path toPath = implPath.resolve(this.beanName + "ServiceImpl.java");
		super.generator(fromPath, toPath, this);
	}
	
	private String toLowerFirstCase(String s) {
		if(Character.isUpperCase(s.charAt(0))) {
			char[] cs = s.toCharArray();
			cs[0] += 32;
			return String.valueOf(cs);
		}
		return s;
	}
	
	
	
}
