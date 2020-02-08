package generator.core.core.exec.impl;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.config.template.ParentPomConfig;
import generator.core.core.exec.GenerateExcutor;
import generator.core.manager.PathManager;
import generator.core.utils.ConfigConverterUtil;
import generator.core.utils.GeneratorUtil;
import generator.core.utils.TemplateKey;

/**
 * 父级工程生成器
 * @author Autom
 * @date 2020年2月8日
 * @version 1.0
 *
 */
public class ParentExcutor implements GenerateExcutor {
	
	protected PathManager pathManager = PathManager.getInstance();
	
	protected MainConfig configuration;
	
	public ParentExcutor(MainConfig configuration) {
		super();
		this.configuration = configuration;
	}

	protected void pomGenerate() throws Exception {
		ParentPomConfig parentPomConfig = ConfigConverterUtil.getBasePomTemplateConfig(configuration, configuration.getArtifactId(), ParentPomConfig.class);
		List<String> modules = configuration.getModules().stream().map(ModuleConfig::getArtifactId).collect(Collectors.toList());
		parentPomConfig.setModules(modules);
		Path toPath = pathManager.getBasePath().resolve("pom.xml");
		GeneratorUtil.putTemplate(TemplateKey.PARENT_POM, GeneratorUtil.readTemplateString(PathManager.PARENTPOM_TEMPLATE_PATH));
		GeneratorUtil.generate(TemplateKey.PARENT_POM, toPath, parentPomConfig);
	}

	@Override
	public void generate() throws Exception {
		this.pomGenerate();
	}
	
}
