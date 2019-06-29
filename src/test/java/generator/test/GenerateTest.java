package generator.test;

import generator.config.pom.ModulePomConfigFactory;
import generator.config.pom.ModulePomConfigFactory.ModulePomConfig;
import generator.core.classes.CommonProjectGenerator;
import generator.core.classes.CommonServiceProjectGenerator;
import generator.core.classes.ModulesProjectGenerator;
import generator.core.pom.CommonPomGenerator;
import generator.core.pom.CommonServicePomGenerator;
import generator.core.pom.ModulePomGenerator;
import generator.core.pom.ParentPomGenerator;
import generator.core.yml.CommonServiceYmlGenerator;

public class GenerateTest {
	
	public static void main(String[] args) {
		ParentPomGenerator parent = ParentPomGenerator.getInstance();
		parent.generate();
		CommonPomGenerator common = CommonPomGenerator.getInstance();
		common.generate();
		CommonServicePomGenerator commonService = CommonServicePomGenerator.getInstance();
		commonService.generate();
		
		CommonProjectGenerator commonProject = new CommonProjectGenerator();
		commonProject.generate();
		
		CommonServiceProjectGenerator commonServiceProject = new CommonServiceProjectGenerator();
		commonServiceProject.generate();
		
		CommonServiceYmlGenerator commonServiceYml  = new CommonServiceYmlGenerator();
		commonServiceYml.generate();
		
		new ModulePomConfigFactory("core");
		ModulePomConfig config = ModulePomConfigFactory.getConfig("core");
		new ModulePomGenerator(config).generate();
		new ModulesProjectGenerator(config).generate();
		
	}
}
