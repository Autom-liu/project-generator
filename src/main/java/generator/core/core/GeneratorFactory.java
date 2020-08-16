package generator.core.core;

import generator.core.config.GenerateMode;
import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.core.exec.GenerateExcutor;
import generator.core.core.exec.MybatisExcutor;
import generator.core.core.exec.impl.CommonExcutor;
import generator.core.core.exec.impl.CommonServiceExcutorImpl;
import generator.core.core.exec.impl.ModuleExcutorImpl;
import generator.core.core.exec.impl.ModuleExcutorV2;
import generator.core.core.exec.impl.MybatisExcutorImpl;
import generator.core.core.exec.impl.MybatisExcutorV2;
import generator.core.core.exec.impl.ParentExcutor;
import lombok.Data;

@Data
public class GeneratorFactory {
	
	private MainConfig configuration;

	public GeneratorFactory(MainConfig configuration) {
		super();
		this.configuration = configuration;
	}
	
	public Generator openGenerator() {
		GenerateMode generateMode = configuration.getGenerateMode();
		if(generateMode == null) {
			throw new RuntimeException("generateMode 值非法");
		}
		Generator generator = new Generator();
		switch(generateMode) {
		case BASE: initBaseExcutors(generator); break;
		case MODULE: initModuleExcutors(generator); break;
		case ENTITY_OVERRIDE: initOverrideExcutors(generator); break;
		case INTEGRATE: initAllExcutors(generator); break;
		default: break;
		}
		return generator;
	}
	
	protected void initBaseExcutors(Generator generator) {
		ParentExcutor parentExcutor = new ParentExcutor(configuration);
		CommonExcutor commonExcutor = new CommonExcutor(configuration);
		CommonServiceExcutorImpl serviceExcutorImpl = new CommonServiceExcutorImpl(configuration);
		generator.addExcutor(parentExcutor);
		generator.addExcutor(commonExcutor);
		generator.addExcutor(serviceExcutorImpl);
	}
	
	protected void initOverrideExcutors(Generator generator) {
		for(ModuleConfig m : configuration.getModules()) {
			MybatisExcutorImpl mybatisExcutorImpl = new MybatisExcutorV2(configuration, m);
			generator.addExcutor(mybatisExcutorImpl);
		}
	}
	
	protected void initModuleExcutors(Generator generator) {
		for(ModuleConfig m : configuration.getModules()) {
			MybatisExcutor mybatisExcutor = new MybatisExcutorV2(configuration, m);
			GenerateExcutor executor = new ModuleExcutorV2(configuration, m, mybatisExcutor);
			generator.addExcutor(executor);
		}
	}
	
	protected void initAllExcutors(Generator generator) {
		initBaseExcutors(generator);
		initModuleExcutors(generator);
	}

}
