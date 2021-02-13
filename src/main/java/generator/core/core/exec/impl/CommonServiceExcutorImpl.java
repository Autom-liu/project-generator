package generator.core.core.exec.impl;

import java.nio.file.Path;
import java.util.Collections;

import generator.core.config.MainConfig;
import generator.core.config.template.ApplicationYmlConfig;
import generator.core.config.template.BasePomTemplateConfig;
import generator.core.config.template.BaseTemplateConfig;
import generator.core.config.template.DependentPomTemplateConfig;
import generator.core.core.exec.CommonServiceExcutor;
import generator.core.core.exec.YmlExcutor;
import generator.core.manager.PathManager;
import generator.core.utils.ConfigConverterUtil;
import generator.core.utils.GeneratorUtil;
import generator.core.utils.TemplateKey;

public class CommonServiceExcutorImpl extends ParentExcutor implements CommonServiceExcutor, YmlExcutor {
	
	private BaseTemplateConfig templateConfig;

	public CommonServiceExcutorImpl(MainConfig configuration) {
		super(configuration);
		this.templateConfig = ConfigConverterUtil.getBaseTemplateConfig(configuration, "common");
	}

	@Override
	public void baseGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcBasePath().resolve("BaseBizEnum.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BaseBizEnum.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.BASE_BIZ_ENUM, templateString1);
		GeneratorUtil.generate(TemplateKey.BASE_BIZ_ENUM, toPath1, templateConfig);

		Path toPath2 = pathManager.getCommonSvcBasePath().resolve("BaseExample.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BaseExample.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.BASE_EXAMPLE, templateString2);
		GeneratorUtil.generate(TemplateKey.BASE_EXAMPLE, toPath2, templateConfig);

		Path toPath3 = pathManager.getCommonSvcBasePath().resolve("BaseOrderByEnum.java");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BaseOrderByEnum.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.BASE_ORDER_BY_ENUM, templateString3);
		GeneratorUtil.generate(TemplateKey.BASE_ORDER_BY_ENUM, toPath3, templateConfig);
	}

	@Override
	public void configGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcConfigPath().resolve("CrosConfig.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("CrosConfig.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.CROS_CONFIG, templateString1);
		GeneratorUtil.generate(TemplateKey.CROS_CONFIG, toPath1, templateConfig);

		Path toPath2 = pathManager.getCommonSvcConfigPath().resolve("MvcConverterConfig.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("MvcConverterConfig.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.MVC_CONVERTER_CONFIG, templateString2);
		GeneratorUtil.generate(TemplateKey.MVC_CONVERTER_CONFIG, toPath2, templateConfig);

		Path toPath3 = pathManager.getCommonSvcConfigPath().resolve("WebConfig.java");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("WebConfig.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.WEB_CONFIG, templateString3);
		GeneratorUtil.generate(TemplateKey.WEB_CONFIG, toPath3, templateConfig);

		Path toPath4 = pathManager.getCommonSvcConfigPath().resolve("WebsiteConfig.java");
		String templateString4 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("WebsiteConfig.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.WEBSITE_CONFIG, templateString4);
		GeneratorUtil.generate(TemplateKey.WEBSITE_CONFIG, toPath4, templateConfig);
	}

	@Override
	public void enumsGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcEnumPath().resolve("DefaultSysErrorEnum.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("DefaultSysErrorEnum.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.DEFAULT_SYSERROR_ENUM, templateString1);
		GeneratorUtil.generate(TemplateKey.DEFAULT_SYSERROR_ENUM, toPath1, templateConfig);

		Path toPath2 = pathManager.getCommonSvcEnumPath().resolve("ErrorEnum.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("ErrorEnum.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.ERROR_ENUM, templateString2);
		GeneratorUtil.generate(TemplateKey.ERROR_ENUM, toPath2, templateConfig);
	}

	@Override
	public void factoryGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcFactoryPath().resolve("BaseEnumConverterDeserializerFactory.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BaseEnumConverterDeserializerFactory.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.BASEENUM_CONVERTER_DESERIALIZER_FACTORY, templateString1);
		GeneratorUtil.generate(TemplateKey.BASEENUM_CONVERTER_DESERIALIZER_FACTORY, toPath1, templateConfig);
	}

	@Override
	public void proxyGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcProxyPath().resolve("CriteriaProxy.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("CriteriaProxy.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.CRITERIA_PROXY, templateString1);
		GeneratorUtil.generate(TemplateKey.CRITERIA_PROXY, toPath1, templateConfig);
	}

	@Override
	public void queryGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcQueryPath().resolve("PageQuery.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("PageQuery.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.PAGE_QUERY, templateString1);
		GeneratorUtil.generate(TemplateKey.PAGE_QUERY, toPath1, templateConfig);
	}

	@Override
	public void serviceGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcServicePath().resolve("BaseService.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BaseService.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.BASE_SERVICE, templateString1);
		GeneratorUtil.generate(TemplateKey.BASE_SERVICE, toPath1, templateConfig);

		Path toPath2 = pathManager.getCommonSvcServicePath().resolve("IService.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("IService.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.ISERVICE, templateString2);
		GeneratorUtil.generate(TemplateKey.ISERVICE, toPath2, templateConfig);

		Path toPath3 = pathManager.getCommonSvcServicePath().resolve("Mapper.java");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("Mapper.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.MAPPER, templateString3);
		GeneratorUtil.generate(TemplateKey.MAPPER, toPath3, templateConfig);
	}

	@Override
	public void voGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcVoPath().resolve("IResult.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("IResult.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.IRESULT, templateString1);
		GeneratorUtil.generate(TemplateKey.IRESULT, toPath1, templateConfig);

		Path toPath2 = pathManager.getCommonSvcVoPath().resolve("PageVO.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("PageVO.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.PAGE_VO, templateString2);
		GeneratorUtil.generate(TemplateKey.PAGE_VO, toPath2, templateConfig);

		Path toPath3 = pathManager.getCommonSvcVoPath().resolve("Result.java");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("Result.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.RESULT, templateString3);
		GeneratorUtil.generate(TemplateKey.RESULT, toPath3, templateConfig);

		Path toPath4 = pathManager.getCommonSvcVoPath().resolve("ResultList.java");
		String templateString4 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("ResultList.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.RESULT_LIST, templateString4);
		GeneratorUtil.generate(TemplateKey.RESULT_LIST, toPath4, templateConfig);

		Path toPath5 = pathManager.getCommonSvcVoPath().resolve("ResultPage.java");
		String templateString5 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("ResultPage.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.RESULT_PAGE, templateString5);
		GeneratorUtil.generate(TemplateKey.RESULT_PAGE, toPath5, templateConfig);
	}

	@Override
	public void webGenerate() throws Exception {
		Path toPath1 = pathManager.getCommonSvcWebPath().resolve("CommonRequestBodyResolver.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("CommonRequestBodyResolver.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.COMMON_REQUEST_BODY_RESOLVER, templateString1);
		GeneratorUtil.generate(TemplateKey.COMMON_REQUEST_BODY_RESOLVER, toPath1, templateConfig);

		Path toPath2 = pathManager.getCommonSvcWebPath().resolve("GobalExceptionHandler.java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("GobalExceptionHandler.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.GOBAL_EXCEPTION_HANDLER, templateString2);
		GeneratorUtil.generate(TemplateKey.GOBAL_EXCEPTION_HANDLER, toPath2, templateConfig);

		Path toPath3 = pathManager.getCommonSvcWebPath().resolve("RequestAop.java");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("RequestAop.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.REQUEST_AOP, templateString3);
		GeneratorUtil.generate(TemplateKey.REQUEST_AOP, toPath3, templateConfig);

		Path toPath4 = pathManager.getCommonSvcWebPath().resolve("RequestPemissionInteceptor.java");
		String templateString4 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("RequestPemissionInteceptor.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.REQUEST_PEMISSION_INTECEPTOR, templateString4);
		GeneratorUtil.generate(TemplateKey.REQUEST_PEMISSION_INTECEPTOR, toPath4, templateConfig);
		
		Path toPath5 = pathManager.getCommonSvcWebPath().resolve("annotation/CommonRequestBody.java");
		String templateString5 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("CommonRequestBody.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.COMMON_REQUEST_BODY, templateString5);
		GeneratorUtil.generate(TemplateKey.COMMON_REQUEST_BODY, toPath5, templateConfig);
		
		Path toPath6 = pathManager.getCommonSvcWebPath().resolve("annotation/PermissionRequire.java");
		String templateString6 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("PermissionRequire.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.PERMISSION_REQUIRE, templateString6);
		GeneratorUtil.generate(TemplateKey.PERMISSION_REQUIRE, toPath6, templateConfig);
	}

	@Override
	protected void pomGenerate() throws Exception {
		String artifactId = super.configuration.getArtifactId();
		String moduleName = artifactId + "-common-service";
		String dependentModuleName = artifactId + "-common";
		BasePomTemplateConfig dependentPomConfig = ConfigConverterUtil.getBasePomTemplateConfig(configuration, dependentModuleName);
		DependentPomTemplateConfig pomTemplateConfig = ConfigConverterUtil.getBasePomTemplateConfig(configuration, moduleName, DependentPomTemplateConfig.class);
		pomTemplateConfig.setDependents(Collections.singletonList(dependentPomConfig));
		
		Path toPath = super.pathManager.getBaseCommonSvcPath().resolve("pom.xml");
		GeneratorUtil.putTemplate(TemplateKey.COMMON_SERVICE_POM, GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("commonservicepom.ftl")));
		GeneratorUtil.generate(TemplateKey.COMMON_SERVICE_POM, toPath, pomTemplateConfig);
	}

	@Override
	public void generate() throws Exception {
		pomGenerate();
		baseGenerate();
		configGenerate();
		enumsGenerate();
		factoryGenerate();
		proxyGenerate();
		queryGenerate();
		serviceGenerate();
		voGenerate();
		webGenerate();
		applicationYmlGenerate();
	}

	@Override
	public void applicationYmlGenerate() throws Exception {
		ApplicationYmlConfig ymlConfig = ConfigConverterUtil.getApplicationYmlConfig(configuration, null);

		Path resourcePath = super.pathManager.getBaseCommonSvcPath().resolve(PathManager.RELATEED_RESOURCE_PATH);
		Path toPath1 = resourcePath.resolve("application-common-dev.yml");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("application-common.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.COMMON_SERVICE_YML, templateString1);
		GeneratorUtil.generate(TemplateKey.COMMON_SERVICE_YML, toPath1, ymlConfig);
		Path toPath2 = resourcePath.resolve("application-common-prod.yml");
		GeneratorUtil.generate(TemplateKey.COMMON_SERVICE_YML, toPath2, ymlConfig);

		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("application-database.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.COMMON_DATABASE_YML, templateString2);
		Path toPath3 = resourcePath.resolve("application-database-dev.yml");
		GeneratorUtil.generate(TemplateKey.COMMON_DATABASE_YML, toPath3, ymlConfig);
		Path toPath4 = resourcePath.resolve("application-database-prod.yml");
		GeneratorUtil.generate(TemplateKey.COMMON_DATABASE_YML, toPath4, ymlConfig);

	}

}
