package generator.core.core.exec.impl;

import java.nio.file.Path;

import generator.core.config.MainConfig;
import generator.core.config.template.BasePomTemplateConfig;
import generator.core.config.template.BaseTemplateConfig;
import generator.core.core.exec.CommonExecutor;
import generator.core.manager.PathManager;
import generator.core.utils.ConfigConverterUtil;
import generator.core.utils.GeneratorUtil;
import generator.core.utils.TemplateKey;

public class CommonExcutor extends ParentExcutor implements CommonExecutor {

	public CommonExcutor(MainConfig configuration) {
		super(configuration);
	}

	@Override
	public void enumsGenerate() throws Exception {
		BaseTemplateConfig templateConfig = ConfigConverterUtil.getBaseTemplateConfig(configuration, "common");
		Path toPath1 = pathManager.getCommonEnumsPath().resolve("BaseStatusEnums.java");
		Path toPath2 = pathManager.getCommonEnumsPath().resolve("IErrorEnum.java");
		GeneratorUtil.putTemplate(TemplateKey.BASE_STATUS_ENUM, GeneratorUtil.readTemplateString(PathManager.BASESTATUSENUM_TEMPLATE_PATH));
		GeneratorUtil.putTemplate(TemplateKey.IERROR_ENUM, GeneratorUtil.readTemplateString(PathManager.IERRORENUM_TEMPLATE_PATH));
		GeneratorUtil.generate(TemplateKey.BASE_STATUS_ENUM, toPath1, templateConfig);
		GeneratorUtil.generate(TemplateKey.IERROR_ENUM, toPath2, templateConfig);
	}

	@Override
	public void exceptionGenerate() {
		
	}

	@Override
	public void utilGenerate() throws Exception {
		
	}

	@Override
	protected void pomGenerate() throws Exception {
		String artifactId = super.configuration.getArtifactId();
		String moduleName = artifactId + "-common";
		BasePomTemplateConfig pomTemplateConfig = ConfigConverterUtil.getBasePomTemplateConfig(configuration, moduleName);
		Path toPath = super.pathManager.getBaseCommonPath().resolve("pom.xml");
		GeneratorUtil.putTemplate(TemplateKey.COMMON_POM, GeneratorUtil.readTemplateString(PathManager.COMMONPOM_TEMPLATE_PATH));
		GeneratorUtil.generate(TemplateKey.COMMON_POM, toPath, pomTemplateConfig);
	}

	@Override
	public void generate() throws Exception {
		pomGenerate();
		enumsGenerate();
		exceptionGenerate();
		utilGenerate();
	}
	
	

}
