package generator.core.core.exec;

import java.nio.file.Path;

import generator.core.config.template.ModuleTemplateConfig;

public interface ModuleExcutor extends GenerateExcutor {
	
	void mainClassGenerate() throws Exception;
	
	ModuleTemplateConfig resolveTemplateConfig(Path path, String className) throws Exception;

	void dtoGenerate(ModuleTemplateConfig templateConfig) throws Exception;
	
	void queryGenerate(ModuleTemplateConfig templateConfig) throws Exception;
	
	void serviceGenerate(ModuleTemplateConfig templateConfig) throws Exception;
	
	void voGenerate(ModuleTemplateConfig templateConfig) throws Exception;
	
	void webGenerate(ModuleTemplateConfig templateConfig) throws Exception;
	
}
