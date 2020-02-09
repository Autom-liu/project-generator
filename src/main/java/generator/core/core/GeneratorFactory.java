package generator.core.core;

import java.util.ArrayList;
import java.util.List;

import generator.core.config.GenerateMode;
import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.core.exec.GenerateExcutor;
import generator.core.core.exec.impl.CommonExcutor;
import generator.core.core.exec.impl.CommonServiceExcutorImpl;
import generator.core.core.exec.impl.MybatisExcutorImpl;
import generator.core.core.exec.impl.ParentExcutor;
import lombok.Data;

@Data
public class GeneratorFactory {
	
	private MainConfig configuration;
	
	private List<GenerateExcutor> excutors;

	public GeneratorFactory(MainConfig configuration) {
		super();
		this.configuration = configuration;
		this.excutors = new ArrayList<>();
	}
	
	public void initializeExcutor() {
		GenerateMode generateMode = configuration.getGenerateMode();
		if(generateMode == null) {
			throw new RuntimeException("generateMode 值非法");
		}
		
		switch(generateMode) {
		case BASE: initBaseExcutors(); break;
		case MODULE: break;
		case ENTITY_OVERRIDE: initOverrideExcutors(); break;
		case INTEGRATE: break;
		default: break;
		}
	}
	
	public void run() throws Exception {
		for(GenerateExcutor excutor : excutors) {
			excutor.generate();
		}
	}
	
	protected void initBaseExcutors() {
		ParentExcutor parentExcutor = new ParentExcutor(configuration);
		CommonExcutor commonExcutor = new CommonExcutor(configuration);
		CommonServiceExcutorImpl serviceExcutorImpl = new CommonServiceExcutorImpl(configuration);
		excutors.add(parentExcutor);
		excutors.add(commonExcutor);
		excutors.add(serviceExcutorImpl);
	}
	
	protected void initOverrideExcutors() {
		for(ModuleConfig m : configuration.getModules()) {
			MybatisExcutorImpl mybatisExcutorImpl = new MybatisExcutorImpl(configuration, m);
			excutors.add(mybatisExcutorImpl);
		}
	}

}
