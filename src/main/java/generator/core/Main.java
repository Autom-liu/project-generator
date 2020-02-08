package generator.core;

import generator.core.core.GeneratorFactory;
import generator.core.core.GeneratorFactoryBuilder;
import generator.core.resource.ConfigReader;
import generator.core.resource.support.JsonConfigReader;

public class Main {

	public static void main(String[] args) throws Exception {
		ConfigReader configReader = new JsonConfigReader("generatorConfig.json");
		
		GeneratorFactory generatorFactory = new GeneratorFactoryBuilder().build(configReader);
		generatorFactory.initializeExcutor();
		generatorFactory.run();
	}
	
}
