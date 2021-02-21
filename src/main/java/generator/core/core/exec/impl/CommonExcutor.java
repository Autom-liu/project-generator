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
	
	private BaseTemplateConfig templateConfig;

	public CommonExcutor(MainConfig configuration) {
		super(configuration);
		this.templateConfig = ConfigConverterUtil.getBaseTemplateConfig(configuration, "common");
	}

	@Override
	public void enumsGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonEnumsPath().resolve("BaseStatusEnum.java");
		Path toPath2 = pathManager.getCommonEnumsPath().resolve("IErrorEnum.java");
		GeneratorUtil.putTemplate(TemplateKey.BASE_STATUS_ENUM, GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BaseStatusEnum.ftl")));
		GeneratorUtil.putTemplate(TemplateKey.IERROR_ENUM, GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("IErrorEnum.ftl")));
		GeneratorUtil.generate(TemplateKey.BASE_STATUS_ENUM, toPath1, templateConfig);
		GeneratorUtil.generate(TemplateKey.IERROR_ENUM, toPath2, templateConfig);
	}

	@Override
	public void exceptionGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonExceptionPath().resolve("BizException.java");
		GeneratorUtil.putTemplate(TemplateKey.BIZ_EXCEPTION, GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BizException.ftl")));
		GeneratorUtil.generate(TemplateKey.BIZ_EXCEPTION, toPath1, templateConfig);
		Path toPath2 = pathManager.getCommonExceptionPath().resolve("SystemException.java");
		GeneratorUtil.putTemplate(TemplateKey.SYSTEM_EXCEPTION, GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("SystemException.ftl")));
		GeneratorUtil.generate(TemplateKey.SYSTEM_EXCEPTION, toPath2, templateConfig);
	}

	@Override
	public void utilGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonUtilPath().resolve("ConverterUtils.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("ConverterUtils.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.COVERTER_UTILS, templateString1);
		GeneratorUtil.generate(TemplateKey.COVERTER_UTILS, toPath1, templateConfig);
		
		Path toPath2 = pathManager.getCommonUtilPath().resolve("DateUtils.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("DateUtils.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.DATE_UTILS, templateString2);
		GeneratorUtil.generate(TemplateKey.DATE_UTILS, toPath2, templateConfig);
		
		Path toPath3 = pathManager.getCommonUtilPath().resolve("IdWorker.java");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("IdWorker.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.ID_WORKER, templateString3);
		GeneratorUtil.generate(TemplateKey.ID_WORKER, toPath3, templateConfig);
		
		Path toPath4 = pathManager.getCommonUtilPath().resolve("JsonUtils.java");
		String templateString4 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("JsonUtils.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.JSON_UTILS, templateString4);
		GeneratorUtil.generate(TemplateKey.JSON_UTILS, toPath4, templateConfig);
		
		Path toPath5 = pathManager.getCommonUtilPath().resolve("NumberUtils.java");
		String templateString5 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("NumberUtils.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.NUMBER_UTILS, templateString5);
		GeneratorUtil.generate(TemplateKey.NUMBER_UTILS, toPath5, templateConfig);

		Path toPath6 = pathManager.getCommonUtilPath().resolve("StringUtils.java");
		String templateString6 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("StringUtils.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.STRING_UTILS, templateString6);
		GeneratorUtil.generate(TemplateKey.STRING_UTILS, toPath6, templateConfig);
	}

	@Override
	protected void pomGenerate() throws Exception {
		String artifactId = super.configuration.getArtifactId();
		String moduleName = artifactId + "-common";
		BasePomTemplateConfig pomTemplateConfig = ConfigConverterUtil.getBasePomTemplateConfig(configuration, moduleName);
		Path toPath = super.pathManager.getBaseCommonPath().resolve("pom.xml");
		GeneratorUtil.putTemplate(TemplateKey.COMMON_POM, GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("commonpom.ftl")));
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
